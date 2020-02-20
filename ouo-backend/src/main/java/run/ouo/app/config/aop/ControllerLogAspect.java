package run.ouo.app.config.aop;

import run.ouo.app.dao.mongo.LogDAO;
import run.ouo.app.manager.LoginStatusManager;
import run.ouo.app.model.bo.LoginStatusBO;
import run.ouo.app.model.common.HttpStatusCode;
import run.ouo.app.model.entity.mongo.LogEntity;
import run.ouo.app.model.vo.response.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * aop日志记录
 */
@Aspect
@Component
public class ControllerLogAspect {
    private static final String PACKAGE_PREFIX = "run.ouo.app.controller.";
    private static final String PACKAGE_PREFIX2 = "run.ouo.app.config.handler.BindExceptionHandler.";
    private static final String CONTROLLER_POSTFIX = "Controller.";

    private final LoginStatusManager loginStatusManager;
    private final LogDAO logDAO;

    public ControllerLogAspect(LoginStatusManager loginStatusManager, LogDAO logDAO) {
        this.loginStatusManager = loginStatusManager;
        this.logDAO = logDAO;
    }

    /**
     * 切入点：controller、自定义全局异常
     */
    @Pointcut("execution(public * run.ouo.app.controller..*.*(..)) || " +
            "execution(public * run.ouo.app.config.handler.BindExceptionHandler.handleBindException(..))")
    public void controllerLog() {
    }

    // 环绕通知
    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();

        LogEntity log = new LogEntity();
        // 前置通知
        before(log, joinPoint);

        Object result = null;
        try {
            result = joinPoint.proceed();  // 执行方法
        } catch (Throwable ex) {
            // 异常通知
            log.setException(ex.getMessage());
            setResponseCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            result = new ResultVO(ResultVO.SERVER_ERROR, "未知错误", null);
        }
        // 后置通知
        after(log, result, System.currentTimeMillis() - startTime);
        logDAO.insert(log);  // 记录日志

        return result;
    }

    /**
     * 前置通知
     *
     * @param logEntity 日志
     * @param joinPoint 连接点
     */
    private void before(LogEntity logEntity, ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(request.getSession());
            String requestUrl = request.getRequestURI();
            if (request.getQueryString() != null) {
                requestUrl += "?" + request.getQueryString();
            }

            logEntity.setRequestUrl(requestUrl);  // 日志记录URL
            logEntity.setUserId(loginStatus.getUserId());  // 日志记录用户id
            logEntity.setUserType(loginStatus.getUserType());  // 日志记录用户类型
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String businessTarget = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
        businessTarget = businessTarget
                .replace(PACKAGE_PREFIX, "")
                .replace(CONTROLLER_POSTFIX, ".")
                .replace(PACKAGE_PREFIX2, "");
        logEntity.setBusinessTarget(businessTarget);  // 日志记录业务
    }

    /**
     * 后置通知
     *
     * @param log         日志
     * @param result      方法执行结果
     * @param executeTime 运行时间
     */
    private void after(LogEntity log, Object result, long executeTime) {
        if (!(result instanceof ResultVO)) {
            return;
        }

        ResultVO resultVO = (ResultVO) result;
        log.setResultCode(resultVO.getCode());  // 日志记录结果状态码
        log.setMessage(resultVO.getMessage());  // 日志记录结果消息
        log.setExecuteTime(executeTime);  // 日志记录执行时间
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (attributes == null) {
            return null;
        }

        return attributes.getRequest();
    }

    private void setResponseCode(int statusCode) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return;
        }

        response.setStatus(statusCode);
    }
}

package run.ouo.app.config.aop;

import run.ouo.app.model.common.HttpStatusCode;
import run.ouo.app.model.vo.response.ResultVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * aop 失败http状态码设置
 */
@Aspect
@Component
public class ResultFailedCodeAspect {
    // 切入点为controller和结果封装
    @Pointcut("execution(public run.ouo.app.model.vo.response.ResultVO " +
            "run.ouo.app.controller..*.*(..))")
    public void controllerResult() {
    }

    // 后置通知
    @AfterReturning(value = "controllerResult()", returning = "result")
    public Object afterReturning(ResultVO result) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return result;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return result;
        }

        if (result.getCode() == ResultVO.FAIL) {
            response.setStatus(HttpStatusCode.NOT_ACCEPTABLE);
        }

        return result;
    }
}

package run.ouo.app.config.permission;

import com.fasterxml.jackson.databind.ObjectMapper;
import run.ouo.app.manager.LoginStatusManager;
import run.ouo.app.model.bo.LoginStatusBO;
import run.ouo.app.model.common.HttpStatusCode;
import run.ouo.app.model.vo.response.ResultVO;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    private final PermissionScanner scanner;
    private final LoginStatusManager loginStatusManager;
    private final ObjectMapper objectMapper;

    public PermissionInterceptor(PermissionScanner scanner, LoginStatusManager loginStatusManager, ObjectMapper objectMapper) {
        this.scanner = scanner;
        this.loginStatusManager = loginStatusManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 获取拦截到的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 获取权限
        Permission permission = scanner.scan(method);

        // 不需要登陆/超级权限
        if (!permission.getNeedLogin() || permission.getUserType().equals(0)) {
            return true;
        }

        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(request.getSession());

        // 未登陆
        if (!loginStatus.getLoggedIn()) {
            noLogin(response);
            return false;
        }

        // 用户权限不符
        if (!loginStatus.getUserType().equals(permission.getUserType())) {
            errorRole(response);
            return false;
        }

        // 越权
        if ((loginStatus.getPermission() & permission.getPermission()) != permission.getPermission()) {
            noPermission(response);
            return false;
        }

        return true;
    }

    private void noLogin(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.UNAUTHORIZED);
        ResultVO resultVO = new ResultVO(ResultVO.NO_LOGIN, "您没有登录", null);
        sendResult(resultVO, response);
    }

    private void errorRole(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = new ResultVO(ResultVO.ERROR_ROLE, "您的角色错误", null);
        sendResult(resultVO, response);
    }

    private void noPermission(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = new ResultVO(ResultVO.NO_PERMISSION, "您没有此权限", null);
        sendResult(resultVO, response);
    }

    private void sendResult(ResultVO result, HttpServletResponse response) {
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        try (Writer writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

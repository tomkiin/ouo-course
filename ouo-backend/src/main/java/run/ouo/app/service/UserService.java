package run.ouo.app.service;

import run.ouo.app.manager.LoginStatusManager;
import run.ouo.app.manager.UserManager;
import run.ouo.app.model.bo.AuthInfoBO;
import run.ouo.app.model.bo.LoginStatusBO;
import run.ouo.app.model.common.UserType;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.utils.Md5Encrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService extends BaseService {
    private static final String PASSWORD_SALT = "_Rain_Ng-_Azure_99";

    private final HttpSession session;
    private final UserManager manager;
    private final LoginStatusManager loginStatusManager;
    private final Md5Encrypt md5Encrypt;

    public UserService(HttpSession session, UserManager manager, LoginStatusManager loginStatusManager, Md5Encrypt md5Encrypt) {
        this.session = session;
        this.manager = manager;
        this.loginStatusManager = loginStatusManager;
        this.md5Encrypt = md5Encrypt;
    }

    public ResultVO login(String username, String password, Integer userType) {
        AuthInfoBO authInfo = manager.getAuthInfoByUsername(username, userType);
        if (authInfo == null) {
            return failedResult("用户不存在");
        }
        String passwordHash = computePasswordHash(password);
        if (!passwordHash.equals(authInfo.getPassword())) {
            return failedResult("密码错误");
        }

        if (authInfo.getUserType().equals(UserType.STUDENT)) {
            manager.updateStudentLastLoginTime(username);
        }

        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfo);
        loginStatusManager.setLoginStatus(session, statusBO);

        return success(statusBO);
    }

    public ResultVO getLoginStatus() {
        LoginStatusBO statusBO = loginStatusManager.getLoginStatus(session);
        return success(statusBO);
    }

    public ResultVO logout() {
        loginStatusManager.setLoginStatus(session, null);
        return success("注销成功");
    }

    public String computePasswordHash(String password) {
        String md5 = md5Encrypt.computeHexString(password);
        return md5Encrypt.computeHexString(md5 + PASSWORD_SALT);
    }
}

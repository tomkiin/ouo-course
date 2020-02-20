package run.ouo.app.model.vo.request;

import run.ouo.app.model.common.UserType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "必须选择用户类型")
    @Range(min = UserType.STUDENT, max = UserType.ADMIN, message = "无效的用户类型")
    private Integer userType;
}

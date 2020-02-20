package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import run.ouo.app.config.permission.annotation.Admin;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@TableName("ouo_admin")
@Data
public class AdminEntity {
    public static final String ID = "admin_id";
    public static final String USERNAME = "admin_username";
    public static final String PASSWORD = "admin_password";
    public static final String PRIVILEGE = "admin_privilege";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @TableField(USERNAME)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @TableField(PASSWORD)
    @NotBlank(message = "密码不能为空")
    private String password;

    @TableField(PRIVILEGE)
    @NotNull
    @Range(min = Admin.NO, max = Admin.ALL)
    private Integer privilege;
}

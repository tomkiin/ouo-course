package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@TableName("ouo_student")
@Data
public class StudentEntity {
    public static final String ID = "student_id";
    public static final String CLASS_ID = "student_class_id";
    public static final String NUMBER = "student_number";
    public static final String NAME = "student_name";
    public static final String PASSWORD = "student_password";
    public static final String EMAIL = "student_email";
    public static final String BIRTHDAY = "student_birthday";
    public static final String SEX = "student_sex";
    public static final String LAST_LOGIN_TIME = "student_last_login_time";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(CLASS_ID)
    @NotNull(message = "班级不能为空")
    private Integer classId;

    @TableField(NUMBER)
    @Length(min = 9, max = 9, message = "学号长度必须为9位")
    private String number;

    @TableField(NAME)
    @NotBlank(message = "学生姓名不能为空")
    private String name;

    @TableField(PASSWORD)
    @NotNull
    private String password;

    @TableField(value = EMAIL, updateStrategy = FieldStrategy.IGNORED)
    @Email(message = "邮箱格式不正确")
    private String email;

    @TableField(value = BIRTHDAY, updateStrategy = FieldStrategy.IGNORED)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @TableField(SEX)
    @Range(min = 0, max = 1)
    private Integer sex;

    @TableField(LAST_LOGIN_TIME)
    private Date lastLoginTime;
}

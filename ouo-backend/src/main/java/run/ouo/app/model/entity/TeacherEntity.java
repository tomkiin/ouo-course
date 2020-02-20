package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@TableName("ouo_teacher")
@Data
public class TeacherEntity {
    public static final String ID = "teacher_id";
    public static final String DEPARTMENT_ID = "teacher_department_id";
    public static final String NUMBER = "teacher_number";
    public static final String NAME = "teacher_name";
    public static final String PASSWORD = "teacher_password";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @TableField(DEPARTMENT_ID)
    @NotNull(message = "院系不能为空")
    private Integer departmentId;

    @TableField(NUMBER)
    @Length(min = 9, max = 9, message = "工号长度必须为9位")
    private String number;

    @TableField(NAME)
    @NotBlank(message = "教师姓名不能为空")
    private String name;

    @TableField(PASSWORD)
    @NotNull
    private String password;
}

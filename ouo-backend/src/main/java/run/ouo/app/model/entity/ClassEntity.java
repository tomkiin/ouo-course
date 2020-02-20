package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@TableName("ouo_class")
@Data
public class ClassEntity {
    public static final String ID = "class_id";
    public static final String MAJOR_ID = "class_major_id";
    public static final String GRADE = "class_grade";
    public static final String NAME = "class_name";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @TableField(MAJOR_ID)
    @NotNull(message = "专业不能为空")
    private Integer majorId;

    @TableField(GRADE)
    @NotNull
    @Range(min = 1980, max = 2999, message = "年级范围必须在1980-2999之间")
    private Integer grade;

    @TableField(NAME)
    @NotBlank(message = "班级不能为空")
    private String name;
}

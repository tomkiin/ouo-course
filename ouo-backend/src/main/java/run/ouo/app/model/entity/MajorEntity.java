package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@TableName("ouo_major")
@Data
public class MajorEntity {
    public static final String ID = "major_id";
    public static final String DEPARTMENT_ID = "major_department_id";
    public static final String NAME = "major_name";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @TableField(DEPARTMENT_ID)
    @NotNull(message = "院系不能为空")
    private Integer departmentId;

    @TableField(NAME)
    @NotBlank(message = "专业不能为空")
    private String name;
}

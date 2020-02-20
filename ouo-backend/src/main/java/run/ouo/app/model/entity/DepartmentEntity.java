package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@TableName("ouo_department")
@Data
public class DepartmentEntity {
    public static final String ID = "department_id";
    public static final String NAME = "department_name";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @TableField(NAME)
    @NotBlank(message = "院系不能为空")
    private String name;
}

package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@TableName("ouo_course")
@Data
public class CourseEntity {
    public static final String ID = "course_id";
    public static final String TEACHER_ID = "course_teacher_id";
    public static final String NAME = "course_name";
    public static final String GRADE = "course_grade";
    public static final String TIME = "course_time";
    public static final String LOCATION = "course_location";
    public static final String CREDIT = "course_credit";
    public static final String TYPE = "course_type";
    public static final String SELECTED_COUNT = "course_selected_count";
    public static final String MAX_SIZE = "course_max_size";
    public static final String EXAM_DATE = "course_exam_date";
    public static final String EXAM_LOCATION = "course_exam_location";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @TableField(TEACHER_ID)
    @NotNull(message = "授课教师不能为空")
    private Integer teacherId;

    @TableField(NAME)
    @NotBlank(message = "课程不能为空")
    private String name;

    @TableField(GRADE)
    @NotNull
    @Range(min = 1980, max = 2999, message = "年级信息错误")
    private Integer grade;

    @TableField(TIME)
    @Pattern(regexp = "[1-7]-[0-9]*-[1-4]", message = "课程时间错误") // 一周七天、一天最多12节课、每节课最长时间4节
    private String time;

    @TableField(LOCATION)
    @NotBlank(message = "上课地点不能为空")
    private String location;

    @TableField(CREDIT)
    @NotNull
    @Range(min = 0, max = 20, message = "学分信息错误")
    private Integer credit;

    @TableField(SELECTED_COUNT)
    private Integer selectedCount;

    @TableField(MAX_SIZE)
    @NotNull
    @Range(min = 0, message = "容量不能为负数")
    private Integer maxSize;

    @TableField(value = EXAM_DATE, updateStrategy = FieldStrategy.IGNORED)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date examDate;

    @TableField(value = EXAM_LOCATION, updateStrategy = FieldStrategy.IGNORED)
    private String examLocation;
}

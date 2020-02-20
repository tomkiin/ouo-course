package run.ouo.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


@TableName("ouo_student_course")
@Data
public class StudentCourseEntity {
    public static final String ID = "sc_id";
    public static final String STUDENT_ID = "sc_student_id";
    public static final String COURSE_ID = "sc_course_id";
    public static final String DAILY_SCORE = "sc_daily_score";
    public static final String EXAM_SCORE = "sc_exam_score";
    public static final String SCORE = "sc_score";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(STUDENT_ID)
    @NotNull(message = "学生不能为空")
    private Integer studentId;

    @TableField(COURSE_ID)
    @NotNull(message = "课程不能为空")
    private Integer courseId;

    @TableField(value = DAILY_SCORE, updateStrategy = FieldStrategy.IGNORED)
    @Range(min = 0, max = 100, message = "平时分数必须在0-100之间")
    private Integer dailyScore;

    @TableField(value = EXAM_SCORE, updateStrategy = FieldStrategy.IGNORED)
    @Range(min = 0, max = 100, message = "考试分数必须在0-100之间")
    private Integer examScore;

    @TableField(value = SCORE, updateStrategy = FieldStrategy.IGNORED)
    @Range(min = 0, max = 100, message = "总评分数必须在0-100之间")
    private Integer score;
}

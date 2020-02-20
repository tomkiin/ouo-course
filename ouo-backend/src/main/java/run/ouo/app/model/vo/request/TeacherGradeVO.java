package run.ouo.app.model.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class TeacherGradeVO {
    @NotNull
    private Integer studentCourseId;

    @Range(min = 0, max = 100, message = "平时分数必须在0-100之间")
    private Integer dailyScore;

    @Range(min = 0, max = 100, message = "考试分数必须在0-100之间")
    private Integer examScore;

    @Range(min = 0, max = 100, message = "总评分数必须在0-100之间")
    private Integer score;
}

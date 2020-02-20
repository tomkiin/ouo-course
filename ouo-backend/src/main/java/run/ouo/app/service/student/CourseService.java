package run.ouo.app.service.student;

import run.ouo.app.manager.OptionManager;
import run.ouo.app.manager.student.CourseManager;
import run.ouo.app.model.entity.StudentCourseEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service("student_courseService")
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final OptionManager optionManager;

    public CourseService(CourseManager manager, OptionManager optionManager) {
        this.manager = manager;
        this.optionManager = optionManager;
    }

    public ResultVO list() {
        Integer studentId = getUserId();
        return success(manager.listStudentCourseSelected(studentId));
    }

    public ResultVO delete(Integer studentCourseId) {
        Integer studentId = getUserId();
        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("现在不是选课退课时间!");
        }
        StudentCourseEntity studentCourse = manager.getStudentCourseById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + studentCourseId + "不存在");
        }
        if (!studentCourse.getStudentId().equals(studentId)) {
            return failedResult("此课程非此学生所选!");
        }
        if (studentCourse.getDailyScore() != null || studentCourse.getExamScore() != null || studentCourse.getScore() != null) {
            return failedResult("学生已获得成绩, 不能退选");
        }

        manager.deleteStudentCourse(studentCourse);
        return success("退选成功");
    }
}

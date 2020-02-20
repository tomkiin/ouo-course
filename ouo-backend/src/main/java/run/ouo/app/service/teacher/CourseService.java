package run.ouo.app.service.teacher;

import run.ouo.app.manager.teacher.CourseManager;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.model.vo.response.table.TeacherCourseItemVO;
import run.ouo.app.service.BaseService;
import run.ouo.app.utils.LessonTimeConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacher_courseService")
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO list() {
        Integer teacherId = getUserId();

        List<TeacherCourseItemVO> list = manager.listTeacherCourse(teacherId);
        for (TeacherCourseItemVO vo : list) {
            vo.setTime(lessonTimeConverter.covertTimePart(vo.getTime()));
        }

        return success(list);
    }
}

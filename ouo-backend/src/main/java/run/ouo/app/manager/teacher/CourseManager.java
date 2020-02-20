package run.ouo.app.manager.teacher;

import run.ouo.app.dao.TeacherDAO;
import run.ouo.app.manager.BaseManager;
import run.ouo.app.model.vo.response.table.TeacherCourseItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("teacher_CourseManager")
public class CourseManager extends BaseManager {
    private final TeacherDAO teacherDAO;

    public CourseManager(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId) {
        return teacherDAO.listTeacherCourse(teacherId);
    }
}

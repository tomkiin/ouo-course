package run.ouo.app.manager.student;

import run.ouo.app.dao.StudentCourseDAO;
import run.ouo.app.manager.BaseManager;
import run.ouo.app.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("student_timetableManager")
public class TimetableManager extends BaseManager {
    private final StudentCourseDAO studentCourseDAO;

    public TimetableManager(StudentCourseDAO studentCourseDAO) {
        this.studentCourseDAO = studentCourseDAO;
    }

    public List<TimetableItemVO> listStudentTimetable(Integer studentId) {
        return studentCourseDAO.listStudentTimetable(studentId);
    }
}

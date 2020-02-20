package run.ouo.app.manager.student;

import run.ouo.app.dao.StudentCourseDAO;
import run.ouo.app.manager.BaseManager;
import run.ouo.app.model.vo.response.table.StudentExamItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamManager extends BaseManager {
    private final StudentCourseDAO studentCourseDAO;

    public ExamManager(StudentCourseDAO studentCourseDAO) {
        this.studentCourseDAO = studentCourseDAO;
    }

    public List<StudentExamItemVO> listStudentExam(Integer studentId) {
        return studentCourseDAO.listStudentExam(studentId);
    }
}

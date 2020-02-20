package run.ouo.app.manager.student;

import run.ouo.app.dao.StudentDAO;
import run.ouo.app.manager.BaseManager;
import run.ouo.app.model.entity.StudentEntity;
import run.ouo.app.model.vo.response.StudentInfoVO;
import org.springframework.stereotype.Component;

@Component
public class InfoManager extends BaseManager {
    private final StudentDAO studentDAO;

    public InfoManager(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentInfoVO getStudentInfoByStudentId(Integer studentId) {
        return studentDAO.getStudentInfoById(studentId);
    }

    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    public int updateStudent(StudentEntity entity) {
        return studentDAO.update(entity);
    }
}

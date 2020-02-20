package run.ouo.app.manager;

import run.ouo.app.dao.AdminDAO;
import run.ouo.app.dao.StudentDAO;
import run.ouo.app.dao.TeacherDAO;
import run.ouo.app.model.bo.AuthInfoBO;
import run.ouo.app.model.common.UserType;
import run.ouo.app.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserManager extends BaseManager {
    private final AdminDAO adminDAO;
    private final TeacherDAO teacherDAO;
    private final StudentDAO studentDAO;

    public UserManager(AdminDAO adminDAO, TeacherDAO teacherDAO, StudentDAO studentDAO) {
        this.adminDAO = adminDAO;
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

    public AuthInfoBO getAuthInfoByUsername(String username, Integer userType) {
        if (userType == UserType.STUDENT) {
            return AuthInfoBO.fromStudent(studentDAO.getByNumber(username));
        } else if (userType == UserType.TEACHER) {
            return AuthInfoBO.fromTeacher(teacherDAO.getByNumber(username));
        } else if (userType == UserType.ADMIN) {
            return AuthInfoBO.fromAdmin(adminDAO.getByUsername(username));
        }

        return null;
    }

    public void updateStudentLastLoginTime(String number) {
        StudentEntity entity = studentDAO.getByNumber(number);
        if (entity == null) {
            return;
        }

        entity.setLastLoginTime(new Date());
        studentDAO.update(entity);
    }
}

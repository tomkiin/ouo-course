package run.ouo.app.manager.admin;

import run.ouo.app.dao.CourseDAO;
import run.ouo.app.dao.StudentCourseDAO;
import run.ouo.app.dao.StudentDAO;
import run.ouo.app.manager.BaseManager;
import run.ouo.app.model.entity.CourseEntity;
import run.ouo.app.model.entity.StudentCourseEntity;
import run.ouo.app.model.entity.StudentEntity;
import run.ouo.app.model.vo.response.table.StudentCourseItemVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StudentCourseManager extends BaseManager {
    private final CourseDAO courseDAO;
    private final StudentCourseDAO studentCourseDAO;
    private final StudentDAO studentDAO;

    public StudentCourseManager(CourseDAO courseDAO, StudentCourseDAO studentCourseDAO, StudentDAO studentDAO) {
        this.courseDAO = courseDAO;
        this.studentCourseDAO = studentCourseDAO;
        this.studentDAO = studentDAO;
    }

    public Integer getPageCount(String className, String courseName, String studentName) {
        int count = studentCourseDAO.count(className, courseName, studentName);
        return calcPageCount(count, StudentCourseDAO.PAGE_SIZE);
    }

    public List<StudentCourseItemVO> getPage(Integer index, String className, String courseName, String studentName) {
        return studentCourseDAO.getPage(index, className, courseName, studentName);
    }

    public StudentCourseEntity get(Integer id) {
        return studentCourseDAO.get(id);
    }

    @Transactional
    public int create(StudentCourseEntity entity) {
        courseDAO.increaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.insert(entity);
    }

    public int update(StudentCourseEntity entity) {
        return studentCourseDAO.update(entity);
    }

    @Transactional
    public int delete(StudentCourseEntity entity) {
        courseDAO.decreaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.delete(entity.getId());
    }

    public CourseEntity getCourseById(Integer courseId) {
        return courseDAO.get(courseId);
    }

    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    public StudentCourseEntity getByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        return studentCourseDAO.getByCourseIdAndStudentId(courseId, studentId);
    }

    public Integer getStudentGradeById(Integer studentId) {
        return studentDAO.getGradeById(studentId);
    }

    public boolean inSameDepartment(Integer courseId, Integer studentId) {
        return courseDAO.getDepartmentIdById(courseId)
                .equals(studentDAO.getDepartmentIdById(studentId));
    }
}

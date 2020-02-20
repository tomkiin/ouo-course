package run.ouo.app.manager.admin;

import run.ouo.app.dao.CourseDAO;
import run.ouo.app.dao.DepartmentDAO;
import run.ouo.app.dao.TeacherDAO;
import run.ouo.app.manager.BaseManager;
import run.ouo.app.model.entity.DepartmentEntity;
import run.ouo.app.model.entity.TeacherEntity;
import run.ouo.app.model.vo.response.IdNameVO;
import run.ouo.app.model.vo.response.table.TeacherItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherManager extends BaseManager {
    private final DepartmentDAO departmentDAO;
    private final TeacherDAO teacherDAO;
    private final CourseDAO courseDAO;

    public TeacherManager(DepartmentDAO departmentDAO, TeacherDAO teacherDAO, CourseDAO courseDAO) {
        this.departmentDAO = departmentDAO;
        this.teacherDAO = teacherDAO;
        this.courseDAO = courseDAO;
    }

    public Integer getPageCount(String departmentName, String name) {
        int count = teacherDAO.count(departmentName, name);
        return calcPageCount(count, TeacherDAO.PAGE_SIZE);
    }

    public List<TeacherItemVO> getPage(Integer index, String departmentName, String name) {
        return teacherDAO.getPage(index, departmentName, name);
    }

    public TeacherEntity get(Integer id) {
        return teacherDAO.get(id);
    }

    public int create(TeacherEntity entity) {
        return teacherDAO.insert(entity);
    }

    public int update(TeacherEntity entity) {
        return teacherDAO.update(entity);
    }

    public int delete(Integer id) {
        return teacherDAO.delete(id);
    }

    public boolean hasCourse(Integer teacherId) {
        return courseDAO.countByTeacherId(teacherId) > 0;
    }

    public DepartmentEntity getDepartmentById(Integer id) {
        return departmentDAO.get(id);
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<TeacherEntity> entityList = teacherDAO.listName();
        for (TeacherEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}

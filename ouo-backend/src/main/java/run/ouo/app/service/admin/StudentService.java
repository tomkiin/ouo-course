package run.ouo.app.service.admin;

import run.ouo.app.manager.admin.StudentManager;
import run.ouo.app.model.entity.StudentEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import run.ouo.app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService {
    private final StudentManager manager;
    private final UserService userService;

    public StudentService(StudentManager manager, UserService userService) {
        this.manager = manager;
        this.userService = userService;
    }

    public ResultVO getPageCount(String majorName, String className, String name) {
        return success(manager.getPageCount(majorName, className, name));
    }

    public ResultVO getPage(Integer index, String majorName, String className, String name) {
        return success(manager.getPage(index, majorName, className, name));
    }

    public ResultVO get(Integer id) {
        StudentEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("学生Id: " + id + "不存在!");
        }

        entity.setPassword("");

        return success(entity);
    }

    public ResultVO update(StudentEntity entity) {
        StudentEntity origin = manager.get(entity.getId());
        if (origin == null) {
            return failedResult("学生Id: " + entity.getId() + "不存在!");
        }
        if (manager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级Id: " + entity.getClassId() + "不存在!");
        }

        if (entity.getPassword().equals("")) {
            entity.setPassword(origin.getPassword());
        } else {
            entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        }

        manager.update(entity);
        return success("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("学生Id: " + id + "不存在!");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("此学生还有未退选课程");
        }

        manager.delete(id);
        return success("删除成功");
    }

    public ResultVO create(StudentEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("学生Id: " + entity.getId() + "已存在!");
        }
        if (manager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级Id: " + entity.getClassId() + "不存在!");
        }
        entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        manager.create(entity);
        return success("添加成功");
    }

    public ResultVO listName() {
        return success(manager.listName());
    }
}

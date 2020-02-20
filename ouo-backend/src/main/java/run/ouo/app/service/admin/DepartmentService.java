package run.ouo.app.service.admin;

import run.ouo.app.manager.admin.DepartmentManager;
import run.ouo.app.model.entity.DepartmentEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService {
    private final DepartmentManager manager;

    public DepartmentService(DepartmentManager manager) {
        this.manager = manager;
    }

    public ResultVO getPageCount(String name) {
        return success(manager.getPageCount(name));
    }

    public ResultVO getPage(Integer index, String name) {
        return success(manager.getPage(index, name));
    }

    public ResultVO get(Integer id) {
        DepartmentEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("系Id: " + id + "不存在!");
        }

        return success(entity);
    }

    public ResultVO update(DepartmentEntity entity) {
        if (manager.get(entity.getId()) == null) {
            return failedResult("系Id: " + entity.getId() + "不存在!");
        }

        manager.update(entity);
        return success("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("系Id: " + id + "不存在!");
        }
        if (manager.hasMajor(id)) {
            return failedResult("此系中还有专业未被删除");
        }
        if (manager.hasTeacher(id)) {
            return failedResult("此系中还有教师未被删除");
        }

        manager.delete(id);
        return success("删除成功");
    }

    public ResultVO create(DepartmentEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("系Id: " + entity.getId() + "已存在!");
        }

        manager.create(entity);
        return success("添加成功");
    }

    public ResultVO listName() {
        return success(manager.listName());
    }
}

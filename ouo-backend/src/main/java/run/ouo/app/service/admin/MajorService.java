package run.ouo.app.service.admin;

import run.ouo.app.manager.admin.MajorManager;
import run.ouo.app.model.entity.MajorEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MajorService extends BaseService {
    private final MajorManager manager;

    public MajorService(MajorManager manager) {
        this.manager = manager;
    }

    public ResultVO getPageCount(String departmentName, String name) {
        return success(manager.getPageCount(departmentName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        return success(manager.getPage(index, departmentName, name));
    }

    public ResultVO get(Integer id) {
        MajorEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("专业Id: " + id + "不存在!");
        }

        return success(entity);
    }

    public ResultVO update(MajorEntity entity) {
        if (manager.get(entity.getId()) == null) {
            return failedResult("专业Id: " + entity.getId() + "不存在!");
        }
        if (manager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }

        manager.update(entity);
        return success("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("专业Id: " + id + "不存在!");
        }
        if (manager.hasClass(id)) {
            return failedResult("此专业中还有班级未被删除");
        }

        manager.delete(id);
        return success("删除成功");
    }

    public ResultVO create(MajorEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("专业Id: " + entity.getId() + "已存在!");
        }
        if (manager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }

        manager.create(entity);
        return success("添加成功");
    }

    public ResultVO listName() {
        return success(manager.listName());
    }
}

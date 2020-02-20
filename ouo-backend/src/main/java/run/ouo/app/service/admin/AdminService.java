package run.ouo.app.service.admin;

import run.ouo.app.manager.admin.AdminManager;
import run.ouo.app.model.entity.AdminEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import run.ouo.app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService {
    private final AdminManager manager;
    private final UserService userService;

    public AdminService(AdminManager manager, UserService userService) {
        this.manager = manager;
        this.userService = userService;
    }

    public ResultVO get(Integer id) {
        AdminEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }

        entity.setPassword("");

        return success(entity);
    }

    public ResultVO update(AdminEntity entity) {
        AdminEntity originEntity = manager.get(entity.getId());
        if (originEntity == null) {
            return failedResult("管理员Id: " + entity.getId() + "不存在!");
        }

        if (entity.getPassword().equals("")) {
            entity.setPassword(originEntity.getPassword());
        } else {
            entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        }


        manager.update(entity);
        return success("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }

        manager.delete(id);
        return success("删除成功");
    }

    public ResultVO create(AdminEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("管理员Id: " + entity.getId() + "已存在!");
        }

        entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        manager.create(entity);
        return success("添加成功");
    }

    public ResultVO list() {
        return success(manager.list());
    }
}

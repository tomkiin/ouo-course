package run.ouo.app.controller.admin;

import run.ouo.app.config.permission.annotation.Admin;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.entity.AdminEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.admin.AdminService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.ADMIN_MANAGE)
@RequestMapping("/admin/admin")
@RestController
public class AdminController extends BaseController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated AdminEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated AdminEntity entity) {
        return service.update(entity);
    }

    @GetMapping()
    public ResultVO list() {
        return service.list();
    }
}
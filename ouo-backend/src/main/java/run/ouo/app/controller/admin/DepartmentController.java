package run.ouo.app.controller.admin;

import run.ouo.app.config.permission.annotation.Admin;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.entity.DepartmentEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.admin.DepartmentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.DEPARTMENT_MANAGE)
@RequestMapping("/admin/department")
@RestController
public class DepartmentController extends BaseController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated DepartmentEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated DepartmentEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String name) {
        return service.getPageCount(name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String name) {
        return service.getPage(1, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String name) {
        return service.getPage(index, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}

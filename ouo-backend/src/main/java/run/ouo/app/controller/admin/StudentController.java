package run.ouo.app.controller.admin;

import run.ouo.app.config.permission.annotation.Admin;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.entity.StudentEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.admin.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.STUDENT_MANAGE)
@RequestMapping("/admin/student")
@RestController
public class StudentController extends BaseController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated StudentEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String majorName, String className, String name) {
        return service.getPageCount(majorName, className, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String majorName, String className, String name) {
        return service.getPage(1, majorName, className, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String majorName, String className, String name) {
        return service.getPage(index, majorName, className, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}

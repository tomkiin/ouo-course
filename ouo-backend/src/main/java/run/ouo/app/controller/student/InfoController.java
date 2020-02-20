package run.ouo.app.controller.student;

import run.ouo.app.config.permission.annotation.Student;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.vo.request.StudentInfoFormVO;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.student.InfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Student
@RequestMapping("/student/info")
@RestController
public class InfoController extends BaseController {
    private final InfoService service;

    public InfoController(InfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResultVO get() {
        return service.get();
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentInfoFormVO formVO) {
        return service.update(formVO);
    }
}

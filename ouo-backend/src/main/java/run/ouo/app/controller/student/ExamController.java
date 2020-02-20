package run.ouo.app.controller.student;

import run.ouo.app.config.permission.annotation.Student;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.student.ExamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/exam")
@RestController
public class ExamController extends BaseController {
    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @RequestMapping("/list")
    public ResultVO list() {
        return service.list();
    }
}

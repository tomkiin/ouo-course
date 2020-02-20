package run.ouo.app.controller.teacher;

import run.ouo.app.config.permission.annotation.Teacher;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.teacher.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Teacher
@RequestMapping("/teacher/course")
@RestController("teacher_courseController")
public class CourseController extends BaseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @RequestMapping("/list")
    public ResultVO list() {
        return service.list();
    }
}

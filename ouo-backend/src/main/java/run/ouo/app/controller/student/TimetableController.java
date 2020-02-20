package run.ouo.app.controller.student;

import run.ouo.app.config.permission.annotation.Student;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.student.TimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/timetable")
@RestController("student_timeTableController")
public class TimetableController extends BaseController {
    private final TimetableService service;

    public TimetableController(TimetableService service) {
        this.service = service;
    }

    @RequestMapping
    public ResultVO get() {
        return service.get();
    }
}

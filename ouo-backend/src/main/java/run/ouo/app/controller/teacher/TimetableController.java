package run.ouo.app.controller.teacher;

import run.ouo.app.config.permission.annotation.Teacher;
import run.ouo.app.controller.BaseController;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.teacher.TimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Teacher
@RequestMapping("/teacher/timetable")
@RestController
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

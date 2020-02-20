package run.ouo.app.controller.teacher;

import run.ouo.app.config.permission.annotation.Teacher;
import run.ouo.app.model.vo.request.TeacherGradeVO;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.teacher.GradeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Teacher
@RequestMapping("/teacher/grade")
@RestController
public class GradeController {
    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String studentName) {
        return service.getPageCount(courseName, studentName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String studentName) {
        return service.getPage(index, courseName, studentName);
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated TeacherGradeVO vo) {
        return service.update(vo);
    }
}

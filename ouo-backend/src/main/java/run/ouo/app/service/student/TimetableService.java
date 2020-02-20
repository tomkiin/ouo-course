package run.ouo.app.service.student;

import run.ouo.app.manager.student.TimetableManager;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service("student_timetableService")
public class TimetableService extends BaseService {
    private final TimetableManager manager;

    public TimetableService(TimetableManager manager) {
        this.manager = manager;
    }

    public ResultVO get() {
        Integer studentId = getUserId();
        return success(manager.listStudentTimetable(studentId));
    }
}

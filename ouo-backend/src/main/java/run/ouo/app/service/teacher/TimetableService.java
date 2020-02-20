package run.ouo.app.service.teacher;

import run.ouo.app.manager.teacher.TimetableManager;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class TimetableService extends BaseService {
    private final TimetableManager manager;

    public TimetableService(TimetableManager manager) {
        this.manager = manager;
    }

    public ResultVO get() {
        Integer teacherId = getUserId();
        return success(manager.listTeacherTimetable(teacherId));
    }
}

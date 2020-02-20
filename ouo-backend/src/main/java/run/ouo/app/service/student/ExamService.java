package run.ouo.app.service.student;

import run.ouo.app.manager.student.ExamManager;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ExamService extends BaseService {
    private final ExamManager manager;

    public ExamService(ExamManager manager) {
        this.manager = manager;
    }

    public ResultVO list() {
        return success(manager.listStudentExam(getUserId()));
    }
}

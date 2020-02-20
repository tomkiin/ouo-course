package run.ouo.app.service;

import run.ouo.app.manager.OptionManager;
import run.ouo.app.model.vo.response.ResultVO;
import org.springframework.stereotype.Service;

@Service
public class OptionService extends BaseService {
    private final OptionManager manager;

    public OptionService(OptionManager manager) {
        this.manager = manager;
    }

    public ResultVO setAllowStudentSelect(Boolean status) {
        manager.setAllowStudentSelect(status);
        return success("成功");
    }

    public ResultVO getAllowStudentSelect() {
        return success(manager.getAllowStudentSelect());
    }

    public ResultVO setAllowTeacherGrade(Boolean status) {
        manager.setAllowTeacherGrade(status);
        return success("成功");
    }

    public ResultVO getAllowTeacherGrade() {
        return success(manager.getAllowTeacherGrade());
    }
}

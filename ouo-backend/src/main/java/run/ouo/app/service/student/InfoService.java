package run.ouo.app.service.student;

import run.ouo.app.manager.student.InfoManager;
import run.ouo.app.model.entity.StudentEntity;
import run.ouo.app.model.vo.request.StudentInfoFormVO;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.BaseService;
import run.ouo.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InfoService extends BaseService {
    private final UserService userService;

    private final InfoManager manager;

    public InfoService(UserService userService, InfoManager manager) {
        this.userService = userService;
        this.manager = manager;
    }

    public ResultVO get() {
        return success(manager.getStudentInfoByStudentId(getUserId()));
    }

    public ResultVO update(@RequestBody @Validated StudentInfoFormVO studentInfoForm) {
        StudentEntity student = manager.getStudentById(getUserId());

        String password = studentInfoForm.getPassword();
        if (password == null || password.equals("")) {
            password = student.getPassword();
        } else {
            password = userService.computePasswordHash(password);
        }

        BeanUtils.copyProperties(studentInfoForm, student);
        student.setPassword(password);
        manager.updateStudent(student);

        return success("更新成功");
    }
}

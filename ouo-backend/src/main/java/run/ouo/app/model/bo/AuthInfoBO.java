package run.ouo.app.model.bo;

import run.ouo.app.model.common.UserType;
import run.ouo.app.model.entity.AdminEntity;
import run.ouo.app.model.entity.StudentEntity;
import run.ouo.app.model.entity.TeacherEntity;
import lombok.Data;

@Data
public class AuthInfoBO {
    private Integer id;
    private String username;
    private String password;
    private Integer userType;
    private Integer permission = 0;

    public AuthInfoBO() {
    }

    private AuthInfoBO(Integer id, String username, String password, Integer userType) {
        this(id, username, password, userType, 0);
    }

    private AuthInfoBO(Integer id, String username, String password, Integer userType, Integer permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.permission = permission;
    }

    public static AuthInfoBO fromStudent(StudentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getName(), entity.getPassword(), UserType.STUDENT);
    }

    public static AuthInfoBO fromTeacher(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getName(), entity.getPassword(), UserType.TEACHER);
    }

    public static AuthInfoBO fromAdmin(AdminEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getUsername(), entity.getPassword(), UserType.ADMIN,
                entity.getPrivilege());
    }
}

package run.ouo.app.config.permission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限定义
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Admin {

    int NO = 0; // 无权限
    int DEPARTMENT_MANAGE = 1; // 院系管理
    int MAJOR_MANAGE = 2; // 专业管理
    int CLASS_MANAGE = 4; // 班级管理
    int STUDENT_MANAGE = 8; // 学生管理
    int TEACHER_MANAGE = 16; // 教师管理
    int COURSE_MANAGE = 32; // 课程管理
    int STUDENT_COURSE_MANAGE = 64; // 学生选课管理
    int ADMIN_MANAGE = 128; // 管理员管理
    int ALL = 255; // 所有权限

    int value() default 0;
}

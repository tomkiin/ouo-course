package run.ouo.app.config.permission;

import run.ouo.app.config.permission.annotation.*;
import run.ouo.app.model.common.UserType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 权限扫描器
 */
@Component
public class PermissionScanner {
    /**
     * 权限扫描
     *
     * @param method 方法
     * @return 权限封装
     */
    public Permission scan(Method method) {
        Admin annotation;
        if (getAnnotation(method, NoLimit.class) != null) {
            return new Permission(false);

        } else if (getAnnotation(method, Login.class) != null) {
            return new Permission(UserType.NO, 0);

        } else if (getAnnotation(method, Student.class) != null) {
            return new Permission(UserType.STUDENT);

        } else if (getAnnotation(method, Teacher.class) != null) {
            return new Permission(UserType.TEACHER);

        } else if ((annotation = getAnnotation(method, Admin.class)) != null) {
            return new Permission(UserType.ADMIN, annotation.value());
        }

        return new Permission(false);
    }

    /**
     * 获取方法/类的注解
     *
     * @param method          方法
     * @param annotationClass 注解类型
     * @param <T>             范型
     * @return 注解
     */
    private <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass) {
        T annotation = method.getDeclaredAnnotation(annotationClass);
        if (annotation == null) {
            annotation = method.getDeclaringClass().getDeclaredAnnotation(annotationClass);
        }

        return annotation;
    }
}

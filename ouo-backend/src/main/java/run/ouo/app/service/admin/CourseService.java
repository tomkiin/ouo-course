package run.ouo.app.service.admin;

import run.ouo.app.manager.admin.CourseManager;
import run.ouo.app.model.bo.CourseItemBO;
import run.ouo.app.model.entity.CourseEntity;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.model.vo.response.table.CourseItemVO;
import run.ouo.app.service.BaseService;
import run.ouo.app.utils.LessonTimeConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return success(manager.getPageCount(departmentName, teacherName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String teacherName, String name) {
        List<CourseItemBO> boList = manager.getPage(index, departmentName, teacherName, name);
        List<CourseItemVO> voList = new ArrayList<>(boList.size());

        for (CourseItemBO bo : boList) {
            CourseItemVO vo = new CourseItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return success(voList);
    }

    public ResultVO get(Integer id) {
        CourseEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }

        return success(entity);
    }

    public ResultVO update(CourseEntity entity) {
        CourseEntity origin = manager.get(entity.getId());
        if (origin == null) {
            return failedResult("课程Id: " + entity.getId() + "不存在!");
        }
        if (manager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师Id: " + entity.getTeacherId() + "不存在!");
        }

        entity.setSelectedCount(origin.getSelectedCount());

        manager.update(entity);
        return success("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("还有学生未退选此课程");
        }

        manager.delete(id);
        return success("删除成功");
    }

    public ResultVO create(CourseEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("课程Id: " + entity.getId() + "已存在!");
        }
        if (manager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师Id: " + entity.getTeacherId() + "不存在!");
        }

        manager.create(entity);
        return success("添加成功");
    }

    public ResultVO listName() {
        return success(manager.listName());
    }
}

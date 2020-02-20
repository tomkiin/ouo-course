package run.ouo.app.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import run.ouo.app.model.entity.TeacherEntity;
import run.ouo.app.model.vo.response.table.TeacherCourseItemVO;
import run.ouo.app.model.vo.response.table.TeacherItemVO;
import run.ouo.app.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<TeacherEntity> {
    Integer count(String departmentName, String name);

    IPage<TeacherItemVO> getPage(IPage<TeacherItemVO> page, String departmentName, String name);

    List<TimetableItemVO> listTeacherTimetable(Integer teacherId);

    List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId);
}

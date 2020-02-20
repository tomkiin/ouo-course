package run.ouo.app.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import run.ouo.app.model.entity.ClassEntity;
import run.ouo.app.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMapper extends BaseMapper<ClassEntity> {
    Integer count(String departmentName, String majorName, String name);

    IPage<MajorItemVO> getPage(IPage<MajorItemVO> page, String departmentName, String majorName, String name);
}

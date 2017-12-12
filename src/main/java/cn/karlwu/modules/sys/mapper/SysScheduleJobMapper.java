package cn.karlwu.modules.sys.mapper;

import cn.karlwu.modules.sys.pojo.SysScheduleJob;
import cn.karlwu.modules.sys.pojo.SysScheduleJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysScheduleJobMapper {
    int countByExample(SysScheduleJobExample example);

    int deleteByExample(SysScheduleJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysScheduleJob record);

    int insertSelective(SysScheduleJob record);

    List<SysScheduleJob> selectByExample(SysScheduleJobExample example);

    SysScheduleJob selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysScheduleJob record, @Param("example") SysScheduleJobExample example);

    int updateByExample(@Param("record") SysScheduleJob record, @Param("example") SysScheduleJobExample example);

    int updateByPrimaryKeySelective(SysScheduleJob record);

    int updateByPrimaryKey(SysScheduleJob record);
}
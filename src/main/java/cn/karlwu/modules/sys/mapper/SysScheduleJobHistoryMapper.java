package cn.karlwu.modules.sys.mapper;

import cn.karlwu.modules.sys.pojo.SysScheduleJobHistory;
import cn.karlwu.modules.sys.pojo.SysScheduleJobHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysScheduleJobHistoryMapper {
    int countByExample(SysScheduleJobHistoryExample example);

    int deleteByExample(SysScheduleJobHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysScheduleJobHistory record);

    int insertSelective(SysScheduleJobHistory record);

    List<SysScheduleJobHistory> selectByExample(SysScheduleJobHistoryExample example);

    SysScheduleJobHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysScheduleJobHistory record, @Param("example") SysScheduleJobHistoryExample example);

    int updateByExample(@Param("record") SysScheduleJobHistory record, @Param("example") SysScheduleJobHistoryExample example);

    int updateByPrimaryKeySelective(SysScheduleJobHistory record);

    int updateByPrimaryKey(SysScheduleJobHistory record);
}
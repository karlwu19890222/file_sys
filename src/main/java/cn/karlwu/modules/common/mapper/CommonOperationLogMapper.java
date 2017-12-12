package cn.karlwu.modules.common.mapper;

import cn.karlwu.modules.common.pojo.CommonOperationLog;
import cn.karlwu.modules.common.pojo.CommonOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonOperationLogMapper {
    int countByExample(CommonOperationLogExample example);

    int deleteByExample(CommonOperationLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommonOperationLog record);

    int insertSelective(CommonOperationLog record);

    List<CommonOperationLog> selectByExample(CommonOperationLogExample example);

    CommonOperationLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommonOperationLog record, @Param("example") CommonOperationLogExample example);

    int updateByExample(@Param("record") CommonOperationLog record, @Param("example") CommonOperationLogExample example);

    int updateByPrimaryKeySelective(CommonOperationLog record);

    int updateByPrimaryKey(CommonOperationLog record);
}
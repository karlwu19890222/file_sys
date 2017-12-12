package cn.karlwu.modules.common.mapper;

import cn.karlwu.modules.common.pojo.CommonLeavingMessage;
import cn.karlwu.modules.common.pojo.CommonLeavingMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonLeavingMessageMapper {
    int countByExample(CommonLeavingMessageExample example);

    int deleteByExample(CommonLeavingMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommonLeavingMessage record);

    int insertSelective(CommonLeavingMessage record);

    List<CommonLeavingMessage> selectByExample(CommonLeavingMessageExample example);

    CommonLeavingMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommonLeavingMessage record, @Param("example") CommonLeavingMessageExample example);

    int updateByExample(@Param("record") CommonLeavingMessage record, @Param("example") CommonLeavingMessageExample example);

    int updateByPrimaryKeySelective(CommonLeavingMessage record);

    int updateByPrimaryKey(CommonLeavingMessage record);
}
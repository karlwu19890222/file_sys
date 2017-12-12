package cn.karlwu.modules.common.mapper;

import cn.karlwu.modules.common.pojo.CommonAttachment;
import cn.karlwu.modules.common.pojo.CommonAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonAttachmentMapper {
    int countByExample(CommonAttachmentExample example);

    int deleteByExample(CommonAttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommonAttachment record);

    int insertSelective(CommonAttachment record);

    List<CommonAttachment> selectByExample(CommonAttachmentExample example);

    CommonAttachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommonAttachment record, @Param("example") CommonAttachmentExample example);

    int updateByExample(@Param("record") CommonAttachment record, @Param("example") CommonAttachmentExample example);

    int updateByPrimaryKeySelective(CommonAttachment record);

    int updateByPrimaryKey(CommonAttachment record);
}
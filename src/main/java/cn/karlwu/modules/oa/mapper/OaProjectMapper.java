package cn.karlwu.modules.oa.mapper;

import cn.karlwu.modules.oa.pojo.OaProject;
import cn.karlwu.modules.oa.pojo.OaProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OaProjectMapper {
    int countByExample(OaProjectExample example);

    int deleteByExample(OaProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(OaProject record);

    int insertSelective(OaProject record);

    List<OaProject> selectByExample(OaProjectExample example);

    OaProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OaProject record, @Param("example") OaProjectExample example);

    int updateByExample(@Param("record") OaProject record, @Param("example") OaProjectExample example);

    int updateByPrimaryKeySelective(OaProject record);

    int updateByPrimaryKey(OaProject record);
}
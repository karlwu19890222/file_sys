package cn.karlwu.modules.sys.mapper;

import cn.karlwu.modules.sys.pojo.SysMail;
import cn.karlwu.modules.sys.pojo.SysMailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMailMapper {
    int countByExample(SysMailExample example);

    int deleteByExample(SysMailExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysMail record);

    int insertSelective(SysMail record);

    List<SysMail> selectByExample(SysMailExample example);

    SysMail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysMail record, @Param("example") SysMailExample example);

    int updateByExample(@Param("record") SysMail record, @Param("example") SysMailExample example);

    int updateByPrimaryKeySelective(SysMail record);

    int updateByPrimaryKey(SysMail record);
}
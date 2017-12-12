package cn.karlwu.modules.file.mapper;

import cn.karlwu.modules.file.pojo.FileDiskSys;
import cn.karlwu.modules.file.pojo.FileDiskSysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileDiskSysMapper {
    int countByExample(FileDiskSysExample example);

    int deleteByExample(FileDiskSysExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileDiskSys record);

    int insertSelective(FileDiskSys record);

    List<FileDiskSys> selectByExample(FileDiskSysExample example);

    FileDiskSys selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileDiskSys record, @Param("example") FileDiskSysExample example);

    int updateByExample(@Param("record") FileDiskSys record, @Param("example") FileDiskSysExample example);

    int updateByPrimaryKeySelective(FileDiskSys record);

    int updateByPrimaryKey(FileDiskSys record);
}
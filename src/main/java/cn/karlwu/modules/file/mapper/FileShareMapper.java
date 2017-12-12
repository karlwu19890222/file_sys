package cn.karlwu.modules.file.mapper;

import cn.karlwu.modules.file.pojo.FileShare;
import cn.karlwu.modules.file.pojo.FileShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileShareMapper {
    int countByExample(FileShareExample example);

    int deleteByExample(FileShareExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileShare record);

    int insertSelective(FileShare record);

    List<FileShare> selectByExample(FileShareExample example);

    FileShare selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileShare record, @Param("example") FileShareExample example);

    int updateByExample(@Param("record") FileShare record, @Param("example") FileShareExample example);

    int updateByPrimaryKeySelective(FileShare record);

    int updateByPrimaryKey(FileShare record);
}
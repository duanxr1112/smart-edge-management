package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lenovo.ailab.smartedge.dao.po.OperatingSystem;

public interface OperatingSystemMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(OperatingSystem record);
    
    int insertversion(@Param("id") Integer id,@Param("version") String version,@Param("fileName") String fileName,@Param("filePath") String filePath);

    OperatingSystem selectByPrimaryKey(@Param("id") Integer id);

    List<OperatingSystem> selectAll(@Param("name") String name,@Param("osCode") String osCode);

    List<OperatingSystem> selectAllByOsCodes(@Param("osCodes") List<String> osCodes);

    int updateByPrimaryKey(OperatingSystem record);

    OperatingSystem selectFilePath(@Param("path") String path,@Param("type") String type);

    List<OperatingSystem> selectNoPageOSystem();

}
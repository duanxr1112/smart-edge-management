package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.EdgeDevice;
import org.apache.ibatis.annotations.Param;

public interface EdgeDeviceMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(EdgeDevice record);

    EdgeDevice selectByPrimaryKey(@Param("id") Integer id);

    List<EdgeDevice> selectByPrimaryKeys(@Param("ids") List<Integer> id);

    List<EdgeDevice> selectAllByPrimaryKey(@Param("ids") List<Integer> ids);

    List<EdgeDevice> selectAlledge(@Param("type") String type);

    List<EdgeDevice> selectAllByType(@Param("type") String type, @Param("name") String name);
    
    int updateByPrimaryKey(EdgeDevice record);

    EdgeDevice downloadthumbnail(@Param("path") String path);

    List<EdgeDevice> selectEdgeDeviceByOIds(@Param("oScodes") List<String> oIds);

    List<EdgeDevice> selectEdgeDeviceByType(@Param("type") String type);
}
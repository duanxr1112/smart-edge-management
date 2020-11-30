package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.EdgeDeviceSoftware;
import org.apache.ibatis.annotations.Param;

public interface EdgeDeviceSoftwareMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteEdgeDeviceSoftwareById(@Param("deviceId") Integer deviceId);
    
    int insert(EdgeDeviceSoftware record);

    EdgeDeviceSoftware selectByPrimaryKey(Integer id);

    List<EdgeDeviceSoftware> selectAll();
    
    List<EdgeDeviceSoftware> selectAllEdgeDeviceSoftwareByDeviceId(Integer deviceId);

    List<EdgeDeviceSoftware> selectEdgeDeviceSoftwareByDeviceId(@Param("deviceId") Integer deviceId);

    List<EdgeDeviceSoftware> selectEdgeDeviceSoftwareByDeviceIds(@Param("deviceIds") List<Integer> deviceId);

    int updateByPrimaryKey(EdgeDeviceSoftware record);

    int insertList(@Param("list") List<EdgeDeviceSoftware> addSoftWareList);

    List<EdgeDeviceSoftware> selectEdgeDeviceBySIds(@Param("sIds") List<Integer> sIds);

}
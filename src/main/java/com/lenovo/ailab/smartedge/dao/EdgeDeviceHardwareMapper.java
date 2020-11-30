package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.HardwareDeploymentFile;
import org.apache.ibatis.annotations.Param;

import com.lenovo.ailab.smartedge.dao.po.EdgeDeviceHardware;

public interface EdgeDeviceHardwareMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteEdgeDeviceHardwareById(@Param("deviceId") Integer deviceId);

    int insert(EdgeDeviceHardware record);

    EdgeDeviceHardware selectByPrimaryKey(Integer id);
    
    List<EdgeDeviceHardware> selectAll();
    
    List<EdgeDeviceHardware> selectEdgeDeviceHardwareByDeviceId(@Param("deviceId") Integer deviceId);

    List<EdgeDeviceHardware> selectEdgeDeviceHardwareByeId(@Param("deviceId") Integer deviceId);

    List<EdgeDeviceHardware> selectEdgeDeviceHardwareByDeviceIds(@Param("deviceIds") List<Integer> deviceId);

    List<HardwareDeploymentFile> selectAllEdgeDeviceHardwareByDeviceId(@Param("deviceId") Integer deviceId);

    int updateByPrimaryKey(EdgeDeviceHardware record);

    List<EdgeDeviceHardware> selectEdgeDeviceHardwareByHid(@Param("hIds") List<Integer> hIds);
}
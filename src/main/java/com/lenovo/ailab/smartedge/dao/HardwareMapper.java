package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.DepartmentDevice;
import com.lenovo.ailab.smartedge.dao.po.HardwareDeploymentFile;
import com.lenovo.ailab.smartedge.dao.po.HardwareEnd;
import org.apache.ibatis.annotations.Param;

import com.lenovo.ailab.smartedge.dao.po.Hardware;

public interface HardwareMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Hardware record);
    
    int insertversion(@Param("id") Integer id,@Param("version") String version);

    Hardware selectByPrimaryKey(@Param("id") Integer id);

    List<Hardware> selectAll(@Param("hCode") String hCode,@Param("name") String name);
    
    List<Hardware> selectHardwareByType(@Param("hCode")String hCode, @Param("name")String name);

    List<Hardware> selectHardwareByTypeForEdge(@Param("hCode")String hCode, @Param("name")String name);

    int updateByPrimaryKey(Hardware record);

    List<Hardware> selectNoPageHardware(@Param("type") String type,@Param("endTypeId") Integer endTypeId);

    List<HardwareEnd> selectEndType();

    int insertHardwareDeploymentFile(HardwareDeploymentFile hardwareDeploymentFile);

    int updateHardwareInstrument(HardwareDeploymentFile hardwareDeploymentFile);

    Hardware selectByPrimaryKeyAndEdge(@Param("id") Integer id);

    int insertHardwareInstallImage(DepartmentDevice carema);

    HardwareDeploymentFile selectHardwareInstrument(@Param("type")  String type);
}
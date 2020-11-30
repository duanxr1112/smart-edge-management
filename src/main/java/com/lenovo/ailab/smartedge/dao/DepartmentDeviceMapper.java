package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.dao.vo.CameraInstallStatusVo;
import org.apache.ibatis.annotations.Param;

public interface DepartmentDeviceMapper {
	
    int deleteByPrimaryKey(@Param("dId") Integer id);

    int deleteByDeviceId(@Param("deviceId") Integer id);

    int deleteByIds(@Param("ids") List<Integer> ids);

    int deleteByPrimaryKeys(@Param("eIds") List<Integer> eIds,@Param("dId") Integer dId);

    int insert(DepartmentDevice record);

    int insertList(@Param("list") List<DepartmentDevice> record);

    DepartmentDevice selectByPrimaryKey(@Param("id") Integer id);

    List<DepartmentDevice> selectByDepartmentId(@Param("dId") Integer id);

   // DepartmentDevice selectByDCode(String dcode);

    List<DepartmentDevice> selectAll();

    List<DepartmentDeviceForBtit> getAllDeviceListForPage(@Param("tenant") Integer tenant,@Param("shopCode") String shopCode, @Param("status") String status, @Param("type") String type);

    //List<DepartmentDevice> selectDeviceBytype(@Param("departmentId") Integer departmentId,@Param("type") String type);

    int updateEdgeDeviceStatus(@Param("cId") Integer cId,@Param("status") Integer status);

    int updateDeviceStatus(@Param("deviceStatusVoList") List<DepartmentDevice> deviceStatusVoList);

    int updateByPrimaryKey(DepartmentDevice record);

    int updateListByPrimaryKey(@Param("list") List<DepartmentDevice> record);

    List<DepartmentDevice> getDepartmentEdgeDevicedetail(@Param("departmentId") Integer id);

    List<DepartmentDevice> selectByeIds(@Param("eIds") List<Integer> id);

    List<DepartmentDevice> selectBydId(@Param("departmentId") Integer departmentId);

    List<DepartmentDevice> selectEdgeDeviceForCamera();

    List<DepartmentDeviceForBtit> selectDeviceByDepartmentId(@Param("departmentId") Integer departmentId,@Param("type") String type);

    List<CameraInstallStatusVo> getAllCameraInstallStatus(@Param("departmentId") Integer departmentId);

    int updateCameraInstallStatus(@Param("deviceId") Integer deviceId, @Param("status") Integer status);

    List<HardwareDeploymentFile> getDeploymentFile(@Param("departmentId") Integer departmentId);

    List<DepartmentDeviceForUseType> selectCameraDepartmentById(@Param("departmentId") Integer id);

    Department selectCameraDepartmentByCode(@Param("btitdCode") String code);

    int updateDeviceUseType(@Param("list") List<DepartmentDeviceForUseType> departmentDeviceForUseType);

    DepartmentDevice selectEdgeServerByDepartmentId(@Param("departmentId")Integer departmentId, @Param("deviceId")Integer id);

    List<UpdateDeviceConfig> selectDepartmentBySoftware(@Param("sId") Integer id);

    DepartmentDevice selectByDepartmentIdAndDeviceId(@Param("storeId") Integer storeId, @Param("deviceId") Integer deviceId);

    List<DepartmentDevice> selectDeviceByEdgeServerId(@Param("storeId") Integer storeId, @Param("deviceId") Integer deviceId);

    DepartmentDevice selectCodeByDepartmentIdAndDeviceId(@Param("storeId") Integer storeId,@Param("deviceId") Integer deviceId);

    int updateDeviceConfig(@Param("deviceId") Integer deviceId,@Param("agentConfig") String agentConfig);

    int updateDeviceConfigByCode(@Param("deviceCode") String deviceId,@Param("agentConfig") String agentConfig);

    DepartmentDevice selectByCode(@Param("deviceCode") String devicecode);

   // int updateDeviceIsConfig(@Param("list") List<Integer> initCaremaList);
}
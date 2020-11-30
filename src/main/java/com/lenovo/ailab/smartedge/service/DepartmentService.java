package com.lenovo.ailab.smartedge.service;

import java.util.HashMap;
import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.dao.vo.*;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Title: DepartmentService.java
 * @Autohr "chenpeng"
 * @data 2019年12月3日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
public interface DepartmentService {
	
    int deleteDepartmentById(Integer id);

    ResponseModel insertDepartment(Department record);

    Department selectDepartmentById(Integer id);

    Department selectDepartmentByCode(String dcode);

    List<Department> selectAllDepartment(String name, String dCode);
    
    PageResult<Department> selectPageDepartment(PageRequest pageRequest, String name, String dCode);

    int updateDepartmentById(Department record);
	
    int deleteDepartmentDevice(Integer id);

    int insertDepartmentDevice(List<AddDepartmentDevice> addDepartmentDevice,Integer dId,String type);

    DepartmentDevice selectDepartmentDeviceById(Integer id);
        
    List<DepartmentDevice> selectDepartmentDeviceBydepartmentId(Integer departmentId);
    
    List<DepartmentDevice> selectAllDepartmentDevice();
    
    PageResult<DepartmentDevice> selectPageDepartmentDevice(Integer departmentId,String type,PageRequest pageRequest);

    int updateDepartmentDeviceById(DepartmentDevice record);

    int deleteDepartmentDeviceTopologyById(Integer id);

    ResponseModel insertDepartmentDeviceTopology(DepartmentDeviceTopology record);

    DepartmentDeviceTopology selectDepartmentDeviceTopologyById(Integer id);

    List<Department> selectAllDepartmentTopology();
    
    List<DepartmentDeviceTopology> selectAllDepartmentTopologyByDepartmentId(Integer departmentId);
    
    int updateDepartmentDeviceTopologyById(DepartmentDeviceTopology record);

    ResponseModel getDepartmentAddress();

    List<DepartmentDevice> getDepartmentEdgeDevicedetail(Integer id);

    ResponseModel getDepartmentsConfigForCamera(Integer departmentId);

    List<HardwareDeploymentFile> getAllDepartmentsConfigForCamera();

    List<Department>  selectAllDepartment();

    List<EdgeDeviceStatusVo> selectEquipmentDetail(Integer dId);

    ResponseModel getJwttoken(String lenovoToken);

    ResponseModel whetherDepartmentDeviceIsNormal(List<EdgeDeviceStatusVo>  edgeDeviceStatusVoList);

    Department selectByBtitDepartmentCode(String newCode,String type);

    PageResult<DepartmentDeviceForBtit> getDeviceListForPage(PageRequest pageRequest, Integer tenant,String shopCode, String status, String type);

    List<CameraInstallStatusVo> getAllCameraInstallStatus(Integer departmentId);

    ResponseModel updateCameraInstallStatus(Integer deviceId, String imageId,Integer status);

    EquipmentDeploymentDetails getAllDeviceDistribution(Integer departmentId);

    List<DepartmentDeviceForUseType> selectCameraDepartmentById(Integer id);

    List<DepartmentDeviceForUseType> selectCameraDepartmentByCode(String departmentCode);

    ResponseModel updateDeviceUseType(Integer departmentId,List<DepartmentDeviceForUseType> departmentDeviceForUseType);

    ResponseModel deleteDeviceForCamera(Integer deviceId);

    ResponseModel uploadDeviceForCameraCoordinates(Integer departmentId, CameraCoordinatesTwo cameraCoordinates,Integer deviceId);

    ResponseModel getNoticeForAgent(Integer storeId, Integer deviceId);

    ResponseModel getHeartForServer(ServerStatus serverStatus);

    ResponseModel reStartToContrlServer(String name, Integer storeId, Integer deviceId);

    ResponseModel upgradeVersionForModel(Integer storeId, Integer deviceId, EdgeServerInitVo edgeServerInitvO, HttpServletResponse response);

    ResponseModel reStartTx2(Integer storeId, Integer deviceId);

    ResponseModel updateLineIntotheShop(Integer storeId, Integer deviceId,EdgeServerInitVo edgeServerInitvO);

    ResponseModel init(Integer storeId, Integer deviceId, EdgeServerInitVo edgeServerInitvO, HttpServletResponse response);

    ResponseModel run(Integer storeId, Integer deviceId,EdgeServerInitVo edgeServerInitvO);

    List<EdgeServerDetail> getDeviceList(Integer departmentId);

    HashMap<String,Object> getConfig(String type, Integer deviceId);

    ResponseModel addModelConfig(EdgeServerDetail softwareConfig, Integer departmentId, Integer deviceId);

    ResponseModel stopModel(Integer storeId, Integer deviceId, EdgeServerInitVo edgeServerInitvO);

    ResponseModel deleteModel(Integer storeId, Integer deviceId, EdgeServerInitVo edgeServerInitvO);

    ResponseModel updateEdgeDeviceConfig(EdgeDeviceConfig edgeDeviceConfig, String deviceId);

    ResponseModel uploadInstrument(HardwareDeploymentFile installAndDeploy, MultipartFile file);

    ResponseModel selectInstrument(String type);

    ResponseModel updateConfig(String type, Integer storeId, Integer deviceId, EdgeServerInitVo edgeServerInitvO);

    ResponseModel insertModelConfigLog(ModelConfigLog modelConfigLog, Integer storeId, Integer deviceId);

    ResponseModel getLog(Integer departmentId, String startTime, String endTime);
}

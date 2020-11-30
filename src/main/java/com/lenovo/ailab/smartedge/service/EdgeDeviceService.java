package com.lenovo.ailab.smartedge.service;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.utils.ResponseModel;


/**
 * Title: EdgeDeviceService.java
 * @Autohr "chenpeng"
 * @data 2019年12月4日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
public interface EdgeDeviceService {
	
	int deleteEdgeDeviceById(Integer id);

    int insertEdgeDevice(EdgeDevice record);

    int insertEdgeDevices(EdgeDevice record);

    EdgeDevice selectEdgeDeviceById(Integer id);

    List<EdgeDevice> selectAllEdgeDeviceById(List<Integer> ids);

    List<EdgeDevice> selectAllEdgeDevice(String type);

	List<EdgeDevice> selectAllEdgeDeviceByType(String type,String name);
    
    PageResult<EdgeDevice> selectPageEdgeDevice(PageRequest pageRequest);
    
    PageResult<EdgeDevice> selectPageEdgeDeviceByType(String type,PageRequest pageRequest,String name);

    int updateEdgeDeviceById(EdgeDevice record);
    
    int deleteEdgeDeviceSoftwareById(Integer deviceId,Integer softwareId);

    int insertEdgeDeviceSoftware(EdgeDeviceSoftware record);

    EdgeDeviceSoftware selectEdgeDeviceSoftwareById(Integer id);

    List<EdgeDeviceSoftware> selectAllEdgeDeviceSoftware();
    
	List<EdgeDeviceSoftware> selectAllEdgeDeviceSoftwareByDeviceId(Integer deviceId);	
    
    PageResult<EdgeDeviceSoftware> selectPageEdgeDeviceSoftware(PageRequest pageRequest);
    
    PageResult<EdgeDeviceSoftware> selectPageEdgeDeviceSoftwareByDeviceId(Integer deviceId,PageRequest pageRequest);

    int updateEdgeDeviceSoftwareById(EdgeDeviceSoftware record);

    int deleteEdgeDeviceHardwareById(Integer deviceId,Integer hardwareId);

    int insertEdgeDeviceHardware(EdgeDeviceHardware record);

    EdgeDeviceHardware selectEdgeDeviceHardwareById(Integer id);
    
    List<EdgeDeviceHardware> selectEdgeDeviceHardwareByDeviceId(Integer deviceId);

    List<EdgeDeviceHardware> selectAllEdgeDeviceHardware();
    
    PageResult<EdgeDeviceHardware> selectPageEdgeDeviceHardware(PageRequest pageRequest);
    
    PageResult<EdgeDeviceHardware> selectPageEdgeDeviceHardwareByDeviceId(Integer deviceId,PageRequest pageRequest);

    int updateEdgeDeviceHardwareById(EdgeDeviceHardware record);

    List<DepartmentDevice> selectEquipmentDetail(Integer dId);

    ResponseModel selectDeviceByTx2IdAndDepartmentId(String storeId, String deviceId);
}

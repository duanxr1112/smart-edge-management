package com.lenovo.ailab.smartedge.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.web.multipart.MultipartFile;


/**
 * Title: ResourceService.java
 * @Autohr "chenpeng"
 * @data 2019年12月3日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
public interface ResourceService {

	int deleteHardwareById(Integer id);

    int insertHardware(Hardware record);
    
    int insertHardwareVersion(Integer id,String version);

    Hardware selectHardwareById(String type,Integer id);

    List<Hardware> selectAllHardware(String hCode, String name);
       
    List<Hardware> selectAllByType(String type, String hCode, String name);
    
    PageResult<Hardware> selectPageHardware(PageRequest pageRequest,String hCode, String name);
    
    PageResult<Hardware> selectPageHardwareByType(String type, String hCode, String name,PageRequest pageRequest);

    int updateHardware(Hardware record);

    int deleteOSystemById(Integer id);

    int insertOSystem(OperatingSystem record);
    
    int insertOSystemVersion(Integer id,String version,String fileName, String filePath);

    OperatingSystem selectOSystemById(Integer id);

    List<OperatingSystem> selectAllOSystem(String name,String osCode);
    
    PageResult<OperatingSystem> selectPageOSystem(PageRequest pageRequest,String name,String osCode);

    int updateOSystem(OperatingSystem record);

    int deleteSoftwareById(Integer id);

    int insertSoftware(Software record);

    ResponseModel insertSoftwareVersion(Integer id,String version,String fileName, String filePath);

    Software selectSoftwareById(Integer id);

    List<Software> selectAllSoftware(String sCode,String name);
    
    PageResult<Software> selectPageSoftware(PageRequest pageRequest,String sCode,String name);

    int updateSoftware(Software record);

    ResponseModel uploadOSystem(MultipartFile file,String type);

    ResponseModel downloadOSystemFilegetSystemType();

    List<OperatingSystem> selectNoPageOSystem();

    List<Hardware> selectNoPageHardware(String type,Integer endTypeId);

    List<Software> selectPageSoftware();

    ResponseModel getAllEndType();

    int updateEdgeDeviceStatus(Integer cId, HardwareStstus hardware);

    ResponseModel updateHardwareConfigFile(Integer dId, MultipartFile file,String type);

    ResponseModel insertHardwareInstallImage(Integer cId, MultipartFile file,String type);

    List<DepartmentDevice> selectEdgeDeviceForCamera();

    ResponseModel updateHardwareConfigFileForLine(JSONObject jsonObject,Integer dId);
}

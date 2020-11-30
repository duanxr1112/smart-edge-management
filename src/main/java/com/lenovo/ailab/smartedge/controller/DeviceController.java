package com.lenovo.ailab.smartedge.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.dao.vo.CameraInstallStatusVo;
import com.lenovo.ailab.smartedge.dao.vo.EdgeServerDetail;
import com.lenovo.ailab.smartedge.dao.vo.EdgeServerInitVo;
import com.lenovo.ailab.smartedge.service.DepartmentService;
import com.lenovo.ailab.smartedge.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.service.EdgeDeviceService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/edgedevices")
public class DeviceController {

	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
	@Autowired
	EdgeDeviceService edgeDeviceService;
    @Autowired
    ResourceService resourceService;
	@Autowired
	private DepartmentService departmentService;


	/**
	 * @about 根据type显示配置单列表
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/type/{type}/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getPageEdgeDeviceByType(@PathVariable(value = "type", required = true) String type,
												  @PathVariable(value = "pagenum", required = false) Integer pageNum,
												  @PathVariable(value = "pagesize", required = false) Integer pageSize,
												  @Param(value = "name") String name) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<EdgeDevice> edgeDeviceList = edgeDeviceService.selectPageEdgeDeviceByType(type,pageQuery,name);
		String json = JSON.toJSONString(edgeDeviceList,SerializerFeature.DisableCircularReferenceDetect);
        JSONObject result = JSONObject.parseObject(json);
        return ResponseModel.ok(result);
	}

	/**
	 * @about save edgeDevice
	 * @param EdgeDevice
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertEdgeDevices(@RequestBody EdgeDevice EdgeDevice) {
		int id = edgeDeviceService.insertEdgeDevices(EdgeDevice);
		EdgeDevice.setId(id);
		return ResponseModel.ok(EdgeDevice);
	}

	/**
	 * @about update edgeDevice
	 * @param id
	 * @param EdgeDevice
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateEdgeDevice(@PathVariable(value = "id", required = true) Integer id, @RequestBody EdgeDevice EdgeDevice) {
		edgeDeviceService.updateEdgeDeviceById(EdgeDevice);
		return ResponseModel.ok(EdgeDevice);
	}

	/**
	 * @about select edgeDevice detail
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getEdgeDeviceInfo(@PathVariable(value = "id", required = true) Integer id) {
		EdgeDevice EdgeDevice = edgeDeviceService.selectEdgeDeviceById(id);
        String json = JSON.toJSONString(EdgeDevice,SerializerFeature.DisableCircularReferenceDetect);
        JSONObject result = JSONObject.parseObject(json);
		return ResponseModel.ok(result);
	}

	/**
	 * @about delete edgeDevice
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delEdgeDevice(@PathVariable(value = "id", required = true) Integer id) {
		int result = edgeDeviceService.deleteEdgeDeviceById(id);
		return ResponseModel.ok(result);
	}


    /**
     * @about upload file for edgeDevice thumbnail
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseModel upload(@RequestParam("file") MultipartFile file) {
        return resourceService.uploadOSystem(file,"edge_device");
    }

/*	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertEdgeDevice(@RequestBody EdgeDevice EdgeDevice, HttpServletRequest httpServletRequest) {
		int id = edgeDeviceService.insertEdgeDevice(EdgeDevice);
		EdgeDevice.setId(id);
		return ResponseModel.ok(EdgeDevice);
	}*/

	/**
	 * @about  edgeDevice drop-down
	 * @return
	 */
	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllEdgeDevice(@PathVariable(value = "type", required = true) String type) {
		List<EdgeDevice> EdgeDeviceList = edgeDeviceService.selectAllEdgeDevice(type);
		return ResponseModel.ok(EdgeDeviceList);
	}

	@RequestMapping(value = "/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getPageEdgeDevice(@PathVariable(value = "pagenum", required = false) Integer pageNum,
			@PathVariable(value = "pagesize", required = false) Integer pageSize,HttpServletRequest httpServletRequest) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<EdgeDevice> result = edgeDeviceService.selectPageEdgeDevice(pageQuery);
		return ResponseModel.ok(result);
	}

	@RequestMapping(value = "/{deviceId}/softwares", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertEdgeDeviceSoftware(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			@RequestBody EdgeDeviceSoftware record, HttpServletRequest httpServletRequest) {
		int id = edgeDeviceService.insertEdgeDeviceSoftware(record);
		return ResponseModel.ok(record);
	}

	@RequestMapping(value = "/{deviceId}/softwares4more", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertEdgeDeviceList(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			@RequestBody List<EdgeDeviceSoftware> softwareList, HttpServletRequest httpServletRequest) {
		for (EdgeDeviceSoftware software : softwareList) {
			edgeDeviceService.insertEdgeDeviceSoftware(software);
		}
		return ResponseModel.ok(softwareList);
	}

	@RequestMapping(value = "/{deviceId}/softwares", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllEdgeDeviceSoftware(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			HttpServletRequest httpServletRequest) {
		List<EdgeDeviceSoftware> list = edgeDeviceService.selectAllEdgeDeviceSoftwareByDeviceId(deviceId);
		return ResponseModel.ok(list);
	}

	@RequestMapping(value = "/{deviceId}/softwares/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getPageEdgeDeviceSoftware(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			@PathVariable(value = "pagenum", required = false) Integer pageNum,
			@PathVariable(value = "pagesize", required = false) Integer pageSize,
			HttpServletRequest httpServletRequest) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<EdgeDeviceSoftware> result = edgeDeviceService.selectPageEdgeDeviceSoftwareByDeviceId(deviceId,
				pageQuery);
		return ResponseModel.ok(result);
	}

	@RequestMapping(value = "/{deviceId}/softwares/{softwareId}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delEdgeDeviceSoftware(@PathVariable(value = "softwareId", required = true) Integer softwareId,
			@PathVariable(value = "deviceId", required = true) Integer deviceId,
			HttpServletRequest httpServletRequest) {
		int result = edgeDeviceService.deleteEdgeDeviceSoftwareById(deviceId, softwareId);
		return ResponseModel.ok(result);
	}

	@RequestMapping(value = "/{deviceId}/softwares/{softwareId}", method = RequestMethod.PUT, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateEdgeDeviceSoftware(
			@PathVariable(value = "softwareId", required = true) Integer softwareId,
			@PathVariable(value = "deviceId", required = true) Integer deviceId, @RequestBody EdgeDeviceSoftware record,
			HttpServletRequest httpServletRequest) {
		edgeDeviceService.updateEdgeDeviceSoftwareById(record);
		return ResponseModel.ok(record);
	}

	@RequestMapping(value = "/{deviceId}/hardwares", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertEdgeDeviceHardware(@RequestBody EdgeDeviceHardware edgeDeviceHardware,
			HttpServletRequest httpServletRequest) {
		int id = edgeDeviceService.insertEdgeDeviceHardware(edgeDeviceHardware);
		return ResponseModel.ok(edgeDeviceHardware);
	}

	@RequestMapping(value = "/{deviceId}/hardwares4more", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertEdgeDeviceHardware(@RequestBody List<EdgeDeviceHardware> list,
			HttpServletRequest httpServletRequest) {
		for (EdgeDeviceHardware hardware : list) {
			int id = edgeDeviceService.insertEdgeDeviceHardware(hardware);
		}
		return ResponseModel.ok(list);
	}

	@RequestMapping(value = "/{deviceId}/hardwares", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllEdgeDeviceHardware(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			HttpServletRequest httpServletRequest) {
		List<EdgeDeviceHardware> list = edgeDeviceService.selectAllEdgeDeviceHardware();
		return ResponseModel.ok(list);
	}

	@RequestMapping(value = "/{deviceId}/hardwares/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getPageEdgeDeviceHardware(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			@PathVariable(value = "pagenum", required = false) Integer pageNum,
			@PathVariable(value = "pagesize", required = false) Integer pageSize,
			HttpServletRequest httpServletRequest) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<EdgeDeviceHardware> result = edgeDeviceService.selectPageEdgeDeviceHardwareByDeviceId(deviceId,
				pageQuery);
		return ResponseModel.ok(result);
	}

	@RequestMapping(value = "/{deviceId}/hardwares/{id}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getEdgeDeviceHardwareInfo(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			@PathVariable(value = "id", required = true) Integer hardwareId,
			HttpServletRequest httpServletRequest) {
		EdgeDeviceHardware hardware = edgeDeviceService.selectEdgeDeviceHardwareById(hardwareId);
		return ResponseModel.ok(hardware);
	}

	@RequestMapping(value = "/{deviceId}/hardwares/{id}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delEdgeDeviceHardware(@PathVariable(value = "deviceId", required = true) Integer deviceId,
			@PathVariable(value = "id", required = true) Integer hardwareId, HttpServletRequest httpServletRequest) {
		int result = edgeDeviceService.deleteEdgeDeviceHardwareById(deviceId, hardwareId);
		return ResponseModel.ok(result);
	}

	/**
	 * @return 查询所有摄像头状态
	 */
	@RequestMapping(value = "/getInstallStatus/{departmentId}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllCameraInstallStatus(@PathVariable(value = "departmentId", required = true) Integer departmentId) {
		List<CameraInstallStatusVo> departmentList = departmentService.getAllCameraInstallStatus(departmentId);
		return ResponseModel.ok(departmentList);
	}

	/**
	 * @param deviceId
	 * @param imageId
	 * @return 人工审核安装图
	 */
	@RequestMapping(value = "/updateInstallStatus/{deviceId}/{status}/{imageId}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateCameraInstallStatus(@PathVariable(value = "deviceId", required = true) Integer deviceId,
												   @PathVariable(value = "status", required = true) Integer status,
												   @PathVariable(value = "imageId", required = true) String imageId) {
		return departmentService.updateCameraInstallStatus(deviceId,imageId,status);
	}

	/**
	 * @param departmentId
	 * @return 获取门店摄像头分布情况: 安装校对图，画线数据
	 */
	@RequestMapping(value = "/getAllDeviceStatus/{departmentId}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllDeviceDistribution(@PathVariable(value = "departmentId", required = true) Integer departmentId) {
        EquipmentDeploymentDetails allDeviceDistribution = departmentService.getAllDeviceDistribution(departmentId);
        return ResponseModel.ok(allDeviceDistribution);
	}

	/**
	 * @param departmentId
	 * @return 获取设备状态 tx2列表
	 */
	@RequestMapping(value = "/getAllDeviceStatus/deviceList/{departmentId}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDeviceList(@PathVariable(value = "departmentId", required = true) Integer departmentId) {
		List<EdgeServerDetail> edgeServerList = departmentService.getDeviceList(departmentId);
		return ResponseModel.ok(edgeServerList);
	}


    /**
     * @param deviceId
     * @return 查看模型与tx2的配置详情
     */
    @RequestMapping(value = "/getAllDeviceStatus/getConfig/{type}/{deviceId}", method = RequestMethod.GET, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel getConfig(@PathVariable(value = "deviceId", required = true) Integer deviceId,
                                    @PathVariable(value = "type", required = true) String type) {
        HashMap<String,Object> config = departmentService.getConfig(type,deviceId);
        return ResponseModel.ok(config);
    }


    /**
	 * @param deviceId
	 * @return 修改模型与tx2的配置详情
	 */
	@RequestMapping(value = "/getAllDeviceStatus/updateConfig/{type}/{storeId}/{deviceId}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateConfig(@PathVariable(value = "storeId", required = true) Integer storeId,
									   @PathVariable(value = "deviceId", required = true) Integer deviceId,
									   @PathVariable(value = "type", required = true) String type,
									   @RequestBody EdgeServerInitVo edgeServerInitvO) {
		if(StringUtils.isNotBlank(edgeServerInitvO.getContent())){
			return departmentService.updateConfig(type,storeId,deviceId,edgeServerInitvO);
		}
		return ResponseModel.fail("修改内容不可为空！", -1);
	}

	/**
	 * @param type
	 * @param version
	 * @param description
	 * @param file
	 * @return 上传工具包
	 */
	@RequestMapping(value = "/upload/installAndDeploy", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel uploadInstrument(@Param(value = "type") String type,
										   @Param("version") String version,
										   @Param("description") String description,
										   @RequestParam("file") MultipartFile file) {
		if(null != file && StringUtils.isNotBlank(type)){
			HardwareDeploymentFile hardwareDeploymentFile = new HardwareDeploymentFile();
			hardwareDeploymentFile.setType(type);
			hardwareDeploymentFile.setDescription(description);
			hardwareDeploymentFile.setVersion(version);
			return departmentService.uploadInstrument(hardwareDeploymentFile,file);
		}
		return ResponseModel.fail("上传文件或类型不可为空！", -1);
	}
    /**
     * @param type
     * @return
     */
    @RequestMapping(value = "/getInstallAndDeployInstrument/{type}", method = RequestMethod.GET, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel selectInstrument(@PathVariable(value = "type", required = true) String type) {
        return departmentService.selectInstrument(type);
    }

}

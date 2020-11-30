package com.lenovo.ailab.smartedge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.service.EdgeDeviceService;
import com.lenovo.ailab.smartedge.service.EdgeServiceImpl;
import com.lenovo.ailab.smartedge.service.ResourceService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.service.DepartmentService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;

@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/departments")
public class DepartmentController {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService departmentService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private EdgeServiceImpl edgeServiceImpl;
	@Autowired
	EdgeDeviceService edgeDeviceService;

	/**
	 * @about select department for page
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getPageDepartment(@PathVariable(value = "pagenum", required = false) Integer pageNum,
										    @PathVariable(value = "pagesize", required = false) Integer pageSize,
										    @Param(value = "name") String name, @Param(value = "dCode") String dCode) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<Department> result = departmentService.selectPageDepartment(pageQuery,name,dCode);
		return ResponseModel.ok(result);
	}

	/**
	 * @about department detail
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/noToken/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDepartmentInfo(@PathVariable(value = "id", required = true) Integer id) {
		Department department = departmentService.selectDepartmentById(id);
		return ResponseModel.ok(department);
	}

	/**
	 * @about department edgeDevice detail
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edgeDevice/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDepartmentEdgeDevicedetail(@PathVariable(value = "id", required = true) Integer id) {
		List<DepartmentDevice> department = departmentService.getDepartmentEdgeDevicedetail(id);
		return ResponseModel.ok(department);
	}

	/**
	 * @about department address drop-down
	 * @return
	 */
	@RequestMapping(value = "/address", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDepartmentAddress() {
		return  departmentService.getDepartmentAddress();
	}

	/**
	 * @about save department
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertDepartment(@RequestBody Department department) {
		department.insertGenerator();
		return departmentService.insertDepartment(department);
	}

	/**
	 * @about save department and edgeDevice
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "/{departmentid}/{type}/devices", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertDepartmentDevice(@PathVariable(value = "departmentid", required = true) Integer departmentId,
												 @PathVariable(value = "type", required = true) String type,
												 @RequestBody AddDepartmentDevice addDepartmentDevice) {
		int id = departmentService.insertDepartmentDevice(addDepartmentDevice.getAddDepartmentDeviceList(),addDepartmentDevice.getdId(),type);
		//departmentDevice.setId(id);
		return ResponseModel.ok(addDepartmentDevice);
	}

	/**
	 * @about select DepartmentDeviceTopology List
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllDepartment() {
		List<Department> departmentList = departmentService.selectAllDepartmentTopology();
		return ResponseModel.ok(departmentList);
	}

	/**
	 * @about delete department
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delDepartment(@PathVariable(value = "id", required = true) Integer id) {
		int result = departmentService.deleteDepartmentById(id);
		return ResponseModel.ok(result);
	}

    /**
     * @about upload file for department thumbnail
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseModel upload(@RequestParam("file") MultipartFile file) {
        return  resourceService.uploadOSystem(file,"department");
    }

    /**
     * @about 查看店铺下的配置单
     * @param departmentId
     * @param httpServletRequest
     * @return
     */
	@RequestMapping(value = "/{departmentid}/devices", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllDepartmentDevice(@PathVariable(value = "departmentid", required = true) Integer departmentId,
												HttpServletRequest httpServletRequest) {
		List<DepartmentDevice> list = departmentService.selectDepartmentDeviceBydepartmentId(departmentId);
		return ResponseModel.ok(list);
	}
	/**
	 * @about save departmentDeviceTopology
	 * @param departmentDeviceTopology
	 * @return
	 */
	@RequestMapping(value = "/{departmentid}/topologys", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertDepartmentTopology(@RequestBody DepartmentDeviceTopology departmentDeviceTopology) {
		return departmentService.insertDepartmentDeviceTopology(departmentDeviceTopology);
	}

	/**
	 * @about  Equipment details<回显设备详情>
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "/{departmentid}/topologys", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllEdgeDevice(@PathVariable(value = "departmentid", required = true) Integer departmentId) {
		List<DepartmentDevice> EdgeDeviceList = edgeDeviceService.selectEquipmentDetail(departmentId);
		JSONArray jsonArrayRtn= JSONArray.parseArray(JSON.toJSONString(EdgeDeviceList, SerializerFeature.DisableCircularReferenceDetect));
		return ResponseModel.ok(jsonArrayRtn);
	}
	/**
	 * @about get DepartmentDeviceTopology detail
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "/{departmentid}/topologys/equipment", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDepartmentTopologyInfo(@PathVariable(value = "departmentid", required = true) Integer departmentId) {
		DepartmentDeviceTopology departmentTopology = departmentService.selectDepartmentDeviceTopologyById(departmentId);
		return ResponseModel.ok(departmentTopology);
	}

	/**
	 * @about department detail for btit code
	 * @return
	 */
	@RequestMapping(value = "/departmentDetail/{type}/{tenant}/{shopCode}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDepartmentInfo(@PathVariable(value = "tenant", required = true) Integer tenant,
										    @PathVariable(value = "shopCode", required = true) String shopCode,
										    @PathVariable(value = "type", required = true) String type) {
		String newCode = tenant + "-" + shopCode;
		Department department = departmentService.selectByBtitDepartmentCode(newCode,type);
		return ResponseModel.ok(department);
	}

	/**
	 * @param pageNum
	 * @param pageSize
	 * @return 设备列表 for btit code
	 */
	@RequestMapping(value = "/getDeviceList/{type}/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDeviceListForPage(@PathVariable(value = "pagenum", required = false) Integer pageNum,
											   @PathVariable(value = "pagesize", required = false) Integer pageSize,
											   @PathVariable(value = "type", required = false) String type,
                                               @Param(value = "tenant") Integer tenant,
                                               @Param(value = "shopCode") String shopCode,@Param(value = "status") String status) {

		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);

		PageResult<DepartmentDeviceForBtit> result = departmentService.getDeviceListForPage(pageQuery,tenant,shopCode,status,type);
		return ResponseModel.ok(result);
	}

	/*@PostMapping(value = "/{id}/bgimage/upload")
	public ResponseModel fileUpload(@PathVariable(value = "id", required = true) Integer id, @RequestParam(value = "file") MultipartFile file,  HttpServletRequest request) {
		if (file.isEmpty()) {
			logger.error("文件为空");
		}
		String fileName = file.getOriginalFilename();// 文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		if(!("jpg".endsWith(suffixName.toLowerCase())||"jpeg".endsWith(suffixName.toLowerCase()))){
			logger.error("please upload JPG/JPEG Image");
		}
		Department department=departmentService.selectDepartmentById(id);
		if(department!=null){
			fileName = department.getdCode() + suffixName; // 新文件名
			File dest = new File(DEPARTMENT_FILE_PATH + fileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {
				file.transferTo(dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ResponseModel.ok(1);
	}*/
/*	@RequestMapping(value = "/{departmentid}/devices4more", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertDepartmentDeviceList(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@RequestBody List<DepartmentDevice> departmentDeviceList, HttpServletRequest httpServletRequest) {
		for (DepartmentDevice departmentDevice : departmentDeviceList) {
			int id = departmentService.insertDepartmentDevice(departmentDevice);
			departmentDevice.setdId(id);
		}
		return ResponseModel.ok(departmentDeviceList);
	}*/

	/**
	 * @param id
	 * @param department
	 * @return  put department
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateDepartment(@PathVariable(value = "id", required = true) Integer id, @RequestBody Department department) {
		departmentService.updateDepartmentById(department);
		return ResponseModel.ok(department);
	}

	@RequestMapping(value = "/{departmentid}/devices/type/{type}/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getPageDepartmentDevice(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@PathVariable(value = "type", required = true) String type,
			@PathVariable(value = "pagenum", required = false) Integer pageNum,
			@PathVariable(value = "pagesize", required = false) Integer pageSize,
			HttpServletRequest httpServletRequest) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<DepartmentDevice> result = departmentService.selectPageDepartmentDevice(departmentId, type,
				pageQuery);
		return ResponseModel.ok(result);
	}

	@RequestMapping(value = "/{departmentid}/devices/{deviceid}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDepartmentDeviceInfo(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@PathVariable(value = "deviceid", required = true) Integer deviceId,
			HttpServletRequest httpServletRequest) {
		DepartmentDevice departmentDevice = departmentService.selectDepartmentDeviceById(deviceId);
		return ResponseModel.ok(departmentDevice);
	}

	@RequestMapping(value = "/{departmentid}/devices/{deviceid}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delDepartmentDevice(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@PathVariable(value = "deviceid", required = true) Integer deviceId,
			HttpServletRequest httpServletRequest) {
		int result = departmentService.deleteDepartmentDevice(deviceId);
		return ResponseModel.ok(result);
	}
	@RequestMapping(value = "/{departmentid}/devices", method = RequestMethod.PUT, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateDepartmentDevice(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@RequestBody DepartmentDevice departmentDevice) {
		departmentService.updateDepartmentDeviceById(departmentDevice);
		return ResponseModel.ok(departmentDevice);
	}
/*	@RequestMapping(value = "/{departmentid}/topologys", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllDepartmentTopology(@PathVariable(value = "departmentid", required = true) Integer departmentId) {
		List<DepartmentDeviceTopology> list = departmentService.selectAllDepartmentTopologyByDepartmentId(departmentId);
		return ResponseModel.ok(list);
	}*/

	@RequestMapping(value = "/{departmentid}/topologys/{topologyid}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delDepartmentTopology(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@PathVariable(value = "topologyid", required = true) Integer topologyId,
			HttpServletRequest httpServletRequest) {
		int result = departmentService.deleteDepartmentDeviceTopologyById(topologyId);
		return ResponseModel.ok(result);
	}

	@RequestMapping(value = "/{departmentid}/topologys/{deviceid}", method = RequestMethod.PUT, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateDepartmentTopologys(
			@PathVariable(value = "departmentid", required = true) Integer departmentId,
			@PathVariable(value = "deviceid", required = true) Integer deviceId,
			@RequestBody DepartmentDeviceTopology departmentTopology, HttpServletRequest httpServletRequest) {
		departmentService.updateDepartmentDeviceTopologyById(departmentTopology);
		return ResponseModel.ok(departmentTopology);
	}

    /**
     * @param departmentId
     * @param httpServletRequest
     * @return
     * @about get DepartmentsConfig File For Camera
     */
    @RequestMapping(value = "/{departmentId}/config/camera", method = RequestMethod.GET, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel getDepartmentsConfigForCamera(@PathVariable(value = "departmentId", required = true) Integer departmentId,
                                                       HttpServletRequest httpServletRequest) {
    	return departmentService.getDepartmentsConfigForCamera(departmentId);
    }

	/**
	 * @param departmentId
	 * @param httpServletRequest
	 * @return
	 * @about get DepartmentsConfig File For Camera
	 */
	@RequestMapping(value = "/getDeviceDeploymentFile/{departmentId}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDeviceDeploymentFileForCamera(@PathVariable(value = "departmentId", required = true) Integer departmentId,
													   HttpServletRequest httpServletRequest) {
		return departmentService.getDepartmentsConfigForCamera(departmentId);
	}

    /**
     * @param httpServletRequest
     * @return
     * @about get All DepartmentsConfig File For Camera
     */
    @RequestMapping(value = "/getDeviceDeploymentFile/all", method = RequestMethod.GET, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel getAllDepartmentsConfigForCamera(HttpServletRequest httpServletRequest) {
        List<HardwareDeploymentFile> allDepartmentsConfigForCamera = departmentService.getAllDepartmentsConfigForCamera();
        return ResponseModel.ok(allDepartmentsConfigForCamera);
    }

	/**
	 * @about select all department for nopage
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllDepartmentForNoPage() {
        List<Department> departments = departmentService.selectAllDepartment();
        return ResponseModel.ok(departments);
	}

	/**
	 * @about select all department for nopage
	 * @return
	 */
	@RequestMapping(value = "/smartStore/getDepartment", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllDepartmentForSmartStore() {
		List<Department> departments = departmentService.selectAllDepartment();
		return ResponseModel.ok(departments);
	}

	/**
	 * @param departmentId
	 * @return 获取设备类型
	 */
	@RequestMapping(value = "/getDeviceUseType/{departmentId}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDeviceUseType(@PathVariable(value = "departmentId", required = true) Integer departmentId) {
		List<DepartmentDeviceForUseType> departmentDevice = departmentService.selectCameraDepartmentById(departmentId);
		return ResponseModel.ok(departmentDevice);
	}

	/**
	 * @return 获取设备类型
	 */
	@RequestMapping(value = "/getDeviceUseType/byDepartmentCode/{departmentCode}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getDeviceUseTypeByCode(@PathVariable(value = "departmentCode", required = true) String departmentCode) {
		List<DepartmentDeviceForUseType> departmentDevice = departmentService.selectCameraDepartmentByCode(departmentCode);
		return ResponseModel.ok(departmentDevice);
	}

    /**
     * @about 更新摄像头的用途类型
     * @param departmentId
     * @return
     */
    @RequestMapping(value = "/updateDeviceUseType/{departmentId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel updateDeviceUseType(@PathVariable(value = "departmentId", required = true) Integer departmentId,
                                             @RequestBody DepartmentDeviceForUseType departmentDeviceForUseType) {
    	if(null != departmentDeviceForUseType.getDepartmentDeviceForUseTypes() && departmentDeviceForUseType.getDepartmentDeviceForUseTypes().size() > 0){
			return departmentService.updateDeviceUseType(departmentId, departmentDeviceForUseType.getDepartmentDeviceForUseTypes());
		}
		return ResponseModel.fail("useType is null",1);
    }

	/**
	 * @about 删除摄像头
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/updateDeviceUseType/{deviceId}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel deleteDeviceForCamera(@PathVariable(value = "deviceId", required = true) Integer deviceId) {

		return departmentService.deleteDeviceForCamera(deviceId);
	}

	/**
	 * @param departmentId
	 * @param cameraCoordinates
	 * @return 上传门店摄像头部署坐标箭头指示
	 */
	@RequestMapping(value = "/uploadCoordinates/{departmentId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel uploadDeviceForCameraCoordinates(@PathVariable(value = "departmentId", required = true) Integer departmentId,
														   @PathVariable(value = "deviceId", required = true) Integer deviceId,
														   @RequestBody CameraCoordinatesTwo cameraCoordinates) {
		if(null != cameraCoordinates){
			return departmentService.uploadDeviceForCameraCoordinates(departmentId,cameraCoordinates,deviceId);
		}
		return ResponseModel.fail("param is not null ",1);

	}

	@RequestMapping(value = "/log/{departmentId}/{startTime}/{endTime}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getLog(@PathVariable(value = "departmentId", required = true) Integer departmentId,
								@PathVariable(value = "startTime", required = true) String startTime,
								@PathVariable(value = "endTime", required = true) String endTime) {

		return ResponseModel.ok(departmentService.getLog(departmentId,startTime,endTime));

	}
}

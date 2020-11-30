package com.lenovo.ailab.smartedge.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.lenovo.ailab.smartedge.dao.po.DepartmentDevice;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lenovo.ailab.smartedge.dao.po.Hardware;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.service.ResourceService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/resource/hardwares")
public class ResourceOfHardwareController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceOfHardwareController.class);
	@Autowired
	ResourceService resourceService;

	/**
	 * @about save hardware
	 * @param hardware
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertHardware(@RequestBody @Valid Hardware hardware, HttpServletRequest httpServletRequest) {

		hardware.insertGenerator();
		int result = resourceService.insertHardware(hardware);

		return ResponseModel.ok(hardware);
	}

	/**
	 * @about select hardware
	 * @param pageNum
	 * @param pageSize
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllHardware(@PathVariable(value = "pagenum", required = false) Integer pageNum,
										@PathVariable(value = "pagesize", required = false) Integer pageSize,
										@Param(value = "hCode") String hCode, @Param(value = "name") String name,
										HttpServletRequest httpServletRequest) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<Hardware> result = resourceService.selectPageHardware(pageQuery, hCode, name);
		return ResponseModel.ok(result);
	}


	/**
	 * @about select hardware drop-down list
	 * @return
	 */
	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllHardwares(@PathVariable(value = "type", required = false) String type) {

		List<Hardware> result = resourceService.selectNoPageHardware(type,null);

		return ResponseModel.ok(result);
	}

	/**
	 * @about select end type
	 * @return
	 */
	@RequestMapping(value = "/end/type", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllEndType() {
		ResponseModel result = resourceService.getAllEndType();

		return result;
	}

	/**
	 * @about  边缘节点和智能硬件根据type获取列表
	 * @param pageNum
	 * @param pageSize
	 * @param type
	 * @param hCode
	 * @param name
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/type/{type}/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllHardware(@PathVariable(value = "pagenum", required = false) Integer pageNum,
										@PathVariable(value = "pagesize", required = false) Integer pageSize,
										@PathVariable(value = "type", required = true) String type,
										@Param(value = "hCode") String hCode, @Param(value = "name") String name,
										HttpServletRequest httpServletRequest) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<Hardware> result = resourceService.selectPageHardwareByType(type, hCode, name, pageQuery);
		return ResponseModel.ok(result);
	}

	/**
	 * @about hardware detail
	 * @param id
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/{type}/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getHardwareInfo(@PathVariable(value = "id", required = true) Integer id,
										  @PathVariable(value = "type", required = true) String type,
										   HttpServletRequest httpServletRequest) {

		Hardware hardware = resourceService.selectHardwareById(type,id);

		return ResponseModel.ok(hardware);
	}

	/**
	 * @about delete hardware
	 * @param id
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delHardware(@PathVariable(value = "id", required = true) Integer id,
			HttpServletRequest httpServletRequest) {

		int result = resourceService.deleteHardwareById(id);

		return ResponseModel.ok(result);
	}

	/**
	 * @about update hareware
	 * @param id
	 * @param hardware
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateHardware(@PathVariable(value = "id", required = true) Integer id,
										 @RequestBody @Valid Hardware hardware) {
		hardware.setId(id);
		resourceService.updateHardware(hardware);

		return ResponseModel.ok(hardware);
	}

	/**
	 * @param id
	 * @param version
	 * @param httpServletRequest
	 * @return
	 * @about upgrade verion for osystem
	 */
	@RequestMapping(value = "/{id}/addversion/{version}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertHardwareVersion(@PathVariable(value = "id", required = true) Integer id,
											    @PathVariable(value = "version", required = true) String version, HttpServletRequest httpServletRequest) {

		int result = resourceService.insertHardwareVersion(id, version);

		return ResponseModel.ok(result);
	}

    /**
     * @param cId
     * @param hardware
     * @return
     * @about update camera status
     */
   /* @RequestMapping(value = "/config/camera/{cId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel insertHardware(@PathVariable(value = "cId", required = true) Integer cId,
                                        @RequestBody @Valid HardwareStstus hardware) {
        int result = resourceService.updateEdgeDeviceStatus(cId,hardware);
        return ResponseModel.ok(result);
    }*/

	/**
	 * @param dId : departmentId
	 * @param file
	 * @return
	 * @about update camera deploymentFile
	 */
	@RequestMapping(value = "/config/camera/{dId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateHardwareConfigFile(@PathVariable(value = "dId", required = true) Integer dId,
												   @RequestParam("file") MultipartFile file) {
        ResponseModel responseModel = resourceService.updateHardwareConfigFile(dId,file,"deploy");
        return responseModel;
	}

	/**
	 * @param jsonObject
	 * @return  上传划线数据by部署工具
	 */
	@RequestMapping(value = "/config/camera/lineation/{dId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateHardwareConfigFileForLine(@RequestBody JSONObject jsonObject,
	@PathVariable(value = "dId",required = true) Integer dId) {
		return resourceService.updateHardwareConfigFileForLine(jsonObject,dId);
	}

	/**
	 * @about all hardware list
	 * @return
	 */
	@RequestMapping(value = "/camera/all", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getHardwareInfo() {

        List<DepartmentDevice> departmentDeviceList = resourceService.selectEdgeDeviceForCamera();

        return ResponseModel.ok(departmentDeviceList);
	}

	/**
	 * @param cId
	 * @param file
	 * @return  上传摄像头校对图片
	 * @about insertHardwareInstallImage for camera
	 */
	@RequestMapping(value = "/installImage/{type}/{cId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertHardwareInstallImage(@PathVariable(value = "cId", required = true) Integer cId,
													@PathVariable(value = "type", required = true) String type,
												  @RequestParam("file") MultipartFile file) {
		ResponseModel responseModel = resourceService.insertHardwareInstallImage(cId,file,type);
		return responseModel;
	}
}

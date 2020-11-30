package com.lenovo.ailab.smartedge.controller;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lenovo.ailab.smartedge.dao.po.OperatingSystem;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.service.ResourceService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/resource/osystems")
public class ResourceOfSystemController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceOfSystemController.class);

	@Autowired
	ResourceService resourceService;

	/**
	 * @about save OSystem
	 * @param osystem
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertOSystem(@RequestBody @Valid OperatingSystem osystem) {
		int result = resourceService.insertOSystem(osystem);
		osystem.setId(result);
		return ResponseModel.ok(osystem);
	}

    /**
     * @about upload file for OSystem
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseModel upload(@RequestParam("file") MultipartFile file) {

       return resourceService.uploadOSystem(file,"OSystem");
    }

    /**
     * @about select OSystem type
     * @param
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel getSystemType() {

        return  resourceService.downloadOSystemFilegetSystemType();
    }

	/**
	 * @about upgrade OSystem
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/addversion", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel upgradeOSystem(@PathVariable(value = "id", required = true) Integer id,
										@RequestBody OperatingSystem osystem) {
		int result = resourceService.insertOSystemVersion(id, osystem.getVersion(),osystem.getFileName(),osystem.getFilePath());

		return ResponseModel.ok(result);
	}

	/**
	 * @about select OSystem
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllOSystem(@PathVariable(value = "pagenum", required = false) Integer pageNum,
									   @PathVariable(value = "pagesize", required = false) Integer pageSize,
									   @Param(value = "name") String name, @Param(value = "osCode") String osCode) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);
		PageResult<OperatingSystem> result = resourceService.selectPageOSystem(pageQuery,name,osCode);
		return ResponseModel.ok(result);
	}

	/**
	 * @about select OSystem for drop-down list
	 * @param
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllOSystems() {
		List<OperatingSystem> result = resourceService.selectNoPageOSystem();
		return ResponseModel.ok(result);
	}

	/**
	 * @about OSystem detail
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getOSystemInfo(@PathVariable(value = "id", required = true) Integer id) {
		OperatingSystem operatingSystem = resourceService.selectOSystemById(id);
		return ResponseModel.ok(operatingSystem);
	}

    /**
     * @about delete OSystem
     * @param id
     * @return
     */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delOSystem(@PathVariable(value = "id", required = true) Integer id) {
		int result = resourceService.deleteOSystemById(id);
		return ResponseModel.ok(result);
	}

	/**
	 * @about update OSystem
	 * @param id
	 * @param OSystem
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateOSystem(@PathVariable(value = "id", required = true) Integer id,
									    @RequestBody @Valid OperatingSystem OSystem) {
		int result = resourceService.updateOSystem(OSystem);
		return ResponseModel.ok(result);
	}

}

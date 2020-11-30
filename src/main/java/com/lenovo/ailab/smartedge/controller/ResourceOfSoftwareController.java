package com.lenovo.ailab.smartedge.controller;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lenovo.ailab.smartedge.dao.po.Software;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.service.ResourceService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/resource/softwares")
public class ResourceOfSoftwareController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceOfSoftwareController.class);
	@Autowired
	ResourceService resourceService;

	/**
	 * @about save software
	 * @param software
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertSoftware(@RequestBody @Valid Software software) {
		int id = resourceService.insertSoftware(software);
		software.setId(id);
		return ResponseModel.ok(software);
	}

	/**
	 * @about upgrade software
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/addversion", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertSoftware(@PathVariable(value = "id", required = true) Integer id,
										 @RequestBody Software software) {
		return resourceService.insertSoftwareVersion(id, software.getVersion(), software.getFileName(), software.getFilePath());
	}

	/**
	 * @about select software
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/pnum/{pagenum}/psize/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllSoftware(@PathVariable(value = "pagenum", required = false) Integer pageNum,
                                         @PathVariable(value = "pagesize", required = false) Integer pageSize,
                                         @Param(value = "sCode") String sCode,@Param(value = "name") String name) {
		PageRequest pageQuery = new PageRequest();
		pageQuery.setPageNum(pageNum == null ? 0 : pageNum);
		pageQuery.setPageSize(pageSize == null ? 10 : pageSize);

		PageResult<Software> result = resourceService.selectPageSoftware(pageQuery,sCode,name);
		return ResponseModel.ok(result);
	}


	/**
	 * @about select software drop-down list
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getAllSoftwares() {
		List<Software> result = resourceService.selectPageSoftware();
		return ResponseModel.ok(result);
	}


	/**
	 * @about software detail
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getSoftwareInfo(@PathVariable(value = "id", required = true) Integer id) {
		Software software = resourceService.selectSoftwareById(id);
		return ResponseModel.ok(software);
	}

	/**
	 * @about delate software
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel delSoftware(@PathVariable(value = "id", required = true) Integer id) {
		int result = resourceService.deleteSoftwareById(id);
		return ResponseModel.ok(result);
	}

	/**
	 * @about update software
	 * @param id
	 * @param software
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateSoftware(@PathVariable(value = "id", required = true) Integer id, @RequestBody @Valid Software software) {
		resourceService.updateSoftware(software);
		return ResponseModel.ok(software);
	}

	/**
	 * @about upload file for software
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	public ResponseModel upload(@RequestParam("file") MultipartFile file) {
		return  resourceService.uploadOSystem(file,"software");
	}
}

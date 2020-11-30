package com.lenovo.ailab.smartedge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lenovo.ailab.smartedge.dao.po.EdgeDeviceConfig;
import com.lenovo.ailab.smartedge.dao.po.ModelConfigLog;
import com.lenovo.ailab.smartedge.dao.vo.EdgeDeviceStatusVo;
import com.lenovo.ailab.smartedge.dao.vo.EdgeServerDetail;
import com.lenovo.ailab.smartedge.dao.vo.EdgeServerInitVo;
import com.lenovo.ailab.smartedge.dao.vo.ServerStatus;
import com.lenovo.ailab.smartedge.service.EdgeDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lenovo.ailab.smartedge.dao.po.Department;
import com.lenovo.ailab.smartedge.dao.po.DepartmentDevice;
import com.lenovo.ailab.smartedge.service.DepartmentService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;

/**
 * Title: HeartBeatController.java
 * @Autohr "chenpeng"
 * @data 2019年12月6日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/heartbeat")
public class HeartBeatController {
	
	private static final Logger logger = LoggerFactory.getLogger(HeartBeatController.class);
	@Autowired
	DepartmentService departmentService;
    @Autowired
    EdgeDeviceService edgeDeviceService;
	
	@RequestMapping(value = "/{dcode}/{ecode}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel heartbeat(@PathVariable(value = "dcode", required = true) String dcode,
			@PathVariable(value = "ecode", required = true) String ecode,HttpServletRequest httpServletRequest) {
		
		Department department=departmentService.selectDepartmentByCode(dcode);
		if(department!=null){
			List<DepartmentDevice> deviceList=department.getDepartmentDeviceList();
			for(DepartmentDevice device:deviceList){
				if(device.getEdgeDevice()!=null && ecode.equals(device.getEdgeDevice().geteCode())){
				//	logger.error("{}-{},heartbeat success!",dcode,ecode);
					return ResponseModel.ok(device.getEdgeDevice().getSoftwareList());
				}
			}
		}
		return ResponseModel.fail("", -1);
	}

	/**
	 * @param dId
	 * @param httpServletRequest
	 * @return
	 * @about department edgeDevice is normal
	 */
	@RequestMapping(value = "/{dId}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel heartbeatIsNormal(@PathVariable(value = "dId", required = true) Integer dId, HttpServletRequest httpServletRequest) {
       // logger.info("department dId is {} , heartbeat detection start ", dId );

		List<EdgeDeviceStatusVo>  departmentDevices = departmentService.selectEquipmentDetail(dId);

		return ResponseModel.ok(departmentDevices);
	}

    /**
     * @param departmentDevice
     * @return 心跳机制<每一分钟接收一次>
     */
	@RequestMapping(value = "/getHeartbeat", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel heartbeatIsNormal(@RequestBody EdgeDeviceStatusVo departmentDevice) {
		//logger.info("departmentDeviceCodeList is {} , heartbeat detection start ", departmentDevice.getEdgeDeviceStatusVoList() );
        if(departmentDevice.getEdgeDeviceStatusVoList().size() > 0 && null != departmentDevice.getEdgeDeviceStatusVoList()){
           return  departmentService.whetherDepartmentDeviceIsNormal(departmentDevice.getEdgeDeviceStatusVoList());
        }
		return ResponseModel.fail("jsonParam not is null",-1);
	}

	/**
	 * @return  controlService 发送消息说明agent 已上线
	 */
	@RequestMapping(value = "/notice/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getNoticeForAgent(@PathVariable(value = "storeId", required = true) Integer storeId,
										   @PathVariable(value = "deviceId", required = true) Integer deviceId) {

		//logger.info("get Notice For Agent , departmentId is {} , deviceId is {} ", storeId, deviceId );

		return departmentService.getNoticeForAgent(storeId,deviceId);
	}

	/**
	 * @param serverStatus
	 * @return  controlService 发送消息 - 监控服务(模型)的状态
	 */
	@RequestMapping(value = "/serverStatus", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel getHeartForServer(@RequestBody ServerStatus serverStatus) {

	//	logger.info("get Server Status is {}" ,serverStatus.getServerStatusList());

		if(serverStatus.getServerStatusList().size() >0 && null != serverStatus.getServerStatusList()){
			return departmentService.getHeartForServer(serverStatus);
		}
		return ResponseModel.fail("serverHeartList is null",1);
	}

	/**
	 * @param software
	 * @param storeId
	 * @param deviceId
	 * @return 手动重启模型
	 */
/*	@RequestMapping(value = "/reStart/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel reStartToContrlServer(@RequestBody Software software, @PathVariable(value = "storeId", required = true) Integer storeId,
											    @PathVariable(value = "deviceId", required = true) Integer deviceId) {

		if(StringUtils.isNotBlank(software.getName())){
			return departmentService.reStartToContrlServer(software.getName(),storeId,deviceId);
		}
		return ResponseModel.fail("name is null",-1);
	}*/

	/**
	 * @param storeId
	 * @param deviceId
	 * @return 手动重启tx2
	 */
	@RequestMapping(value = "/reStartTx2/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel reStartTx2(@PathVariable(value = "storeId", required = true) Integer storeId,
											   @PathVariable(value = "deviceId", required = true) Integer deviceId) {
		return departmentService.reStartTx2(storeId,deviceId);
	}

	/**
	 * @param storeId
	 * @param deviceId
	 * @return 模型升级
	 */
	@RequestMapping(value = "/upgradeVersion/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel upgradeVersionForModel( @RequestBody EdgeServerInitVo edgeServerInitvO,
												 @PathVariable(value = "storeId", required = true) Integer storeId,
												 @PathVariable(value = "deviceId", required = true) Integer deviceId,
												 HttpServletResponse response) {

		if(edgeServerInitvO.getEdgeServerInitList().size() > 0 && null != edgeServerInitvO.getEdgeServerInitList()){
			return departmentService.upgradeVersionForModel(storeId,deviceId,edgeServerInitvO,response);
		}
		return ResponseModel.fail("name is null",-1);
	}

    /**
     * @param btitdCode
     * @param deviceCode
     * @return 根据tx2_id 与 门店id 查询门店下设备详情
     */
    @RequestMapping(value = "/getDeviceDetail/{btitdCode}/{deviceCode}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel selectDeviceByTx2IdAndDepartmentId(@PathVariable(value = "btitdCode", required = true) String btitdCode,
                                                             @PathVariable(value = "deviceCode", required = true) String deviceCode) {

        return edgeDeviceService.selectDeviceByTx2IdAndDepartmentId(btitdCode,deviceCode);
    }

	/**
	 * @param storeId
	 * @param deviceId
	 * @return 手动修改进店线配置
	 */
	@RequestMapping(value = "/updateLineIntotheStore/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateLineIntotheShop(@PathVariable(value = "storeId", required = true) Integer storeId,
											    @PathVariable(value = "deviceId", required = true) Integer deviceId,
											    @RequestBody EdgeServerInitVo edgeServerInitvO) {

		return departmentService.updateLineIntotheShop(storeId,deviceId,edgeServerInitvO);
	}

	/**
	 * @param storeId
	 * @param deviceId
	 * @return 手动init
	 */
	@RequestMapping(value = "/initModel/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel init(@PathVariable(value = "storeId", required = true) Integer storeId,
							   @PathVariable(value = "deviceId", required = true) Integer deviceId,
							   @RequestBody EdgeServerInitVo edgeServerInitvO, HttpServletResponse response) {

		return departmentService.init(storeId,deviceId,edgeServerInitvO,response);
	}

	/**
	 * @param storeId
	 * @param deviceId
	 * @return 手动启动模型
	 */
	@RequestMapping(value = "/runModel/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel run(@PathVariable(value = "storeId", required = true) Integer storeId,
                              @RequestBody EdgeServerInitVo edgeServerInitvO,
							  @PathVariable(value = "deviceId", required = true) Integer deviceId) {

		return departmentService.run(storeId,deviceId,edgeServerInitvO);
	}

	/**
	 * @param storeId
	 * @param deviceId
	 * @return 手动停止模型
	 */
	@RequestMapping(value = "/stopModel/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel stopModel(@PathVariable(value = "storeId", required = true) Integer storeId,
							 @RequestBody EdgeServerInitVo edgeServerInitvO,
							 @PathVariable(value = "deviceId", required = true) Integer deviceId) {

		return departmentService.stopModel(storeId,deviceId,edgeServerInitvO);
	}

    /**
     * @param storeId
     * @param deviceId
     * @return 手动删除模型
     */
    @RequestMapping(value = "/deleteModel/{storeId}/{deviceId}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel deleteModel(@PathVariable(value = "storeId", required = true) Integer storeId,
									 @RequestBody EdgeServerInitVo edgeServerInitvO,
                                   @PathVariable(value = "deviceId", required = true) Integer deviceId) {

        return departmentService.deleteModel(storeId,deviceId,edgeServerInitvO);
    }

	/**
	 * @param deviceId
	 * @return 添加模型实例
	 */
	@RequestMapping(value = "/addModel/{departmentId}/{deviceId}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel addModelConfig(@PathVariable(value = "departmentId", required = true) Integer departmentId,
										@PathVariable(value = "deviceId", required = true) Integer deviceId,
										@RequestBody EdgeServerDetail softwareConfig) {
		return departmentService.addModelConfig(softwareConfig,departmentId,deviceId);
	}

	/**
	 * @param deviceId
	 * @return 更新tx2配置
	 */
	@RequestMapping(value = "/updateEdgeDeviceConfig/{deviceCode}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel updateEdgeDeviceConfig(@PathVariable(value = "deviceCode", required = true) String deviceId,
										          @RequestBody EdgeDeviceConfig edgeDeviceConfig) {
		return departmentService.updateEdgeDeviceConfig(edgeDeviceConfig,deviceId);
	}

	/**
	 * @param deviceId
	 * @param storeId
	 * @return 接受模型日志
	 */
	@RequestMapping(value = "/insertModelLog/{storeId}/{deviceId}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseModel insertModelConfigLog(@PathVariable(value = "storeId", required = true) Integer storeId,
										    @PathVariable(value = "deviceId", required = true) Integer deviceId,
										    @RequestBody ModelConfigLog modelConfigLog) {
		return departmentService.insertModelConfigLog(modelConfigLog,storeId,deviceId);
	}
}

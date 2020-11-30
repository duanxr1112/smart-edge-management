package com.lenovo.ailab.smartedge.service;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.ailab.smartedge.common.CommonParams;
import com.lenovo.ailab.smartedge.config.CompressionFile;
import com.lenovo.ailab.smartedge.dao.*;
import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.dao.vo.*;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;


/**
 * Title: EdgeServiceImpl.java
 * 
 * @Autohr "chenpeng"
 * @data 2019年12月4日
 * @Email chenpeng10@lenovo.com
 * @description:
 **/
@Service
@CacheConfig(cacheNames = "edge")
public class EdgeServiceImpl implements EdgeDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(EdgeServiceImpl.class);
	@Autowired
	EdgeDeviceMapper edgeDeviceMapper;
	@Autowired
	EdgeDeviceSoftwareMapper deviceSoftwareMapper;
	@Autowired
	SoftwareMapper softwareMapper;
	@Autowired
	EdgeDeviceHardwareMapper deviceHardwareMapper;
	@Autowired
	ResourceService resourceService;
    @Autowired
    DepartmentMapper departmentMapper;
	@Autowired
	DepartmentDeviceMapper deviceMapper;
	@Autowired
    OperatingSystemMapper operatingSystemMapper;
	@Autowired
    DepartmentDeviceTopologyMapper departmentDeviceTopologyMapper;
	@Autowired
    DepartmentServiceImpl departmentServiceImpl;
    @Value("${controlServer.caremaVideoStream}")
    String CONTROL_SERVER_CAREMAVIDEOSTREAM;

    /**
     * @about delete edgeDevice
     * @param id
     * @return
     */
	@Override
	//@Caching(evict={@CacheEvict(cacheNames="device", key = "#id"),@CacheEvict(cacheNames="allDevice",allEntries=true)})
	@Transactional(rollbackFor = Exception.class)
	public int deleteEdgeDeviceById(Integer id) {

        int result = edgeDeviceMapper.deleteByPrimaryKey(id);
        int deviceHardware = deviceHardwareMapper.deleteEdgeDeviceHardwareById(id);
        int deviceSoftware = deviceSoftwareMapper.deleteEdgeDeviceSoftwareById(id);

        if(deviceHardware < 0 || deviceSoftware < 0 || result < 0){
            logger.info("delete deleteEdgeDeviceById Likely to fail");
        }
        return result;
	}

	@Override
	//@CacheEvict(cacheNames="allDevice",allEntries=true)
	public int insertEdgeDevice(EdgeDevice record) {
		return edgeDeviceMapper.insert(record);
	}

	/**
	 * @about save edgeDevices
	 * @param record
	 * @return
	 */
	@Override
	//@CacheEvict(cacheNames="allDevice",allEntries=true)
    @Transactional(rollbackFor = Exception.class)
	public int insertEdgeDevices(EdgeDevice record) {
        int deviceHardware = 0;
        int deviceSoftware = 0;
		record.insertGenerator();
        // 1. 添加边缘设备
        Integer typeId = 1;
        List<EdgeDevice> edgeDevices = edgeDeviceMapper.selectEdgeDeviceByType(record.getType());
        if(edgeDevices.size() > 0 && null != edgeDevices ){
             typeId = typeId + edgeDevices.get(0).getTypeId();
        }
        record.setTypeId(typeId);
        int insert = edgeDeviceMapper.insert(record);
        // 2. 添加软件/硬件
        EdgeDeviceHardware edgeDeviceHardware = new EdgeDeviceHardware();
        edgeDeviceHardware.sethId(record.gethId());
        edgeDeviceHardware.seteId(record.getId());
        deviceHardware = deviceHardwareMapper.insert(edgeDeviceHardware);
        if(null != record.getsId()){
			for (int i = 0; i < record.getsId().size(); i++) {
				// 查找版本号
				Software software = softwareMapper.selectByPrimaryKey(record.getsId().get(i));
				EdgeDeviceSoftware edgeDeviceSoftware = new EdgeDeviceSoftware();
				edgeDeviceSoftware.setsId(record.getsId().get(i));
				edgeDeviceSoftware.seteId(record.getId());
				deviceSoftware = deviceSoftwareMapper.insert(edgeDeviceSoftware);
			}
		}
        if(deviceHardware < 0 || deviceSoftware < 0 || insert < 0){
            logger.info("Save the result Likely to fail ");
        }

        return insert;
	}

    /**
     * @about select edgeDevice detail
     * @param id
     * @return
     */
	@Override
	//@Cacheable(cacheNames="device",key = "#id")
	public EdgeDevice selectEdgeDeviceById(Integer id) {
		EdgeDevice edgeDevice= edgeDeviceMapper.selectByPrimaryKey(id);
		logger.info(" edgeDevice detail is "+edgeDevice);
		if(edgeDevice!=null){
			edgeDevice.setHardwareList(selectEdgeDeviceHardwareByDeviceId(id));
			edgeDevice.setSoftwareList(selectAllEdgeDeviceSoftwareByDeviceId(id));
		}
		return edgeDevice;	
	}

	@Override
	//@Cacheable(cacheNames="device",key = "#id")
	public List<EdgeDevice> selectAllEdgeDeviceById(List<Integer> ids) {
	    // 1.查询所有配置单
		List<EdgeDevice> edgeDeviceList = edgeDeviceMapper.selectAllByPrimaryKey(ids);
		logger.info(" edgeDevice detail is " + edgeDeviceList.toString());
        List<EdgeDeviceHardware> hardwareList = new ArrayList<>();
		if(null != edgeDeviceList && edgeDeviceList.size() > 0){
		    // 2.查询所有配置单的所有硬件
            List<EdgeDeviceHardware> hardwares = deviceHardwareMapper.selectEdgeDeviceHardwareByDeviceIds(ids);

            if(hardwares.size() > 0){
                for(EdgeDevice edgeDevice : edgeDeviceList){
                    HashMap<String, List<String>> hashMap = new HashMap<>();
                    List<String> params = new ArrayList<>();
                    List<String> softParam = new ArrayList<>();
                    for(EdgeDeviceHardware hardware : hardwares){
                        if(edgeDevice.getId().equals(hardware.geteId())){
                            hardwareList.add(hardware);
                            params.add(hardware.gethJsonConfig());
                        }
                    }
					hashMap.put(CommonParams.HARDWARE, params);
                    edgeDevice.setHardwareList(hardwareList);
                    List<EdgeDeviceSoftware> edgeDeviceSoftwares = selectAllEdgeDeviceSoftwareByDeviceId(edgeDevice.getId());
                    if(null != edgeDeviceSoftwares && edgeDeviceSoftwares.size() > 0){
                        for(EdgeDeviceSoftware edgeDeviceSoftware : edgeDeviceSoftwares){
                            softParam = edgeDeviceSoftware.getAllParam();
                        }
                    }
                    hashMap.put(CommonParams.SOFTWARE,softParam);
                    edgeDevice.setAllParam(hashMap);
                    edgeDevice.setSoftwareList(edgeDeviceSoftwares);
                }
            }
		}
		return edgeDeviceList;
	}

	/**
	 * @about all selectAlledge
	 * @return
	 */
	@Override
//	@Cacheable(cacheNames="allDdevice")
	public List<EdgeDevice> selectAllEdgeDevice(String type) {
		List<EdgeDevice> list= edgeDeviceMapper.selectAlledge(type);
		/*if(list!=null){
			for(EdgeDevice edgeDevice:list){
				edgeDevice.setHardwareList(selectEdgeDeviceHardwareByDeviceId(edgeDevice.getId()));
				edgeDevice.setSoftwareList(selectAllEdgeDeviceSoftwareByDeviceId(edgeDevice.getId()));
			}
		}*/
		return list;
	}

	/**
	 * @about 设备详情、列表
	 * @return
	 */
	@Override
	//@Cacheable(cacheNames = "allDeviceTopology", key = "#id")
	public List<DepartmentDevice> selectEquipmentDetail(Integer id) {
        // 1.根据店铺找配置单
		List<DepartmentDevice> edgeDeviceList = deviceMapper.getDepartmentEdgeDevicedetail(id);

        // 1.1 查询设备的rtspurl  -- 部署文件
        HashMap<String, Object> hashMap = departmentServiceImpl.selectDepartmentInstallFile(id,null);

        // 删除下载包
        System.gc();
        CompressionFile.delFolder(CommonParams.INSTALL_DEPLOYMENT_FILE_COMPRESSIONFILE);
		List<CameraCoordinates> lineList = new ArrayList<>();

        // 2.设备返回
		if(null != edgeDeviceList && edgeDeviceList.size() > 0){
			for(DepartmentDevice edgeDevice : edgeDeviceList){
			    if(edgeDevice.getUseTy() == 1){
                    edgeDevice.setUseType(CommonParams.CAMERA_USE_TYPE_1);
                }
                if(edgeDevice.getUseTy() == 2){
                    edgeDevice.setUseType(CommonParams.CAMERA_USE_TYPE_2);
                }
                // 视频流地址
			    if(hashMap.keySet().contains(edgeDevice.getId().toString())){
                    CameraCoordinatesTwo coordinatesTwo = JSONObject.parseObject(hashMap.get(edgeDevice.getId().toString()).toString(), CameraCoordinatesTwo.class);
                   // edgeDevice.setUseType(coordinatesTwo.getCameraType() == 1 ? CommonParams.CAMERA_USE_TYPE_1 : CommonParams.CAMERA_USE_TYPE_2);
                    edgeDevice.setRtspurl(coordinatesTwo.getUrl());
                }
				// 获取摄像头画图线数据  TODO 以前是media.json 现在是paintermap.json
				if(null != hashMap.get(CommonParams.ENTER_OR_PASSING_LINES)){
                    HashMap<String, Object> oldValue = JSONObject.parseObject(hashMap.get(CommonParams.ENTER_OR_PASSING_LINES).toString(), HashMap.class);
                    if(!oldValue.keySet().isEmpty() && oldValue.containsKey(edgeDevice.getId().toString())){
                        if(CommonParams.CAMERA_USE_TYPE_1.equals(edgeDevice.getUseType())){
                            lineList = JSONArray.parseArray(oldValue.get(edgeDevice.getId().toString()).toString(), CameraCoordinates.class);
                        }
                    }
				}
				edgeDevice.setCameraLineList(lineList);
			}

			List<Integer> topoIds = new ArrayList<>();
			// 4.是否被拖拽
			List<DepartmentDeviceTopology> deviceTopology = departmentDeviceTopologyMapper.selectDepartmentDeviceTopology(id);
			if(null != deviceTopology && deviceTopology.size() > 0){
				String data = deviceTopology.get(0).getData();
				DepartmentDeviceTopologyParam departmentDeviceTopologyParam = JSON.parseObject(data, DepartmentDeviceTopologyParam.class);
				List<NodeData> nodeDataArray = departmentDeviceTopologyParam.getNodeDataArray();
				for(NodeData nodeData : nodeDataArray){
					if(nodeData.getType().equals(CommonParams.DEPARTMENT)){
						continue;
					}else{
						topoIds.add(nodeData.getId());
					}
				}
			}
			// 5.安装设备内置软件硬件
            for(DepartmentDevice edgeDevice : edgeDeviceList){
				/*List<EdgeDeviceHardware> hardwares = new ArrayList<>();
				List<EdgeDeviceSoftware> softwares = new ArrayList<>();
				List<OperatingSystem> oSystems = new ArrayList<>();*/

				// 1.硬件
				List<Integer> deviceIds = new ArrayList<>();
				deviceIds.add(edgeDevice.geteId());
				List<String> osCodes = new ArrayList<>();
				osCodes.add(edgeDevice.getOsCode());
				List<EdgeDeviceHardware> hardwareList = deviceHardwareMapper.selectEdgeDeviceHardwareByDeviceIds(deviceIds);
				// 2.软件
				List<EdgeDeviceSoftware> softwaresList = deviceSoftwareMapper.selectEdgeDeviceSoftwareByDeviceIds(deviceIds);
				// 3. Osystem
				List<OperatingSystem> systemList = operatingSystemMapper.selectAllByOsCodes(osCodes);
                // 智能终端没有软件和操作系统
				/*if(edgeDevice.geteType().equals("edge")){
				    if(null != softwaresList && softwaresList.size() > 0){
                        for(EdgeDeviceSoftware software : softwaresList){
                            softwares.add(software);
                        }
                    }
                    if(null != systemList && systemList.size() > 0){
                        for(OperatingSystem oSystem : systemList){
                            oSystems.add(oSystem);
                        }
                    }
                }
                if(null != hardwareList && hardwareList.size() > 0){
                    for(EdgeDeviceHardware hardware : hardwareList){
                        if(edgeDevice.geteId().equals(hardware.geteId())){
                            hardwares.add(hardware);
                        }
                    }
                }*/
                edgeDevice.setHardwareList(hardwareList);
                edgeDevice.setoSystmeList(systemList);
                edgeDevice.setSoftwareList(softwaresList);
                // 暂时返回空
                edgeDevice.setDescription(StringUtils.isBlank(edgeDevice.getDescription()) ? " " : edgeDevice.getDescription());
                edgeDevice.setParam(StringUtils.isBlank(edgeDevice.getParam()) ? " " : edgeDevice.getParam());
               // edgeDevice.setUseType(StringUtils.isBlank(edgeDevice.getUseType()) ? " " : edgeDevice.getUseType());
				// 是否拖拽
				edgeDevice.setIsDrag(0);
                if(null != topoIds && topoIds.size() > 0){
					for (int i = 0; i < topoIds.size(); i++) {
						if(topoIds.get(i).equals(edgeDevice.getId())){
							edgeDevice.setIsDrag(1);
						}
					}
				}
			}
		}

		return edgeDeviceList;
	}

	/**
	 * @param btitdCode
	 * @param devicecode
	 * @return 根据tx2_id 与 门店id 查询设备详情
	 */
	@Override
	public ResponseModel selectDeviceByTx2IdAndDepartmentId(String btitdCode, String devicecode) {

		// 1.根据code 查找id
        Department department = departmentMapper.selectByBtitDepartmentCode(btitdCode);
        if(null == department){
            return ResponseModel.fail("找不到该门店！",-1);
        }
        DepartmentDevice device = deviceMapper.selectByCode(devicecode);
        List<DepartmentDevice> departmentDeviceList = deviceMapper.selectDeviceByEdgeServerId(department.getId(),device.getId());

		List<CaremaVo> caremaList = new ArrayList<>();
		HashMap<String, Object> resultHashMap = new HashMap<>();
		// 2.查询摄像头部署文件划线数据
		HashMap<String, Object> hashMap = departmentServiceImpl.selectDepartmentInstallFile(department.getId(),null);
        // 2.1删除下载包
        System.gc();
        CompressionFile.delFolder(CommonParams.INSTALL_DEPLOYMENT_FILE_COMPRESSIONFILE);
		// 3.摄像头数据
		for(DepartmentDevice departmentDevice : departmentDeviceList){
			if(hashMap.keySet().contains(departmentDevice.getId().toString())){
                List<CameraCoordinates> lineList = new ArrayList<>();
                CaremaVo caremaVo = new CaremaVo();
                caremaVo.setCaremaId(departmentDevice.getId());
                caremaVo.setCaremaName(departmentDevice.getServerName());
                caremaVo.setCode(departmentDevice.getServerCode());
				CaremaVoForLineList caremaVoForLineList = JSONObject.parseObject(hashMap.get(departmentDevice.getId().toString()).toString(), CaremaVoForLineList.class);
                // 获取摄像头画图线数据  TODO 以前是media.json 现在是paintermap.json
                if(null != hashMap.get(CommonParams.ENTER_OR_PASSING_LINES)){
                    HashMap<String, Object> oldValue = JSONObject.parseObject(hashMap.get(CommonParams.ENTER_OR_PASSING_LINES).toString(), HashMap.class);
                    if(!oldValue.keySet().isEmpty() && oldValue.containsKey(departmentDevice.getId().toString())){
                        lineList = JSONArray.parseArray(oldValue.get(departmentDevice.getId().toString()).toString(), CameraCoordinates.class);
                    }
                }
                caremaVo.setLineList(lineList);
				caremaVo.setUrl(caremaVoForLineList.getUrl());
				caremaVo.setCameraType(caremaVoForLineList.getCameraType());
                caremaList.add(caremaVo);
			}
		}
		// 4.模型数据
		List<Software> softwareList = softwareMapper.selectEdgeServerDetail(department.getId(),device.getId());
		// 5.去重
		softwareList = softwareList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
				new TreeSet<>(Comparator.comparing(o -> o.getId()))), ArrayList::new));

		List<SoftwareConfig> softwareConfigList = softwareMapper.selectSoftwareConfigForCanUse(department.getId(),device.getId());
		List<SoftwareForDevice> softwareResult = new ArrayList<>();
		if(null != softwareList && softwareList.size() > 0 ){
			for(Software software : softwareList){
				List<String> setSoftwareConfigCodeList = new ArrayList<>();
				SoftwareForDevice softwareForDevice = new SoftwareForDevice();
				softwareForDevice.setId(software.getId());
				softwareForDevice.setCode(software.getsCode());
				softwareForDevice.setUrl(software.getFilePath());
				softwareForDevice.setName(software.getFileName());

				for(SoftwareConfig softwareConfig1 : softwareConfigList){
					if(softwareConfig1.getsId().equals(software.getId())){
						setSoftwareConfigCodeList.add(softwareConfig1.getCode());
						softwareForDevice.setVersion(softwareConfig1.getVersion());
					}
				}
				softwareForDevice.setSoftwareConfigCode(setSoftwareConfigCodeList);
				softwareResult.add(softwareForDevice);
			}
		}

		resultHashMap.put("cameraList",caremaList);
		resultHashMap.put("caremaList",caremaList);
		resultHashMap.put("tx2Id",device.getId());
		resultHashMap.put("tx2Code",devicecode);
		resultHashMap.put("storeCode",btitdCode);
		resultHashMap.put("storeId",department.getId());
		resultHashMap.put("caremaVideoStream",CONTROL_SERVER_CAREMAVIDEOSTREAM);
        resultHashMap.put("allSoftwareList",softwareResult);
        resultHashMap.put("softwareList",softwareResult);
		return ResponseModel.ok(resultHashMap);
	}
    public  boolean isNotBlank(String st){
        if(StringUtils.isBlank(st) || "\"\"".equals(st)){
            return true;
        }
        return false;
    }

	@Override
	//@Cacheable(cacheNames="allDdevice")
	public List<EdgeDevice> selectAllEdgeDeviceByType(String type,String name) {
		List<EdgeDevice> list = edgeDeviceMapper.selectAllByType(type,name);
		List<Integer> eIds = new ArrayList();
		if(list != null && list.size() > 0){
			for(EdgeDevice edgeDevice:list){
                eIds.add(edgeDevice.getId());
                edgeDevice.setIsConfig(0);
                edgeDevice.setHardwareList(selectEdgeDeviceHardwareByDeviceId(edgeDevice.getId()));
				edgeDevice.setSoftwareList(selectAllEdgeDeviceSoftwareByDeviceId(edgeDevice.getId()));
			}
            // 判断状态<是否有店铺已生成设备>
            List<DepartmentDevice> deviceList = deviceMapper.selectByeIds(eIds);
            if(null != deviceList && deviceList.size() > 0){
                for(EdgeDevice edgeDevice :list){
                    for(DepartmentDevice departmentDevice : deviceList){
                        if(edgeDevice.getId().equals(departmentDevice.geteId())){
                            edgeDevice.setIsConfig(1);
                        }
                    }
                }
            }
		}
		return list;
	}

	/**
	 * @about update edgeDevice
	 * @param record
	 * @param
	 * @param
	 * @return
	 */
	@Override
	//@CachePut(cacheNames="device",key = "#record.id")
	@Transactional(rollbackFor = Exception.class)
	public int updateEdgeDeviceById(EdgeDevice record) {
		int deviceSoftware = 0;
        // 1. 修改配置单
        int result = edgeDeviceMapper.updateByPrimaryKey(record);
        // 2. 先删除已绑定的软件/硬件
		deviceHardwareMapper.deleteEdgeDeviceHardwareById(record.getId());
		deviceSoftwareMapper.deleteEdgeDeviceSoftwareById(record.getId());
		// 3. 在新增
		EdgeDeviceHardware edgeDeviceHardware = new EdgeDeviceHardware();
		edgeDeviceHardware.sethId(record.gethId());
		edgeDeviceHardware.seteId(record.getId());
		int deviceHardware = deviceHardwareMapper.insert(edgeDeviceHardware);
        List<EdgeDeviceSoftware> addSoftWareList = new ArrayList<>();
		if(null != record.getsId()){
			for (int i = 0; i < record.getsId().size(); i++) {
				EdgeDeviceSoftware edgeDeviceSoftware = new EdgeDeviceSoftware();
				edgeDeviceSoftware.setsId(record.getsId().get(i));
				edgeDeviceSoftware.seteId(record.getId());
				addSoftWareList.add(edgeDeviceSoftware);
			}
		}
		if(addSoftWareList.size() > 0){
			deviceSoftware = deviceSoftwareMapper.insertList(addSoftWareList);
		}
		if(deviceHardware < 0 || deviceSoftware < 0 || result < 0){
			logger.info("Save the result Likely to fail ");
		}

        return result;
	}

	@Override
	//@Caching(evict={@CacheEvict(cacheNames="DeviceSoftware", key = "#id"),@CacheEvict(cacheNames="allDeviceSoftware",allEntries=true)})
	public int deleteEdgeDeviceSoftwareById(Integer deviceId, Integer softwareId) {
		return deviceSoftwareMapper.deleteEdgeDeviceSoftwareById(deviceId);
	}

	@Override
	//@CacheEvict(cacheNames="allDeviceSoftware",allEntries=true)
	public int insertEdgeDeviceSoftware(EdgeDeviceSoftware record) {
		return deviceSoftwareMapper.insert(record);
	}

	@Override
	//@Cacheable(cacheNames="DeviceSoftware",key = "#id")
	public EdgeDeviceSoftware selectEdgeDeviceSoftwareById(Integer id) {
		EdgeDeviceSoftware deviceSoftware= deviceSoftwareMapper.selectByPrimaryKey(id);
		if(deviceSoftware!=null){
			deviceSoftware.setSoftware(resourceService.selectSoftwareById(deviceSoftware.getsId()));
		}
		return deviceSoftware;
	}

	@Override
	//@Cacheable(cacheNames="allDeviceSoftware")
	public List<EdgeDeviceSoftware> selectAllEdgeDeviceSoftware() {
		List<EdgeDeviceSoftware> list= deviceSoftwareMapper.selectAll();
		if(list!=null){
			for(EdgeDeviceSoftware deviceSoftware:list){
				if(deviceSoftware!=null){
					deviceSoftware.setSoftware(resourceService.selectSoftwareById(deviceSoftware.getsId()));
				}
			}
		}
		return list;
	}

	@Override
	//@Cacheable(cacheNames="allDeviceSoftware",key="#deviceId")
	public List<EdgeDeviceSoftware> selectAllEdgeDeviceSoftwareByDeviceId(Integer deviceId) {
		List<EdgeDeviceSoftware> list =  deviceSoftwareMapper.selectAllEdgeDeviceSoftwareByDeviceId(deviceId);
        List<String> params = new ArrayList<>();
        if(null != list && list.size() > 0){
			for(EdgeDeviceSoftware deviceSoftware:list){
				if(deviceSoftware != null){
                    Software software = resourceService.selectSoftwareById(deviceSoftware.getsId());
                    if(null != software.getParameter() && !software.getParameter().equals("")){
                        params.add(software.getParameter());
                    }
                    logger.info("from selectAllEdgeDeviceSoftwareByDeviceId() , software is "+software.getId()+software.getName()+"==============");
                    deviceSoftware.setSoftware(software);
                    deviceSoftware.setAllParam(params);
				}
			}
		}
		return list;
	}

	@Override
	//@CachePut(cacheNames="DeviceSoftware",key = "#record.id")
	public int updateEdgeDeviceSoftwareById(EdgeDeviceSoftware record) {
		return deviceSoftwareMapper.updateByPrimaryKey(record);
	}

	@Override
	//@Caching(evict={@CacheEvict(cacheNames="DeviceHardware", key = "#id"),@CacheEvict(cacheNames="allDeviceHardware",allEntries=true)})
	public int deleteEdgeDeviceHardwareById(Integer deviceId, Integer hardwareId) {
		return deviceHardwareMapper.deleteEdgeDeviceHardwareById(deviceId);
	}

	@Override
	//@CacheEvict(cacheNames="allDeviceHardware",allEntries=true)
	public int insertEdgeDeviceHardware(EdgeDeviceHardware record) {
		return deviceHardwareMapper.insert(record);
	}

	@Override
	//@Cacheable(cacheNames="DeviceHardware",key = "#id")
	public EdgeDeviceHardware selectEdgeDeviceHardwareById(Integer id) {
		EdgeDeviceHardware edgeDeviceHardware= deviceHardwareMapper.selectByPrimaryKey(id);
		if(edgeDeviceHardware!=null){
			edgeDeviceHardware.setHardware(resourceService.selectHardwareById(null,edgeDeviceHardware.gethId()));
		}
		return edgeDeviceHardware;
	}

	@Override
	//@Cacheable(cacheNames="allDeviceHardware",key = "#deviceId")
	public List<EdgeDeviceHardware> selectEdgeDeviceHardwareByDeviceId(Integer deviceId) {
		return deviceHardwareMapper.selectEdgeDeviceHardwareByDeviceId(deviceId);
	}

	@Override
	//@Cacheable(cacheNames="allDeviceHardware")
	public List<EdgeDeviceHardware> selectAllEdgeDeviceHardware() {
		List<EdgeDeviceHardware> list=deviceHardwareMapper.selectAll();
		if(list!=null&&list.size()>0){
			for(EdgeDeviceHardware edgeDeviceHardware:list){
				if(edgeDeviceHardware!=null){
					edgeDeviceHardware.setHardware(resourceService.selectHardwareById(null,edgeDeviceHardware.gethId()));
				}
			}
		}
		return list;
	}
	


	@Override
	//@CachePut(cacheNames="DeviceHardware",key = "#record.id")
	public int updateEdgeDeviceHardwareById(EdgeDeviceHardware record) {
		return deviceHardwareMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult<EdgeDevice> selectPageEdgeDevice(PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<EdgeDevice> sysMenus = selectAllEdgeDevice(null);
		return PageUtils.getPageResult(pageRequest, new PageInfo<EdgeDevice>(sysMenus));
	}
	
	@Override
	public PageResult<EdgeDevice> selectPageEdgeDeviceByType(String type, PageRequest pageRequest,String name) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<EdgeDevice> sysMenus = selectAllEdgeDeviceByType(type,name);
		return PageUtils.getPageResult(pageRequest, new PageInfo<EdgeDevice>(sysMenus));
	}

	@Override
	public PageResult<EdgeDeviceSoftware> selectPageEdgeDeviceSoftware(PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<EdgeDeviceSoftware> sysMenus = selectAllEdgeDeviceSoftware();
		return PageUtils.getPageResult(pageRequest, new PageInfo<EdgeDeviceSoftware>(sysMenus));
	}

	@Override
	public PageResult<EdgeDeviceSoftware> selectPageEdgeDeviceSoftwareByDeviceId(Integer deviceId,
			PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<EdgeDeviceSoftware> sysMenus =  selectAllEdgeDeviceSoftwareByDeviceId(deviceId);
		return PageUtils.getPageResult(pageRequest, new PageInfo<EdgeDeviceSoftware>(sysMenus));
	}

	@Override
	public PageResult<EdgeDeviceHardware> selectPageEdgeDeviceHardware(PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<EdgeDeviceHardware> sysMenus =selectAllEdgeDeviceHardware();
		return PageUtils.getPageResult(pageRequest, new PageInfo<EdgeDeviceHardware>(sysMenus));
	}

	@Override
	public PageResult<EdgeDeviceHardware> selectPageEdgeDeviceHardwareByDeviceId(Integer deviceId,
			PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<EdgeDeviceHardware> sysMenus =selectEdgeDeviceHardwareByDeviceId(deviceId);
		return PageUtils.getPageResult(pageRequest, new PageInfo<EdgeDeviceHardware>(sysMenus));
	}





}

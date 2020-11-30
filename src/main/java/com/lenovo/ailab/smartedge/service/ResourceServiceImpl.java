package com.lenovo.ailab.smartedge.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lenovo.ailab.smartedge.common.CommonParams;
import com.lenovo.ailab.smartedge.config.CompressionFile;
import com.lenovo.ailab.smartedge.config.TreeConfig;
import com.lenovo.ailab.smartedge.config.UploadFileConfig;
import com.lenovo.ailab.smartedge.dao.*;
import com.lenovo.ailab.smartedge.dao.po.*;
import com.lenovo.ailab.smartedge.dao.vo.FileVo;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import com.lenovo.ailab.smartedge.utils.SystemTypes;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lenovo.ailab.smartedge.domain.PageRequest;
import com.lenovo.ailab.smartedge.domain.PageResult;
import com.lenovo.ailab.smartedge.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


/**
 * Title: ResourceServiceImpl.java
 * 
 * @Autohr "chenpeng"
 * @data 2019年12月3日
 * @Email chenpeng10@lenovo.com
 * @description:
 **/
@Service
@CacheConfig(cacheNames = "resource")
public class ResourceServiceImpl implements ResourceService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Value("${file.down-url}")
    String FILE_DOWN_URL;
	@Autowired
	HardwareMapper hardwareMapper;
	@Autowired
	OperatingSystemMapper osystemMapper;
	@Autowired
	SoftwareMapper softwareMapper;
	@Autowired
	EdgeDeviceHardwareMapper edgeDeviceHardwareMapper;
	@Autowired
	EdgeDeviceSoftwareMapper edgeDeviceSoftwareMapper;
	@Autowired
	EdgeDeviceMapper edgeDeviceMapper;
    @Autowired
    private UploadFileConfig uploadFileConfig;
    @Autowired
	private DepartmentDeviceMapper departmentDeviceMapper;
    @Autowired
	private DepartmentServiceImpl departmentServiceImpl;
    @Autowired
    private RestTemplate restTemplate;

	@Override
	//@Caching(evict={@CacheEvict(cacheNames="hardware", key = "#id"),@CacheEvict(cacheNames="allHardware",allEntries=true)})
	public int deleteHardwareById(Integer id) {
		return hardwareMapper.deleteByPrimaryKey(id);
	}

	@Override
	//@CacheEvict(cacheNames="allHardware",allEntries=true)
	public int insertHardware(Hardware record) {
		record.setVersion(CommonParams.VERSION_DEFAULT);
		return hardwareMapper.insert(record);
	}

	@Override
	//@Cacheable(cacheNames="hardware",key = "#id")
	public Hardware selectHardwareById(String type,Integer id) {
        Hardware hardware = new Hardware();
        if(null != type){
	        if("end".equals(type)){
	            hardware = hardwareMapper.selectByPrimaryKey(id);
            }else{
                hardware = hardwareMapper.selectByPrimaryKeyAndEdge(id);
            }
        }
        return hardware;
	}

	/**
	 * @about  智能硬件类型下拉菜单
	 * @return
	 */
	@Override
	//@Cacheable(cacheNames="allEndType")
	public ResponseModel getAllEndType() {
		List<TreeType> nodes = Lists.newArrayList();
		List<HardwareEnd> list = hardwareMapper.selectEndType();
		for(HardwareEnd hardwareEnd : list){
			nodes.add(new TreeType(hardwareEnd.getId(), hardwareEnd.getName(),String.valueOf(hardwareEnd.getId()), -1));
		}
		return ResponseModel.ok(TreeConfig.getTree(nodes));
	}

    /**
     * @param cId
     * @param hardware
     * @return
     * @about update camera status
     */
    @Override
    public int updateEdgeDeviceStatus(Integer cId, HardwareStstus hardware) {
    	int status = 0;
        return  departmentDeviceMapper.updateEdgeDeviceStatus(cId,status);
    }

    public HardwareDeploymentFile insertCameraDeployFile(Integer dId, MultipartFile file){
		ResponseModel responseModel = uploadOSystem(file, CommonParams.HARDWARE_CONFIG_FILE);
		String deploymentFilePath = "";
		if(0 == responseModel.getStatus()){
			HashMap<String,String> map = (HashMap<String,String>)responseModel.getResult();
			String filePath = map.get("filePath");
			if(!StringUtils.isEmpty(filePath)){
				deploymentFilePath = filePath;
			}
            /*String modelResult = (String)responseModel.getResult();
            String[] split = modelResult.split("\\:");
            if(split.length > 0){
               // deploymentFilePath = FILE_DOWN_URL + split[1];
                deploymentFilePath = split[1];
            }*/
			// 1. 入库部署文件
			String version = deploymentFilePath.valueOf((int)(System.currentTimeMillis() / 1000));
			HardwareDeploymentFile hardwareDeploymentFile = new HardwareDeploymentFile();
			hardwareDeploymentFile.setdId(dId);
			hardwareDeploymentFile.setVersion(version);
			hardwareDeploymentFile.setDeploymentFilePath(deploymentFilePath);
			hardwareDeploymentFile.setDeploymentFileName( dId +"_"+ version + CommonParams.FILE_TYPE_ZIP);
			hardwareDeploymentFile.setDeploymentFileSize(file.getSize());
			hardwareDeploymentFile.setType(CommonParams.DEPLOY_FILE);
			int result = hardwareMapper.insertHardwareDeploymentFile(hardwareDeploymentFile);
			if(result > 0){
				return hardwareDeploymentFile;
			}
		}
		return null;
    }
	/**
	 * @param dId
	 * @param file
	 * @return
	 */
	@Override
    @Transactional(rollbackFor = Exception.class)
	public ResponseModel updateHardwareConfigFile(Integer dId,MultipartFile file,String type) {
		// 上传部署包
		HardwareDeploymentFile deploymentFile = insertCameraDeployFile(dId, file);
		if(null != deploymentFile){
			// 2. 更改摄像头用途类型
			HashMap<String, Object> hashMap = departmentServiceImpl.selectDepartmentInstallFile(dId,deploymentFile.getDeploymentFilePath());
			List<DepartmentDeviceForUseType> departmentDeviceForUseType = new ArrayList<>();
			Iterator iter = hashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				try{
					// 转换类型有问题，不是摄像头的数据，直接跳过  Integer num = (Integer) entry.getKey();
					CameraCoordinatesTwo coordinatesTwo = JSONObject.parseObject(entry.getValue().toString(), CameraCoordinatesTwo.class);
					DepartmentDeviceForUseType deviceForUseType = new DepartmentDeviceForUseType();
					deviceForUseType.setId(Integer.parseInt(entry.getKey().toString()));
					deviceForUseType.setUseType(coordinatesTwo.getCameraType());
					departmentDeviceForUseType.add(deviceForUseType);

				}catch (Exception e){

				}
			}

			if(null != departmentDeviceForUseType && departmentDeviceForUseType.size() > 0 && type.equals("deploy")){
				// 删除安装数据,更新摄像头类型
				departmentDeviceMapper.updateDeviceUseType(departmentDeviceForUseType);
			}
		}else{
			return ResponseModel.fail("上传文件路径为空，上传失败！",-1);
		}
        return ResponseModel.ok(deploymentFile);
	}

    /**
     * @param cId
     * @param file
     * @param type
     * @return 上传摄像头校对图片
     */
    @Override
    public ResponseModel insertHardwareInstallImage(Integer cId, MultipartFile file,String type) {
        ResponseModel responseModel = uploadOSystem(file, CommonParams.HARDWARE_INSTALL_THE_IMAGE_FILE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String deploymentFilePath = "";
        if(0 == responseModel.getStatus()){
            HashMap<String,String> map = (HashMap<String,String>)responseModel.getResult();
            String filePath = map.get("filePath");
            if(!StringUtils.isEmpty(filePath)){
                deploymentFilePath = filePath;
            }
			DepartmentDevice carema = new DepartmentDevice();
			carema.setId(cId);

            if(type.equals(CommonParams.UPLOAD_COVERAGE_AREA_IMAGE)){
				carema.setCoverageAreaImage(deploymentFilePath);
				carema.setInstallStatus(1);
			}else{
				carema.setInstallTheImage(deploymentFilePath);
                carema.setInstallTheImageId(format.format(new Date()) + "_" + cId);
			}
            int result = hardwareMapper.insertHardwareInstallImage(carema);
            responseModel.setResult(carema.getInstallTheImageId());
        }

        return responseModel;
    }

	@Override
	public List<DepartmentDevice> selectEdgeDeviceForCamera() {

		return departmentDeviceMapper.selectEdgeDeviceForCamera();
	}

	/**
	 * @param jsonObject
	 * @return  上传划线数据by部署工具
	 */
	@Override
	public ResponseModel updateHardwareConfigFileForLine(JSONObject jsonObject,Integer departmentId) {
		// 1.校验参数
		/*if(jsonObject.isEmpty()){
            return ResponseModel.fail("参数不可为空！",-1);
		}*/
		// 2.获取门店最新部署文件解压  写入更新文件
		List<HardwareDeploymentFile> deploymentFile = departmentDeviceMapper.getDeploymentFile(departmentId);
		HardwareDeploymentFile hardwareDeploymentFile = null;
        long randomNum = System.currentTimeMillis();
        if(null != deploymentFile && deploymentFile.size() == 0){
			return ResponseModel.fail("找不到该门店的部署文件！",-1);
		}
		String urlFile = deploymentFile.get(0).getDeploymentFilePath();
		String urlFileName = deploymentFile.get(0).getDeploymentFileName();
		String[] split = urlFileName.split("\\.");
		urlFileName = split[0];
		if(StringUtils.isNotBlank(urlFile)){
			// 3.下载并解压
			byte[] imageFromURL = CompressionFile.getImageFromURL(urlFile);
			File file = CompressionFile.getFileByBytes(imageFromURL, CommonParams.INSTALL_DEPLOYMENT_FILE_COMPRESSIONFILE, randomNum+CommonParams.INSTALL_DEPLOYMENT_FILE_TEST);
			// 4.解析并追加
			if(null != file) {
				try {
					if (!file.exists()) {
						file.createNewFile();
					}
					FileVo fileVo = CompressionFile.UnZipFolderForLine(file.getAbsolutePath(), CommonParams.INSTALL_DEPLOYMENT_FILE_COMPRESSIONFILE + urlFileName + File.separator,CommonParams.ENTERLINES_JSON);
					if (null != fileVo) {
						logger.info("add  before  ...  media.json is {}", fileVo.getWrite());

						FileWriter fileWriter = new FileWriter(fileVo.getFile().getParent() + File.separator + CommonParams.ENTERLINES_JSON, false);
						fileWriter.write(jsonObject.toJSONString());
						fileWriter.flush();
						fileWriter.close();
						// 5. 重新压缩
						File fileResult = CompressionFile.ZipFolder(fileVo.getFile().getParent(), fileVo.getFile().getParent() + CommonParams.FILE_TYPE_ZIP);
						// 6. 更新上传
						if(fileResult.exists()){
							logger.info("file exist !!!!!! file path is {} " ,fileResult.getPath());

							String path = fileResult.getPath() + CommonParams.FILE_TYPE_ZIP;
							File file1 = new File(path);
							FileInputStream fileInputStream = new FileInputStream(file1);
							MultipartFile multipartFile = new MockMultipartFile(file1.getName(), file1.getName(),
									ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
							fileInputStream.close();
							hardwareDeploymentFile = insertCameraDeployFile(departmentId, multipartFile);
						}
					}
					// 删除下载压缩包
					System.gc();
					CompressionFile.delFolder(CommonParams.INSTALL_DEPLOYMENT_FILE_COMPRESSIONFILE);
				} catch (Exception e) {
					logger.info(" have an exception , Failed to parse the uploaded file");
					return ResponseModel.fail("解析有误，上传失败！",-1);
				}
			}
		}
		return ResponseModel.ok(hardwareDeploymentFile);
	}


	@Override
	//@Cacheable(cacheNames="allHardware")
	public List<Hardware> selectAllHardware(String hCode, String name) {
		return hardwareMapper.selectAll(hCode, name);
	}

	@Override
	//@Cacheable(cacheNames="allHardware",key = "#type")
	public List<Hardware> selectAllByType(String type, String hCode, String name) {
		List<Hardware> hardwareList = new ArrayList<>();
		if(CommonParams.EDGE_TYPE_END.equals(type)){
			hardwareList = hardwareMapper.selectHardwareByType(hCode, name);
		}else{
			hardwareList = hardwareMapper.selectHardwareByTypeForEdge(hCode, name);
		}
		// 返回isConfig<判断是否被使用>
		List<Integer> hIds = new ArrayList<>();
        if(null != hardwareList && hardwareList.size() > 0){
            for(Hardware hardware : hardwareList){
				hIds.add(hardware.getId());
            }
			List<EdgeDeviceHardware> edgeDeviceHardwares = edgeDeviceHardwareMapper.selectEdgeDeviceHardwareByHid(hIds);
            if(null != edgeDeviceHardwares && edgeDeviceHardwares.size() > 0){
				for(Hardware hardware : hardwareList){
					hardware.setIsConfig(0);
					for(EdgeDeviceHardware edgeDeviceHardware : edgeDeviceHardwares){
						if(hardware.getId().equals(edgeDeviceHardware.gethId())){
							hardware.setIsConfig(1);
						}
					}
				}
			}
        }
        return hardwareList;
	}

	@Override
	//@CachePut(cacheNames="hardware",key = "#record.id")
	public int updateHardware(Hardware record) {
		return hardwareMapper.updateByPrimaryKey(record);
	}

    /**
     * @about delete OSystem
     * @param id
     * @return
     */
	@Override
	//@Caching(evict={@CacheEvict(cacheNames="OSystem", key = "#id"),@CacheEvict(cacheNames="allOSystem",allEntries=true)})
	public int deleteOSystemById(Integer id) {
		return osystemMapper.deleteByPrimaryKey(id);
	}

    /**
     * @about save OSystem
     * @param record
     * @return
     */
	@Override
	//@CacheEvict(cacheNames="allOSystem",allEntries=true)
	public int insertOSystem(OperatingSystem record) {
		record.insertGenerator();
        record.setVersion(CommonParams.VERSION_DEFAULT);
		return osystemMapper.insert(record);

	}

	/**
	 * @about upload file
	 * @param file
	 * @return
	 */
	@Override
	public ResponseModel uploadOSystem(MultipartFile file,String type) {
        if (file.isEmpty()) {
            logger.error("The file is empty");
            return ResponseModel.fail("The file must not be empty",1);
        }
        String fileName = file.getOriginalFilename();// 文件本名
        String suffixName = fileName.substring(fileName.lastIndexOf(CommonParams.SPLIT));  // 后缀名
        // 校验文件类型
        if(CommonParams.SOFTWARE.equals(type) || CommonParams.O_SYSTEM.equals(type) || CommonParams.HARDWARE_CONFIG_FILE.equals(type)){
            if(!(CommonParams.FILE_TYPE_ZIP.endsWith(suffixName.toLowerCase()) || CommonParams.FILE_TYPE_RAR.endsWith(suffixName.toLowerCase()))){
                logger.error("Please upload the ZIP/RAR format ");
				return ResponseModel.fail("Please upload the ZIP/RAR format",1);
            }
        }else {
            if(!(CommonParams.FILE_TYPE_JPG.endsWith(suffixName.toLowerCase())
                    || CommonParams.FILE_TYPE_JPEG.endsWith(suffixName.toLowerCase())
                    || CommonParams.STATIC_JPG.endsWith(suffixName.toLowerCase())
            )){
                logger.error("Please upload JPG/JPEG/PNG Image");
				return ResponseModel.fail("Please upload JPG/JPEG/PNG Image",1);
            }
        }
        String newResult = "";
        String fileType = "";
        String result = "";
        try {
            byte[] bytes = file.getBytes();
            String[] split = fileName.split("\\.");
            if(split.length > 0){
                fileType = split[1];
            }
            result = uploadFileConfig.fileUpload(type, fileName, bytes,fileType);
        } catch (Exception e) {
			logger.error(e.toString(), e);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",fileName);
        map.put("filePath", FILE_DOWN_URL + result);
       // newResult = fileName + CommonParams.COLON + FILE_DOWN_URL + result;
        return ResponseModel.ok(map);
	}

	/**
	 * @About select system type
	 * @param
	 * @return
	 */
	@Override
	//@Cacheable(cacheNames="allOSystemTypes")
	public ResponseModel downloadOSystemFilegetSystemType() {
		List<TreeType> nodes = Lists.newArrayList();
		nodes.add(new TreeType(1, SystemTypes.SYSTEM_TYPE_LINUX,SystemTypes.SYSTEM_TYPE_LINUX, -1));
		nodes.add(new TreeType(2, SystemTypes.SYSTEM_TYPE_LINUX_CENTOS,SystemTypes.SYSTEM_TYPE_LINUX_CENTOS,1));
		nodes.add(new TreeType(3, SystemTypes.SYSTEM_TYPE_LINUX_DEEPIN,SystemTypes.SYSTEM_TYPE_LINUX_DEEPIN,1));
		nodes.add(new TreeType(4, SystemTypes.SYSTEM_TYPE_LINUX_LUBUNTU, SystemTypes.SYSTEM_TYPE_LINUX_LUBUNTU,1));
		nodes.add(new TreeType(5, SystemTypes.SYSTEM_TYPE_LINUX_UBUNTU, SystemTypes.SYSTEM_TYPE_LINUX_UBUNTU,1));
		nodes.add(new TreeType(6, SystemTypes.SYSTEM_TYPE_WINDOWS, SystemTypes.SYSTEM_TYPE_WINDOWS,-1));
		nodes.add(new TreeType(7, SystemTypes.SYSTEM_TYPE_WINDOWS_WINDOWS7, SystemTypes.SYSTEM_TYPE_WINDOWS_WINDOWS7,6));
		nodes.add(new TreeType(8, SystemTypes.SYSTEM_TYPE_WINDOWS_WINDOWS8, SystemTypes.SYSTEM_TYPE_WINDOWS_WINDOWS8,6));
		nodes.add(new TreeType(9, SystemTypes.SYSTEM_TYPE_WINDOWS_WINDOWS10, SystemTypes.SYSTEM_TYPE_WINDOWS_WINDOWS10,6));
		nodes.add(new TreeType(10, SystemTypes.SYSTEM_TYPE_MAC,SystemTypes.SYSTEM_TYPE_MAC, -1));
		nodes.add(new TreeType(11, SystemTypes.SYSTEM_TYPE_MAC_MACOS, SystemTypes.SYSTEM_TYPE_MAC_MACOS,10));
		nodes.add(new TreeType(12, SystemTypes.SYSTEM_TYPE_MAC_OSX,SystemTypes.SYSTEM_TYPE_MAC_OSX, 10));

        return ResponseModel.ok(TreeConfig.getTree(nodes));
	}

	/**
	 * @about select OSystem for drop-down list
	 * @return
	 */
	@Override
	//@Cacheable(cacheNames="allOSystem")
	public List<OperatingSystem> selectNoPageOSystem() {
		List<OperatingSystem> result = osystemMapper.selectNoPageOSystem();
		return result;
	}

	@Override
	public List<Hardware> selectNoPageHardware(String type,Integer endTypeId) {
		List<Hardware> hardwareList = hardwareMapper.selectNoPageHardware(type,endTypeId);
		return hardwareList;
	}

	/**
	 * @about select software drop-down list
	 * @return
	 */
	@Override
	//@Cacheable(cacheNames="allSoftware")
	public List<Software> selectPageSoftware() {
		List<Software> softwares = softwareMapper.selectPageSoftware();
		return softwares;
	}

	public String autoUpgradeVersion(String version){
		if (version.equals("")) {
			version = CommonParams.VERSION_DEFAULT_ZERO;
		}
		//将版本号拆解成整数数组
		String[] arr = version.split("\\.");
		int[] ints=new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			ints[i] = Integer.valueOf(arr[i]);
		}

		//递归调用
		autoUpgradeVersion(ints, ints.length - 1);

		//数组转字符串
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ints.length; i++) {
			sb.append(ints[i]);
			if ((i + 1) != ints.length) {
				sb.append(".");
			}
		}
		return sb.toString();
	}

	public void autoUpgradeVersion(int[] ints, int index){
		if(index == 0){
			ints[0] = ints[0] + 1;
		}
		else {
			int value = ints[index] + 1;
			if (value < 10) {
				ints[index] = value;
			}
			else {
				ints[index] = 0;
				autoUpgradeVersion(ints, index - 1);
			}
		}
	}

	@Override
	//@Cacheable(cacheNames="OSystem",key = "#id")
	public OperatingSystem selectOSystemById(Integer id) {
        return osystemMapper.selectByPrimaryKey(id);
	}

	@Override
	//@Cacheable(cacheNames="allOSystem",key = "#id")
	public List<OperatingSystem> selectAllOSystem(String name,String osCode) {
        List<OperatingSystem> operatingSystems = osystemMapper.selectAll(name, osCode);
        List<String> osCodes = new ArrayList<>();
        if(null != operatingSystems && operatingSystems.size() > 0){
            for(OperatingSystem system :operatingSystems){
				osCodes.add(system.getOsCode());
            }
			List<EdgeDevice> edgeDevices = edgeDeviceMapper.selectEdgeDeviceByOIds(osCodes);
            if(null != edgeDevices && edgeDevices.size() > 0){
				for(OperatingSystem system :operatingSystems){
					system.setIsConfig(0);
					for(EdgeDevice edgeDevice :edgeDevices){
						if(system.getOsCode().equals(edgeDevice.getOsCode())){
							system.setIsConfig(1);
						}
					}
				}
			}
        }
        return operatingSystems;
	}

	@Override
	//@CachePut(cacheNames="OSystem",key = "#record.id")
	public int updateOSystem(OperatingSystem record) {
		return osystemMapper.updateByPrimaryKey(record);
	}

    /**
     * @about delete software
     * @param id
     * @return
     */
	@Override
	//@Caching(evict={@CacheEvict(cacheNames="Software", key = "#id"),@CacheEvict(cacheNames="allSoftware",allEntries=true)})
	public int deleteSoftwareById(Integer id) {
		return softwareMapper.deleteByPrimaryKey(id);
	}

    /**
     * @about save software
     * @param record
     * @return
     */
	@Override
	//@CacheEvict(cacheNames="allSoftware",allEntries=true)
	public int insertSoftware(Software record) {
		record.insertGenerator();
	    record.setVersion(CommonParams.VERSION_DEFAULT);
		return softwareMapper.insert(record);
	}

    /**
     * @about software detail
     * @param id
     * @return
     */
	@Override
	//@Cacheable(cacheNames="Software",key = "#id")
	public Software selectSoftwareById(Integer id) {
		return softwareMapper.selectByPrimaryKey(id);
	}

	@Override
	//@Cacheable(cacheNames="allSoftware")
	public List<Software> selectAllSoftware(String sCode,String name) {
        List<Software> softwares = softwareMapper.selectAll(sCode, name);
        List<Integer> sIds = new ArrayList<>();
        if(null != softwares && softwares.size() > 0){
            for(Software software : softwares){
            	sIds.add(software.getId());
            }
			List<EdgeDeviceSoftware> edgeDeviceSoftwares = edgeDeviceSoftwareMapper.selectEdgeDeviceBySIds(sIds);
            if(null != edgeDeviceSoftwares && edgeDeviceSoftwares.size() > 0){
				for(Software software : softwares){
					software.setIsConfig(0);
					for(EdgeDeviceSoftware edgeDeviceSoftware : edgeDeviceSoftwares){
						if(software.getId().equals(edgeDeviceSoftware.getsId())){
							software.setIsConfig(1);
						}
					}
				}
			}
        }
        return softwares;
	}

    /**
     * @about update software
     * @param record
     * @return
     */
	@Override
	//@CachePut(cacheNames="Software",key = "#record.id")
	public int updateSoftware(Software record) {
		return softwareMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult<Hardware> selectPageHardware(PageRequest pageRequest, String hCode, String name) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<Hardware> sysMenus = selectAllHardware(hCode, name);
		return PageUtils.getPageResult(pageRequest, new PageInfo<Hardware>(sysMenus));

	}

	@Override
	public PageResult<Hardware> selectPageHardwareByType(String type, String hCode, String name, PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<Hardware> sysMenus = selectAllByType(type, hCode, name);
		return PageUtils.getPageResult(pageRequest, new PageInfo<Hardware>(sysMenus));
	}
    /**
     * @about select OSystem for page
     * @param pageRequest
     * @return
     */
	@Override
	public PageResult<OperatingSystem> selectPageOSystem(PageRequest pageRequest,String name,String osCode) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<OperatingSystem> sysMenus = selectAllOSystem(name,osCode);
		return PageUtils.getPageResult(pageRequest, new PageInfo<OperatingSystem>(sysMenus));
	}

    /**
     * @about select software
     * @param pageRequest
     * @return
     */
	@Override
	public PageResult<Software> selectPageSoftware(PageRequest pageRequest,String sCode,String name) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<Software> sysMenus = selectAllSoftware(sCode,name);
		return PageUtils.getPageResult(pageRequest, new PageInfo<Software>(sysMenus));
	}

	@Override
	//@CacheEvict(cacheNames="Hardware",allEntries=true)
	public int insertHardwareVersion(Integer id, String version) {
		return hardwareMapper.insertversion(id, version);
	}

	/**
	 * @About upgrade OSystem
	 * @param
	 * @return
	 */
	@Override
	//@CacheEvict(cacheNames="OSystem",allEntries=true)
	public int insertOSystemVersion(Integer id, String version,String fileName, String filePath) {
		String newVersion = autoUpgradeVersion(version);
        return osystemMapper.insertversion(id, newVersion, fileName, filePath);
	}

    /**
     * @about upgrade software
     * @param id
     * @param version
     * @return
     */
	@Override
	//@CacheEvict(cacheNames="allSoftware",allEntries=true)
	public ResponseModel insertSoftwareVersion(Integer id, String version,String fileName,String filePath) {
        String newVersion = autoUpgradeVersion(version);
        int insertversion = softwareMapper.insertversion(id, newVersion, fileName, filePath);
        return ResponseModel.ok(insertversion);
	}

}

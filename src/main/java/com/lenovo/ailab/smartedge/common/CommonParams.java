package com.lenovo.ailab.smartedge.common;

import java.io.File;

/**
 * Created by Administrator on 2018/10/17.
 */
public class CommonParams {

	private CommonParams() {
		throw new IllegalStateException("Utility class");
	}

	// value
	public static final int ACTIVATION_STATUS_SUCCESS = 0;
	public static final int ACTIVATION_STATUS_SUCCESSED = 1;
	public static final int ACTIVATION_STATUS_ERROR = -1;
	public static final int TOKEN_EXPTIME = 60 * 12;
	// platform
	public static final int PLATFORM_ANDROID = 82;
	public static final int PLATFORM_IOS = 83;
	public static final int PLATFORM_SERVER = 84;
	public static final int PLATFORM_BROWSER = 85;
	// params
	public static final String ID = "id";
	public static final String PLATFORMID = "platformId";
	public static final String WHITELIST = "whitelist";
	public static final String RESULT = "result";
	public static final String STATUS = "status";
	public static final String AK = "accesskey";
	public static final String SK = "secretkey";
	public static final String SIGNATURE = "signature";
	public static final String SIGNTIME = "signTime";
	public static final String LENOVO_TOKEN_NAME = "lenovoToken";
	public static final String TOKEN_NAME = "token";
	public static final String RESULT_ACCESSKEY = "accessKey";
	public static final String DEVICEUUID = "deviceUuid";
	public static final String APPFINGERPRINT = "appFingerPrint";
	public static final String XIP = "X-Real-IP";
	public static final String RESULT_SK = "secretKey";
	public static final String SERVICEKEY = "serviceKey";
	public static final String AUTHORIZEDKEY = "authorizedKey";
	public static final String EXPIRETIME = "expireTime";
	public static final String REMAINSEC = "remainSec";
	public static final String DEVICESTATUS = "deviceStatus";
	public static final String SERVICELIST = "serviceList";
	public static final String TOKEN = "token";
	public static final String RWFERER = "Referer";
	public static final String SYSTEM = "system";
	public static final String APPLICATIONID = "applicationId";
	public static final String DID = "developerId";
	public static final String AID = "accountId";
	public static final String USERNAME = "userName";
	public static final String SPLICING = "_";
	public static final String DEVICELOG = "log";
	public static final String JWTTOKEN = "jwttoken";
	public static final String JWTTOKEN_VALUE = "undefined";

	public static final String URL_APICORE = "/apicore";
	public static final String URL_AIMACHINE = "/aimachine-engine";
	
	public static final String BEGINTIME = "beginTime";

	public static final String MESSAGE = "message";
	
	public static final String SERVICE_TYPE="serviceType";
	public static final String SERVICE_APIS="apis";
	public static final String SERVICE_API_CANONICAL_NAME="apiCanonicalName";
	public static final String SERVICE_API_EXPIRE_SECONDS="expireInSeconds";
	public static final String SOFTWARE = "software";
	public static final String HARDWARE = "hardware";
	public static final String O_SYSTEM = "OSystem";
	public static final String DEPARTMENT = "department";
	public static final String DEPARTMENT_DEVICE = "departmentDevice";
	public static final String EDGE_TYPE_END = "end";
	public static final String EDGE_TYPE_EDGE = "edge";
	public static final String HARDWARE_CONFIG_FILE = "hardware_config_file";
	public static final String HARDWARE_INSTALL_THE_IMAGE_FILE = "hardware_install_the_image_file";
	public static final String FILE_TYPE_ZIP = ".zip";
	public static final String FILE_TYPE_RAR = ".rar";
	public static final String FILE_TYPE_JPG = ".jpg";
	public static final String STATIC_JPG = ".png";
	public static final String FILE_TYPE_JPEG = ".jpeg";
	public static final String SPLIT = ".";
	public static final String COLON = ":";
	public static final Integer END_TYPE_CAMERA = 51;
	public static final String VERSION_DEFAULT = "1.0.0";
	public static final String VERSION_DEFAULT_ZERO = "0.0.0";
	public static final String ADVANCE_REQUEST = "OPTIONS";
	public static final String ADD_EDGE_DEVICE_TYPE = "configuration";
	public static final String HEART_ERR_MSG = "请求超时";
	public static final String DEPARTMENT_DEVICE_STATUS = "正常";
	public static final String DEPARTMENT_DEVICE_NOT_STATUS = "异常";
	public static final String HEARTBEAT_URL = "getHeartbeat";
	public static final String HEARTBEAT_NO_TOKEN_URL = "/heartbeat";
	public static final String TOKEN_URL = "getToken";
	public static final String DEPARTMENT_DETAIL = "departmentDetail";
	public static final String DEPARTMENT_DEVICE_LIST = "getDeviceList";
	public static final String REQUEST_SMARTSTORE_GETDEPARTMENT = "smartStore";
	public static final String UPLOAD_COVERAGE_AREA_IMAGE = "checkCamera";
	public static final String INSTALL_IMAGE_STATUS = "当前记录已过期，请重新审核";
	public static final String DOWNLOAD_INSTALL_FILE = "paintermap.json";
	public static final String DOWNLOAD_INSTALL_FILE_MEDIA_JSON = "media.json";
	public static final String GET_DEVICE_USE_TYPE = "getDeviceUseType";
	public static final String GET_DEVICE_DEPLOYMENT_FILE = "getDeviceDeploymentFile";
	public static final String INSTALL_DEPLOYMENT_FILE_MEDIA = File.separator + "media.json";
	public static final String INSTALL_DEPLOYMENT_FILE_COMPRESSIONFILE = "c:"+File.separator + "CompressionFile" +File.separator;
	public static final String ENTERLINES_JSON = "enterlines.json";
	public static final String INSTALL_DEPLOYMENT_FILE_TEST = ".zip";
	public static final String HEARTBEAT_FOR_CONTROL_SERVER_URL = "notice";
	public static final String CAMERA_USE_TYPE_1 = "进店";
	public static final String CAMERA_USE_TYPE_2 = "热力";
	public static final String CONTROL_SERVER_TYPE = "init";
	public static final String NO_TOKEN = "noToken";
	public static final String GET_ALL_DEVICESTATUS = "getAllDeviceStatus";
	public static final String UPDATE_INSTALL_STATUS = "updateInstallStatus";
	public static final String SERVER_STATUS = "serverStatus";
	public static final String STATUS_FAIL = "保存失败!";
	public static final String SELECT_RESULT_FAIL = "修改配置失败！";
	public static final String SELECT_RESULT_SUCCESS = "修改配置成功！";
	public static final String UPDATE_CONFIG_CONTENT_NULL = "配置信息不可为空！";
	public static final String STOP_MODEL_FAIL = "模型停止操作失败！";
	public static final String GET_DEVICE_DETAIL = "getDeviceDetail";
	public static final String LINE_TYPE_DIRECTION_ENTER = "enter";
	public static final String LINE_TYPE_DIRECTION_RIGHT = "right";
	public static final String LINE_TYPE_DIRECTION_LEFT = "left";
	public static final String NO_TOKEN_EDGEDEVICES = "edgedevices";
	public static final String NO_TOKEN_DEPARTMENTS = "departments";
	public static final String MODEL = "model";
	public static final String UPDATE_EDGE_DEVICE_CONFIG = "updateEdgeDeviceConfig";
	public static final String DEPLOY_FILE = "deployFile";
	public static final String MODEL_SMARTSTORE = "smartstore";
	public static final String FILE_PATH = "filePath";
	public static final String ENTER_OR_PASSING_LINES = "EnterOrPassingLines";



}

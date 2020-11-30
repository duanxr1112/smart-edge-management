package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

public class DepartmentDevice implements Serializable {
	
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotBlank(message = "departmentId can not empty")
    private Integer dId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    //@NotBlank(message = " deviceId not empty")
    private Integer eId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String setting;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String serverCode;
    /*
     * 描述
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String description;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String param;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String jsonConfig;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private HashMap<String,List<String>> allParam;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String serverName;
    /**
     *  配置单名字
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String configName;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer isDrag;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String source;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer status;
    @JSONField(format="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JSONField(serialize=false)  
    private Boolean delFlag;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String installTheImage;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String installTheImageId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer installStatus;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String coverageAreaImage;

    private EdgeDevice edgeDevice;

    // 配置单多个新增
    private List<Integer> eIds;
    private String eType;
    private String thumbnailName;
    private String thumbnail;
    /**
     *  区别是从拓扑图界面添加
     */
    private String type;
    /**
     *  配置单内置参数
     */
    private List<EdgeDeviceHardware> hardwareList;
    private List<EdgeDeviceSoftware> softwareList;
    private List<OperatingSystem> oSystmeList;
    private String osCode;
    /*
     * 用途类型 ; 画线数据
     */
    private int useTy;
    private String useType;
    private String rtspurl;
    private List<CameraCoordinates> cameraLineList;

    private String btitdCode;
    private String serverConfig;


    public String getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(String serverConfig) {
        this.serverConfig = serverConfig;
    }

    public String getBtitdCode() {
        return btitdCode;
    }

    public void setBtitdCode(String btitdCode) {
        this.btitdCode = btitdCode;
    }

    public List<CameraCoordinates> getCameraLineList() {
        return cameraLineList;
    }

    public void setCameraLineList(List<CameraCoordinates> cameraLineList) {
        this.cameraLineList = cameraLineList;
    }

    public int getUseTy() {
        return useTy;
    }

    public void setUseTy(int useTy) {
        this.useTy = useTy;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getRtspurl() {
        return rtspurl;
    }

    public void setRtspurl(String rtspurl) {
        this.rtspurl = rtspurl;
    }

    public String getInstallTheImageId() {
        return installTheImageId;
    }

    public void setInstallTheImageId(String installTheImageId) {
        this.installTheImageId = installTheImageId;
    }

    public Integer getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(Integer installStatus) {
        this.installStatus = installStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCoverageAreaImage() {
        return coverageAreaImage;
    }

    public void setCoverageAreaImage(String coverageAreaImage) {
        this.coverageAreaImage = coverageAreaImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public HashMap<String, List<String>> getAllParam() {
        return allParam;
    }

    public void setAllParam(HashMap<String, List<String>> allParam) {
        this.allParam = allParam;
    }

    public String getInstallTheImage() {
        return installTheImage;
    }

    public void setInstallTheImage(String installTheImage) {
        this.installTheImage = installTheImage;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getOsCode() {
        return osCode;
    }

    public void setOsCode(String osCode) {
        this.osCode = osCode;
    }

    public List<OperatingSystem> getoSystmeList() {
        return oSystmeList;
    }

    public void setoSystmeList(List<OperatingSystem> oSystmeList) {
        this.oSystmeList = oSystmeList;
    }

    public List<EdgeDeviceHardware> getHardwareList() {
        return hardwareList;
    }

    public void setHardwareList(List<EdgeDeviceHardware> hardwareList) {
        this.hardwareList = hardwareList;
    }

    public List<EdgeDeviceSoftware> getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(List<EdgeDeviceSoftware> softwareList) {
        this.softwareList = softwareList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsDrag() {
        return isDrag;
    }

    public void setIsDrag(Integer isDrag) {
        this.isDrag = isDrag;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String geteType() {
        return eType;
    }

    public void seteType(String eType) {
        this.eType = eType;
    }

    public List<Integer> geteIds() {
        return eIds;
    }

    public void seteIds(List<Integer> eIds) {
        this.eIds = eIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }



    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
    }

    public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

	public EdgeDevice getEdgeDevice() {
		return edgeDevice;
	}

	public void setEdgeDevice(EdgeDevice edgeDevice) {
		this.edgeDevice = edgeDevice;
	}

    public void insertGenerator(){
        this.setServerCode(UUID.randomUUID().toString());

    }
}
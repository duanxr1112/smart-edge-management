package com.lenovo.ailab.smartedge.dao.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

public class DepartmentDeviceForBtit implements Serializable {
	
    private Integer id;
    private Integer eId;
    private Integer dId;

    /*
     * 描述
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String description;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String jsonConfig;

    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String deviceName;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String deviceCode;
    /**
     *  配置单名字
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String configName;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String status;
    @JSONField(format="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;
    @JSONField(serialize=false)  
    private Boolean delFlag;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String installTheImage;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String installTheImageId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String coverageAreaImage;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer installStatus;
    /*
     * 用途类型
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String type;
    private int isConfig;
    private int tenant;
    private String departmentName;
    private String ip;
    private String edgeServerIP;
    private Integer edgeServerId;
    private String edgeServerCode;
    private String btitdCode;

    public String getEdgeServerCode() {
        return edgeServerCode;
    }

    public void setEdgeServerCode(String edgeServerCode) {
        this.edgeServerCode = edgeServerCode;
    }

    public Integer getEdgeServerId() {
        return edgeServerId;
    }

    public void setEdgeServerId(Integer edgeServerId) {
        this.edgeServerId = edgeServerId;
    }

    public String getBtitdCode() {
        return btitdCode;
    }

    public void setBtitdCode(String btitdCode) {
        this.btitdCode = btitdCode;
    }

    public int getTenant() {
        return tenant;
    }

    public void setTenant(int tenant) {
        this.tenant = tenant;
    }

    public int getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(int isConfig) {
        this.isConfig = isConfig;
    }

    public String getInstallTheImageId() {
        return installTheImageId;
    }

    public void setInstallTheImageId(String installTheImageId) {
        this.installTheImageId = installTheImageId;
    }

    public String getCoverageAreaImage() {
        return coverageAreaImage;
    }

    public void setCoverageAreaImage(String coverageAreaImage) {
        this.coverageAreaImage = coverageAreaImage;
    }

    public Integer getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(Integer installStatus) {
        this.installStatus = installStatus;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getEdgeServerIP() {
        return edgeServerIP;
    }

    public void setEdgeServerIP(String edgeServerIP) {
        this.edgeServerIP = edgeServerIP;
    }

    public String getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getInstallTheImage() {
        return installTheImage;
    }

    public void setInstallTheImage(String installTheImage) {
        this.installTheImage = installTheImage;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
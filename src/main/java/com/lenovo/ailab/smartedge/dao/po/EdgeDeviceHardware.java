package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

public class EdgeDeviceHardware implements Serializable {
	
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "deviceId can not empty")
    private Integer eId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "hardwareId can not empty")
    private Integer hId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String setting;

    @JSONField(format="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;
    @JSONField(serialize=false)
    private Boolean delFlag;
    
    private Hardware hardware;
    private String hCode;
    private String hName;
    private String hType;
    private String hParameter;
    private String hStatus;
    private String hJsonConfig;

    public String gethJsonConfig() {
        return hJsonConfig;
    }

    public void sethJsonConfig(String hJsonConfig) {
        this.hJsonConfig = hJsonConfig;
    }

    public String gethStatus() {
        return hStatus;
    }

    public void sethStatus(String hStatus) {
        this.hStatus = hStatus;
    }

    public String gethParameter() {
        return hParameter;
    }

    public void sethParameter(String hParameter) {
        this.hParameter = hParameter;
    }

    public String gethCode() {
        return hCode;
    }

    public void sethCode(String hCode) {
        this.hCode = hCode;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethType() {
        return hType;
    }

    public void sethType(String hType) {
        this.hType = hType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
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

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}
}
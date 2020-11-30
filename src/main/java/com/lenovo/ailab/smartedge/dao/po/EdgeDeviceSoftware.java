package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EdgeDeviceSoftware implements Serializable {
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "deviceId can not empty")
    private Integer eId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "softwareId can not empty")
    private Integer sId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String setting;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JSONField(serialize=false)
    private Boolean delFlag;
    private Software software;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String sName;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String sCode;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String sParameter;
    private String version;
    private List<String> allParam;
    private String fileName;
    private String filePath;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getAllParam() {
        return allParam;
    }

    public void setAllParam(List<String> allParam) {
        this.allParam = allParam;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getsParameter() {
        return sParameter;
    }

    public void setsParameter(String sParameter) {
        this.sParameter = sParameter;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
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

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
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

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

}
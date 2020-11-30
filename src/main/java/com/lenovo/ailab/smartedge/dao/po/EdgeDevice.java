package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EdgeDevice implements Serializable {
	
    private Integer id;

    private String eCode;
    
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotBlank
    @Pattern(regexp="(edge|end)",message = "Accept only 'edge' or 'end'")
    private String type;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer typeId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "name can not empty")
    private String name;
    
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "osCode can not empty")
    private String osCode;
    
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotBlank(message = "version can not empty")
    @Pattern(regexp="^[0-9]*[0-9]*(\\.)*[0-9]*[0-9]{1}\\.[0-9]*[0-9]{1}$")
    private String osVersion;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JSONField(serialize=false)
    private Boolean delFlag;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer isConfig;
    /**
     *  缩略图
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String thumbnail;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String thumbnailName;
    private OperatingSystem oSystem;
    private List<EdgeDeviceHardware> hardwareList;
    private List<EdgeDeviceSoftware> softwareList;

    /**
     *  操作系统相关字段
     */
    private Integer osId;
    private String osType;
    private String osName;
    private String osParameter;
    /**
     *  软件硬件id;
     */
    private List<Integer> sId;
    private Integer hId;

    private HashMap<String,List<String>> AllParam;

    public HashMap<String, List<String>> getAllParam() {
        return AllParam;
    }

    public void setAllParam(HashMap<String, List<String>> allParam) {
        AllParam = allParam;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getOsParameter() {
        return osParameter;
    }

    public void setOsParameter(String osParameter) {
        this.osParameter = osParameter;
    }

    public Integer getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(Integer isConfig) {
        this.isConfig = isConfig;
    }

    public EdgeDevice(){
    	hardwareList=new ArrayList<EdgeDeviceHardware>();
    	softwareList=new ArrayList<EdgeDeviceSoftware>();
    }
    public Integer getOsId() {
        return osId;
    }

    public void setOsId(Integer osId) {
        this.osId = osId;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public List<Integer> getsId() {
        return sId;
    }

    public void setsId(List<Integer> sId) {
        this.sId = sId;
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode == null ? null : eCode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOsCode() {
        return osCode;
    }

    public void setOsCode(String osCode) {
        this.osCode = osCode == null ? null : osCode.trim();
    }

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
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


	public OperatingSystem getoSystem() {
		return oSystem;
	}


	public void setoSystem(OperatingSystem oSystem) {
		this.oSystem = oSystem;
	}

    public void insertGenerator(){
        this.seteCode(UUID.randomUUID().toString());
    }
}
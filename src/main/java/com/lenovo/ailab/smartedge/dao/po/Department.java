package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

public class Department implements Serializable {
    private Integer id;
    private String dCode;

    private String btitdCode;

    @NotBlank(message = "name can not empty")
    private String name;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @Size(min=1, max=8)
    private int tenant;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String info;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String deviceDeployInfo;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String backgroundImageName;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String backgroundImagePath;
    @JSONField(format="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;
    @JSONField(serialize=false)  
    private Boolean delFlag;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String address;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String region;
    private String data;

    private String effectPictureName;
    private String effectPicturePath;

    private List<DepartmentDevice> departmentDeviceList;
    
    private List<DepartmentDeviceTopology> departmentDeviceTopologyList;
    private DepartmentDeviceTopology departmentDeviceTopologys;

    /**
     *  是否配置过配置单
     */
    private int isConfig;
    /**
     *  是否构建成功拓扑图
     */
    private int isCompleteTopology;
    /**
     *  服务器的数量
     */
    private int endDeviceNum;
    private int edgeDeviceNum;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     *  服务器的数量
     */
    private int normalDeviceNum;
    private int offNormalDeviceNum;
    private int departmentDeviceTypeNum;
    private int departmentAllDeviceNum;

    public int getDepartmentAllDeviceNum() {
        return departmentAllDeviceNum;
    }

    public void setDepartmentAllDeviceNum(int departmentAllDeviceNum) {
        this.departmentAllDeviceNum = departmentAllDeviceNum;
    }

    public int getNormalDeviceNum() {
        return normalDeviceNum;
    }

    public void setNormalDeviceNum(int normalDeviceNum) {
        this.normalDeviceNum = normalDeviceNum;
    }

    public int getOffNormalDeviceNum() {
        return offNormalDeviceNum;
    }

    public void setOffNormalDeviceNum(int offNormalDeviceNum) {
        this.offNormalDeviceNum = offNormalDeviceNum;
    }

    public int getDepartmentDeviceTypeNum() {
        return departmentDeviceTypeNum;
    }

    public void setDepartmentDeviceTypeNum(int departmentDeviceTypeNum) {
        this.departmentDeviceTypeNum = departmentDeviceTypeNum;
    }

    public String getEffectPictureName() {
        return effectPictureName;
    }

    public void setEffectPictureName(String effectPictureName) {
        this.effectPictureName = effectPictureName;
    }

    public String getEffectPicturePath() {
        return effectPicturePath;
    }

    public void setEffectPicturePath(String effectPicturePath) {
        this.effectPicturePath = effectPicturePath;
    }

    public String getBtitdCode() {
        return btitdCode;
    }

    public void setBtitdCode(String btitdCode) {
        this.btitdCode = btitdCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getEndDeviceNum() {
        return endDeviceNum;
    }

    public void setEndDeviceNum(int endDeviceNum) {
        this.endDeviceNum = endDeviceNum;
    }

    public int getEdgeDeviceNum() {
        return edgeDeviceNum;
    }

    public void setEdgeDeviceNum(int edgeDeviceNum) {
        this.edgeDeviceNum = edgeDeviceNum;
    }

    public int getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(int isConfig) {
        this.isConfig = isConfig;
    }

    public int getIsCompleteTopology() {
        return isCompleteTopology;
    }

    public void setIsCompleteTopology(int isCompleteTopology) {
        this.isCompleteTopology = isCompleteTopology;
    }

    public DepartmentDeviceTopology getDepartmentDeviceTopologys() {
        return departmentDeviceTopologys;
    }

    public void setDepartmentDeviceTopologys(DepartmentDeviceTopology departmentDeviceTopologys) {
        this.departmentDeviceTopologys = departmentDeviceTopologys;
    }

    public Department(){
    	departmentDeviceList=new ArrayList<DepartmentDevice>();
    	departmentDeviceTopologyList=new ArrayList<DepartmentDeviceTopology>();
    }

    public String getBackgroundImageName() {
        return backgroundImageName;
    }

    public void setBackgroundImageName(String backgroundImageName) {
        this.backgroundImageName = backgroundImageName;
    }

    public String getAddress() {
        return address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode == null ? null : dCode.trim();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getDeviceDeployInfo() {
        return deviceDeployInfo;
    }

    public void setDeviceDeployInfo(String deviceDeployInfo) {
        this.deviceDeployInfo = deviceDeployInfo == null ? null : deviceDeployInfo.trim();
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath == null ? null : backgroundImagePath.trim();
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

	public List<DepartmentDevice> getDepartmentDeviceList() {
		return departmentDeviceList;
	}

	public void setDepartmentDeviceList(List<DepartmentDevice> departmentDeviceList) {
		this.departmentDeviceList = departmentDeviceList;
	}

	public List<DepartmentDeviceTopology> getDepartmentDeviceTopologyList() {
		return departmentDeviceTopologyList;
	}

	public void setDepartmentDeviceTopologyList(List<DepartmentDeviceTopology> departmentDeviceTopologyList) {
		this.departmentDeviceTopologyList = departmentDeviceTopologyList;
	}
    public void insertGenerator(){
        this.setdCode(UUID.randomUUID().toString());

    }

    public int getTenant() {
        return tenant;
    }

    public void setTenant(int tenant) {
        this.tenant = tenant;
    }
}
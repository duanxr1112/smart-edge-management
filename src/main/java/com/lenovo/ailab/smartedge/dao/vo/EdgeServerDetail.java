package com.lenovo.ailab.smartedge.dao.vo;

import com.lenovo.ailab.smartedge.dao.po.CaremaPo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/13
 * @About:
 */
public class EdgeServerDetail implements Serializable {

    private Integer id;
    private Integer edgeServerId;
    private String edgeServerName;
    private String edgeServerCode;
    private String modelName;
    private Integer modelId;
    private String modelCode;
    private String softwareCode;
    private String modelDownUrl;
    private String status;
    private String version;
    /*
     *  是否升级 0 一致  1 可升级
     */
    private int flag;
    private int canInitFlag;
    private int parentId;
    /**
     *  选择可用摄像头
     */
    private List<CaremaPo> caremaList;
    private List<EdgeServerDetail> children;

    public List<CaremaPo> getCaremaList() {
        return caremaList;
    }

    public void setCaremaList(List<CaremaPo> caremaList) {
        this.caremaList = caremaList;
    }

    public String getEdgeServerCode() {
        return edgeServerCode;
    }

    public void setEdgeServerCode(String edgeServerCode) {
        this.edgeServerCode = edgeServerCode;
    }

    public int getCanInitFlag() {
        return canInitFlag;
    }

    public void setCanInitFlag(int canInitFlag) {
        this.canInitFlag = canInitFlag;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getSoftwareCode() {
        return softwareCode;
    }

    public void setSoftwareCode(String softwareCode) {
        this.softwareCode = softwareCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<EdgeServerDetail> getChildren() {
        return children;
    }

    public void setChildren(List<EdgeServerDetail> children) {
        this.children = children;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public Integer getEdgeServerId() {
        return edgeServerId;
    }

    public void setEdgeServerId(Integer edgeServerId) {
        this.edgeServerId = edgeServerId;
    }

    public String getEdgeServerName() {
        return edgeServerName;
    }

    public void setEdgeServerName(String edgeServerName) {
        this.edgeServerName = edgeServerName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDownUrl() {
        return modelDownUrl;
    }

    public void setModelDownUrl(String modelDownUrl) {
        this.modelDownUrl = modelDownUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EdgeServerDetail other = (EdgeServerDetail) obj;
        if (other.getSoftwareCode() == null) {
            return false;
        }
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

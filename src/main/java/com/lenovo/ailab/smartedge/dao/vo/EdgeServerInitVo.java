package com.lenovo.ailab.smartedge.dao.vo;

import java.util.List;

public class EdgeServerInitVo {

    private String modelCode;
    private String softwareCode;
    private String content;
    private String version;
    private List<Integer> caremaList;
    private List<EdgeServerInitVo> edgeServerInitList;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getCaremaList() {
        return caremaList;
    }

    public void setCaremaList(List<Integer> caremaList) {
        this.caremaList = caremaList;
    }

    public List<EdgeServerInitVo> getEdgeServerInitList() {
        return edgeServerInitList;
    }

    public void setEdgeServerInitList(List<EdgeServerInitVo> edgeServerInitList) {
        this.edgeServerInitList = edgeServerInitList;
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
}

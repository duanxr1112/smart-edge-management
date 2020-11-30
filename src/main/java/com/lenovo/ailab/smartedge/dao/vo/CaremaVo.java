package com.lenovo.ailab.smartedge.dao.vo;

import com.lenovo.ailab.smartedge.dao.po.CameraCoordinates;

import java.util.List;

public class CaremaVo {
    private Integer caremaId;
    private String caremaName;
    private String code;
    private List<CameraCoordinates> lineList;
    private String url;
    private Integer cameraType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCameraType() {
        return cameraType;
    }

    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }

    public List<CameraCoordinates> getLineList() {
        return lineList;
    }

    public void setLineList(List<CameraCoordinates> lineList) {
        this.lineList = lineList;
    }

    public Integer getCaremaId() {
        return caremaId;
    }

    public void setCaremaId(Integer caremaId) {
        this.caremaId = caremaId;
    }

    public String getCaremaName() {
        return caremaName;
    }

    public void setCaremaName(String caremaName) {
        this.caremaName = caremaName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

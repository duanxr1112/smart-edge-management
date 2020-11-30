package com.lenovo.ailab.smartedge.dao.vo;

import com.lenovo.ailab.smartedge.dao.po.CameraCoordinates;

import java.util.List;

public class CaremaVoForLineList {
    private List<CameraCoordinates> lineList;
    private String url;
    private Integer cameraType;

    public List<CameraCoordinates> getLineList() {
        return lineList;
    }

    public void setLineList(List<CameraCoordinates> lineList) {
        this.lineList = lineList;
    }

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
}

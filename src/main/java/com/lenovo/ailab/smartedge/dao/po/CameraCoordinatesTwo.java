package com.lenovo.ailab.smartedge.dao.po;

import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/4/2
 * @About: 上传门店摄像头部署的箭头指示
 */
public class CameraCoordinatesTwo {

   private List<CameraCoordinates> lineList;

   private String url;
   private int cameraType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCameraType() {
        return cameraType;
    }

    public void setCameraType(int cameraType) {
        this.cameraType = cameraType;
    }

    public List<CameraCoordinates> getLineList() {
        return lineList;
    }

    public void setLineList(List<CameraCoordinates> lineList) {
        this.lineList = lineList;
    }
}

package com.lenovo.ailab.smartedge.dao.vo;

import com.lenovo.ailab.smartedge.dao.po.CameraCoordinates;

import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/3/27
 * @About:
 */
public class CameraInstallVo {
    private int CameraID;
    private String coverageAreaImage;
    private String installTheImage;
    private String installTheImageId;
    private int installStatus;
    private List<CameraCoordinates> lineList;

    public List<CameraCoordinates> getLineList() {
        return lineList;
    }

    public void setLineList(List<CameraCoordinates> lineList) {
        this.lineList = lineList;
    }

    public String getInstallTheImageId() {
        return installTheImageId;
    }

    public void setInstallTheImageId(String installTheImageId) {
        this.installTheImageId = installTheImageId;
    }


    public int getCameraID() {
        return CameraID;
    }

    public void setCameraID(int cameraID) {
        CameraID = cameraID;
    }

    public String getCoverageAreaImage() {
        return coverageAreaImage;
    }

    public void setCoverageAreaImage(String coverageAreaImage) {
        this.coverageAreaImage = coverageAreaImage;
    }

    public String getInstallTheImage() {
        return installTheImage;
    }

    public void setInstallTheImage(String installTheImage) {
        this.installTheImage = installTheImage;
    }

    public int getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(int installStatus) {
        this.installStatus = installStatus;
    }
}

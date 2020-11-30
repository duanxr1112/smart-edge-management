package com.lenovo.ailab.smartedge.dao.po;

import com.lenovo.ailab.smartedge.dao.vo.*;

import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/3/26
 * @About:
 */
public class EquipmentDeploymentDetails {
    private Integer departmentId;
    private List<CameraDetailVo> cameraDetailVoList;
    private List<CameraDetailForLandMarksVo> cameraDetailForLandMarksVoList;
    private List<CameraInstallVo> cameraInstallImage;


    public List<CameraInstallVo> getCameraInstallImage() {
        return cameraInstallImage;
    }

    public void setCameraInstallImage(List<CameraInstallVo> cameraInstallImage) {
        this.cameraInstallImage = cameraInstallImage;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public List<CameraDetailVo> getCameraDetailVoList() {
        return cameraDetailVoList;
    }

    public void setCameraDetailVoList(List<CameraDetailVo> cameraDetailVoList) {
        this.cameraDetailVoList = cameraDetailVoList;
    }

    public List<CameraDetailForLandMarksVo> getCameraDetailForLandMarksVoList() {
        return cameraDetailForLandMarksVoList;
    }

    public void setCameraDetailForLandMarksVoList(List<CameraDetailForLandMarksVo> cameraDetailForLandMarksVoList) {
        this.cameraDetailForLandMarksVoList = cameraDetailForLandMarksVoList;
    }


}

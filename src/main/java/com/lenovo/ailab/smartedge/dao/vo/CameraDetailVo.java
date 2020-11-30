package com.lenovo.ailab.smartedge.dao.vo;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/3/24
 * @About:
 */
public class CameraDetailVo {
    private int CameraID;
    private double ScaleX;
    private double ScaleY;
    private double AngleYaw;
    private double AnglePitch;
    private double Height;
    private String Rtsp;
    private int LandmarkId;
    private LadderVo Ladder;

    public String getRtsp() {
        return Rtsp;
    }

    public void setRtsp(String rtsp) {
        Rtsp = rtsp;
    }

    public int getCameraID() {
        return CameraID;
    }

    public void setCameraID(int cameraID) {
        CameraID = cameraID;
    }

    public double getScaleX() {
        return ScaleX;
    }

    public void setScaleX(double scaleX) {
        ScaleX = scaleX;
    }

    public double getScaleY() {
        return ScaleY;
    }

    public void setScaleY(double scaleY) {
        ScaleY = scaleY;
    }

    public double getAngleYaw() {
        return AngleYaw;
    }

    public void setAngleYaw(double angleYaw) {
        AngleYaw = angleYaw;
    }

    public double getAnglePitch() {
        return AnglePitch;
    }

    public void setAnglePitch(double anglePitch) {
        AnglePitch = anglePitch;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public int getLandmarkId() {
        return LandmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        LandmarkId = landmarkId;
    }

    public LadderVo getLadder() {
        return Ladder;
    }

    public void setLadder(LadderVo ladder) {
        Ladder = ladder;
    }
}

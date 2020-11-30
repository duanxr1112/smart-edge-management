package com.lenovo.ailab.smartedge.dao.vo;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/3/24
 * @About:
 */
public class CameraDetailForLandMarksVo {
    private int LandMarkID;
    private double pixLTX;
    private double pixLTY;
    private double pixRBX;
    private double pixRBY;
    private double SizeX;
    private double SizeY;
    private double Height;

    public int getLandMarkID() {
        return LandMarkID;
    }

    public void setLandMarkID(int landMarkID) {
        LandMarkID = landMarkID;
    }

    public double getPixLTX() {
        return pixLTX;
    }

    public void setPixLTX(double pixLTX) {
        this.pixLTX = pixLTX;
    }

    public double getPixLTY() {
        return pixLTY;
    }

    public void setPixLTY(double pixLTY) {
        this.pixLTY = pixLTY;
    }

    public double getPixRBX() {
        return pixRBX;
    }

    public void setPixRBX(double pixRBX) {
        this.pixRBX = pixRBX;
    }

    public double getPixRBY() {
        return pixRBY;
    }

    public void setPixRBY(double pixRBY) {
        this.pixRBY = pixRBY;
    }

    public double getSizeX() {
        return SizeX;
    }

    public void setSizeX(double sizeX) {
        SizeX = sizeX;
    }

    public double getSizeY() {
        return SizeY;
    }

    public void setSizeY(double sizeY) {
        SizeY = sizeY;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }
}

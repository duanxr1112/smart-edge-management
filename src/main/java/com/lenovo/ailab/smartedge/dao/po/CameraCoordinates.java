package com.lenovo.ailab.smartedge.dao.po;


/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/4/2
 * @About: 上传门店摄像头部署的箭头指示
 */
public class CameraCoordinates {

    /**
     *  0 : 进店线 1：路过线
     */
     private int lineType ;
     private float arrowsStartX;
     private float arrowsStartY;
     private float arrowsEndX;
     private float arrowsEndY;
     private float startX;
     private float startY;
     private float endX;
     private float endY;

    /**
     *  方向
     */
    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int lineType) {
        this.lineType = lineType;
    }

    public float getArrowsStartX() {
        return arrowsStartX;
    }

    public void setArrowsStartX(float arrowsStartX) {
        this.arrowsStartX = arrowsStartX;
    }

    public float getArrowsStartY() {
        return arrowsStartY;
    }

    public void setArrowsStartY(float arrowsStartY) {
        this.arrowsStartY = arrowsStartY;
    }

    public float getArrowsEndX() {
        return arrowsEndX;
    }

    public void setArrowsEndX(float arrowsEndX) {
        this.arrowsEndX = arrowsEndX;
    }

    public float getArrowsEndY() {
        return arrowsEndY;
    }

    public void setArrowsEndY(float arrowsEndY) {
        this.arrowsEndY = arrowsEndY;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}

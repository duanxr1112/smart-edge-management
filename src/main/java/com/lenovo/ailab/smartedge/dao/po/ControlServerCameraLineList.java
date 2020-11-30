package com.lenovo.ailab.smartedge.dao.po;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/14
 * @About:  摄像头画线数据
 */
public class ControlServerCameraLineList {

     private int[] lineStart;
     private int[] lineEnd;
     private int[] directionStart;
     private int[] directionEnd;

    public int[] getLineStart() {
        return lineStart;
    }

    public void setLineStart(int[] lineStart) {
        this.lineStart = lineStart;
    }

    public int[] getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(int[] lineEnd) {
        this.lineEnd = lineEnd;
    }

    public int[] getDirectionStart() {
        return directionStart;
    }

    public void setDirectionStart(int[] directionStart) {
        this.directionStart = directionStart;
    }

    public int[] getDirectionEnd() {
        return directionEnd;
    }

    public void setDirectionEnd(int[] directionEnd) {
        this.directionEnd = directionEnd;
    }
}

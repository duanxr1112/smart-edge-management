package com.lenovo.ailab.smartedge.dao.po;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/14
 * @About: 摄像头视频流地址
 */
public class ControlServerCamList {
    private int id;
    private String uri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

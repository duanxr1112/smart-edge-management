package com.lenovo.ailab.smartedge.dao.po;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/14
 * @About:  tx2监听的摄像头的心跳地址
 */
public class ControlServerCamera {
    /**
     *   摄像头code  视频流地址：host
     */
    private String code;
    private String host;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

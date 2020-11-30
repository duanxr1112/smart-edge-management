package com.lenovo.ailab.smartedge.dao.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/13
 * @About:
 */
public class ServerStatus  implements Serializable {
    private String serverCode;
    private int status;


    private List<ServerStatus> serverStatusList;

    public List<ServerStatus> getServerStatusList() {
        return serverStatusList;
    }

    public void setServerStatusList(List<ServerStatus> serverStatusList) {
        this.serverStatusList = serverStatusList;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

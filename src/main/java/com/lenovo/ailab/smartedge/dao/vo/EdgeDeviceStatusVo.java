package com.lenovo.ailab.smartedge.dao.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/2/19
 * @About:
 */
public class EdgeDeviceStatusVo implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private int status;
    /**
     *  检测时间
     */
    private String detectionDate;
    private String errorMsg;

    /**
     * 心跳机制携带参数
     */
    private HashMap<String,String> message;

    private List<EdgeDeviceStatusVo>  edgeDeviceStatusVoList;

    public List<EdgeDeviceStatusVo> getEdgeDeviceStatusVoList() {
        return edgeDeviceStatusVoList;
    }

    public void setEdgeDeviceStatusVoList(List<EdgeDeviceStatusVo> edgeDeviceStatusVoList) {
        this.edgeDeviceStatusVoList = edgeDeviceStatusVoList;
    }

    public HashMap<String, String> getMessage() {
        return message;
    }

    public void setMessage(HashMap<String, String> message) {
        this.message = message;
    }

    public String getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(String detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

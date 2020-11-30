package com.lenovo.ailab.smartedge.dao.po;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/15
 * @About:
 */
public class UpdateDeviceConfig {
    private Integer id;
    private String name;
    private String code;
    private String filePath;
    private String fileName;
    private Integer edgeDeviceId;
    private String edgeDeviceName;
    private Integer dId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getEdgeDeviceId() {
        return edgeDeviceId;
    }

    public void setEdgeDeviceId(Integer edgeDeviceId) {
        this.edgeDeviceId = edgeDeviceId;
    }

    public String getEdgeDeviceName() {
        return edgeDeviceName;
    }

    public void setEdgeDeviceName(String edgeDeviceName) {
        this.edgeDeviceName = edgeDeviceName;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}

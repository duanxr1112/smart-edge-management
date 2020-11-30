package com.lenovo.ailab.smartedge.dao.vo;

import java.util.List;

public class SoftwareForDevice {
    private Integer id;
    private String name;
    private List<String> softwareConfigCode;
    private String code;
    private String url;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getSoftwareConfigCode() {
        return softwareConfigCode;
    }

    public String getCode() {
        return code;
    }

    public void setSoftwareConfigCode(List<String> softwareConfigCode) {
        this.softwareConfigCode = softwareConfigCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

}

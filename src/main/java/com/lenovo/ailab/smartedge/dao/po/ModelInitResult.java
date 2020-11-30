package com.lenovo.ailab.smartedge.dao.po;


import java.util.List;

public class ModelInitResult{
    private String code;
    private String name;
    private String shell;
    private String content;
    private String softwareConfigCode;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public String getSoftwareConfigCode() {
        return softwareConfigCode;
    }

    public void setSoftwareConfigCode(String softwareConfigCode) {
        this.softwareConfigCode = softwareConfigCode;
    }
}

package com.lenovo.ailab.smartedge.dao.vo;

import java.io.Serializable;

public class SoftwareConfig implements Serializable {

   private Integer id;
   private Integer sId;
   private Integer dId;
   private Integer edgeDeviceId;
   private String code;
   private String sName;
   private String content;
   private String version;
   private String sUrl;
   private String runScript;
   private String stopScript;
   private int canInitFlag;
   private int delFlag;

   // 软件详情
   private String sCode;

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getStopScript() {
        return stopScript;
    }

    public void setStopScript(String stopScript) {
        this.stopScript = stopScript;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getRunScript() {
        return runScript;
    }

    public void setRunScript(String runScript) {
        this.runScript = runScript;
    }

    public int getCanInitFlag() {
        return canInitFlag;
    }

    public void setCanInitFlag(int canInitFlag) {
        this.canInitFlag = canInitFlag;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public Integer getEdgeDeviceId() {
        return edgeDeviceId;
    }

    public void setEdgeDeviceId(Integer edgeDeviceId) {
        this.edgeDeviceId = edgeDeviceId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

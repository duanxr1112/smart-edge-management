package com.lenovo.ailab.smartedge.dao.po;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

public class DepartmentDeviceTopology implements Serializable {
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer dId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String dCode;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer fromDeviceId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer toDeviceId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Date createTime;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Date updateTime;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Boolean delFlag;
    private String jsonParam;
    private String data;

    private String param;
    private String description;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode == null ? null : dCode.trim();
    }

    public Integer getFromDeviceId() {
        return fromDeviceId;
    }

    public void setFromDeviceId(Integer fromDeviceId) {
        this.fromDeviceId = fromDeviceId;
    }

    public Integer getToDeviceId() {
        return toDeviceId;
    }

    public void setToDeviceId(Integer toDeviceId) {
        this.toDeviceId = toDeviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
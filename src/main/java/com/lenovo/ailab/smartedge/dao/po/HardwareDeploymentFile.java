package com.lenovo.ailab.smartedge.dao.po;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/2/11
 * @About:
 */
public class HardwareDeploymentFile implements Serializable {
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer dId;
    private String btitdCode;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String version;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String deploymentFilePath;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String deploymentFileName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String description;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Long deploymentFileSize;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBtitdCode() {
        return btitdCode;
    }

    public void setBtitdCode(String btitdCode) {
        this.btitdCode = btitdCode;
    }

    public Long getDeploymentFileSize() {
        return deploymentFileSize;
    }

    public void setDeploymentFileSize(Long deploymentFileSize) {
        this.deploymentFileSize = deploymentFileSize;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getDeploymentFilePath() {
        return deploymentFilePath;
    }

    public void setDeploymentFilePath(String deploymentFilePath) {
        this.deploymentFilePath = deploymentFilePath;
    }

    public String getDeploymentFileName() {
        return deploymentFileName;
    }

    public void setDeploymentFileName(String deploymentFileName) {
        this.deploymentFileName = deploymentFileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

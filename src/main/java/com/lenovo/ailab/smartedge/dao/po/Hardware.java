package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Hardware implements Serializable {
	
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String hCode;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    //@NotBlank(message = "version can not empty")
    @Pattern(regexp="^[0-9]*[0-9]*(\\.)*[0-9]*[0-9]{1}\\.[0-9]*[0-9]{1}$")
    private String version;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotEmpty(message = "name can not empty")
   // @Pattern(regexp="^[a-zA-Z0-9_ ]{4,32}$")
    private String name;
   
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @NotBlank
    @Pattern(regexp="(edge|end)",message = "Accept only 'edge' or 'end'")
    private String type;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer endTypeId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String endTypeName;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String attrs;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String setting;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @JSONField(format = "#0.00")
    private Float price;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JSONField(serialize=false)
    private Boolean delFlag;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String description;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String parameter;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer eId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer isConfig;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String status;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String jsonConfig;

    public String getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(Integer isConfig) {
        this.isConfig = isConfig;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getEndTypeName() {
        return endTypeName;
    }

    public void setEndTypeName(String endTypeName) {
        this.endTypeName = endTypeName;
    }

    public Integer getEndTypeId() {
        return endTypeId;
    }

    public void setEndTypeId(Integer endTypeId) {
        this.endTypeId = endTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gethCode() {
        return hCode;
    }

    public void sethCode(String hCode) {
        this.hCode = hCode == null ? null : hCode.trim();
    }

    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs == null ? null : attrs.trim();
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
    
    public void insertGenerator(){
    	this.sethCode(UUID.randomUUID().toString());
    }
}
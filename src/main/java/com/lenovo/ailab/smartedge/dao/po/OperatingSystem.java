package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OperatingSystem implements Serializable {
	private Integer id;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String osCode;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	@NotEmpty(message = "type can not empty")
	//@Pattern(regexp="(linux|windows|ios)")
	private String type;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	@NotEmpty(message = "name can not empty")
	//@Pattern(regexp="^[a-zA-Z0-9_ ]{4,32}$")
	private String name;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	//@NotEmpty(message = "version can not empty")
	@Pattern(regexp="^[0-9]*[0-9]*(\\.)*[0-9]*[0-9]{1}\\.[0-9]*[0-9]{1}$")
	private String version;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String fileName;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String filePath;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	@JSONField(serialize = false)
	private Boolean delFlag;
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private String description;
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private String parameter;
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private String jsonConfig;
	@TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer isConfig;
	@TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer eId;

	public String getJsonConfig() {
		return jsonConfig;
	}

	public void setJsonConfig(String jsonConfig) {
		this.jsonConfig = jsonConfig;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Integer getIsConfig() {
		return isConfig;
	}

	public void setIsConfig(Integer isConfig) {
		this.isConfig = isConfig;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOsCode() {
		return osCode;
	}

	public void setOsCode(String osCode) {
		this.osCode = osCode == null ? null : osCode.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath == null ? null : filePath.trim();
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
	    	this.setOsCode(UUID.randomUUID().toString());
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
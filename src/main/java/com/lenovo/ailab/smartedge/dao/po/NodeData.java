package com.lenovo.ailab.smartedge.dao.po;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/1/10
 * @About:
 */
public class NodeData implements Serializable {
    private Integer id;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String type;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String toPort;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String category;
    private String nId;  // 节点id
    private String dId;  // 店铺id
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String name;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String idx;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String textimg;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer key;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer kye;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String code;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String loc;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String linkFromPortIdProperty;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String linkToPortIdProperty;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String source;

    /*
     * 描述
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String description;
    private String parameter;
    private String jsonConfig;
    private String useType;
    /*
     *  摄像头 画线的数据
     */
    private List<CameraCoordinates> cameraLineList;
    private String rtspurl;


    public String getRtspurl() {
        return rtspurl;
    }

    public void setRtspurl(String rtspurl) {
        this.rtspurl = rtspurl;
    }

    public List<CameraCoordinates> getCameraLineList() {
        return cameraLineList;
    }

    public void setCameraLineList(List<CameraCoordinates> cameraLineList) {
        this.cameraLineList = cameraLineList;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public String getParameter() {
        return parameter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getKye() {
        return kye;
    }

    public void setKye(Integer kye) {
        this.kye = kye;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLinkFromPortIdProperty() {
        return linkFromPortIdProperty;
    }

    public void setLinkFromPortIdProperty(String linkFromPortIdProperty) {
        this.linkFromPortIdProperty = linkFromPortIdProperty;
    }

    public String getLinkToPortIdProperty() {
        return linkToPortIdProperty;
    }

    public void setLinkToPortIdProperty(String linkToPortIdProperty) {
        this.linkToPortIdProperty = linkToPortIdProperty;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToPort() {
        return toPort;
    }

    public void setToPort(String toPort) {
        this.toPort = toPort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getTextimg() {
        return textimg;
    }

    public void setTextimg(String textimg) {
        this.textimg = textimg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}

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
public class DepartmentDeviceTopologyParam implements Serializable {
    private Integer id;
    private Integer dId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer fromDeviceId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer toDeviceId;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String linkFromPortIdProperty;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String linkToPortIdProperty;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private List<NodeData> nodeDataArray;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private List<LinkData> linkDataArray;

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

    public List<NodeData> getNodeDataArray() {
        return nodeDataArray;
    }

    public void setNodeDataArray(List<NodeData> nodeDataArray) {
        this.nodeDataArray = nodeDataArray;
    }

    public List<LinkData> getLinkDataArray() {
        return linkDataArray;
    }

    public void setLinkDataArray(List<LinkData> linkDataArray) {
        this.linkDataArray = linkDataArray;
    }
}

package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2019/12/26
 * @About: system type
 */
public class TreeType implements Serializable {
    private int id;

    private String label;
    private String value;
    private int parentId;

    private List<TreeType> children;


    public List<TreeType> getChildren() {
        return children;
    }

    public void setChildren(List<TreeType> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public TreeType(int id, String label, String value, int parentId) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.parentId = parentId;
    }
}

package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;

/**
 * @Author: Duanxr
 * @Date: 2020/7/8  10:01
 * @Description:
 */
public class ModelConfigLog implements Serializable {
    private String name;
    private String content;
    private String code;
    private long timestamp;
    private String createTime;
    private Integer storeId;
    private Integer edgeServerId;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getEdgeServerId() {
        return edgeServerId;
    }

    public void setEdgeServerId(Integer edgeServerId) {
        this.edgeServerId = edgeServerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

}

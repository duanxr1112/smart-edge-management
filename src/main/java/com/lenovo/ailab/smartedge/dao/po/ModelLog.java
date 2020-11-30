package com.lenovo.ailab.smartedge.dao.po;

/**
 * @Author: Duanxr
 * @Date: 2020/9/4  11:36
 * @Description:
 */
public class ModelLog {
   private Integer id ;
   private String content;
   private  String name;
   private String code;
   private String createTime;
   private Integer storeId;
   private String edgeServerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getEdgeServerId() {
        return edgeServerId;
    }

    public void setEdgeServerId(String edgeServerId) {
        this.edgeServerId = edgeServerId;
    }
}

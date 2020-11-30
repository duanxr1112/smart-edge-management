package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;

public class CaremaPo implements Serializable {
    private Integer caremaId;
    private Integer edgeServerId;
    private String caremaType;
    private String caremaName;


    public Integer getEdgeServerId() {
        return edgeServerId;
    }

    public void setEdgeServerId(Integer edgeServerId) {
        this.edgeServerId = edgeServerId;
    }

    public String getCaremaName() {
        return caremaName;
    }

    public void setCaremaName(String caremaName) {
        this.caremaName = caremaName;
    }

    public Integer getCaremaId() {
        return caremaId;
    }

    public void setCaremaId(Integer caremaId) {
        this.caremaId = caremaId;
    }

    public String getCaremaType() {
        return caremaType;
    }

    public void setCaremaType(String caremaType) {
        this.caremaType = caremaType;
    }
}

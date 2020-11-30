package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/1/7
 * @About: department address drop-down
 */
public class City implements Serializable {
    private int id;
    private int pId;
    private String name;

    public City(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

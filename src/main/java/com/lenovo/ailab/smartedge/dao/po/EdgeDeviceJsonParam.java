package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/1/16
 * @About:
 */
public class EdgeDeviceJsonParam implements Serializable {
    private Integer id;
  //  private Integer eId;
    private String type;
    private String param;

    public Integer getId() {
        return id;
    }
/*    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }*/
    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

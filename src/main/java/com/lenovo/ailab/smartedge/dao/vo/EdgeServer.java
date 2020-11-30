package com.lenovo.ailab.smartedge.dao.vo;

import java.io.Serializable;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/5/13
 * @About:
 */
public class EdgeServer implements Serializable {

    private Integer edgeServerId;
    private String edgeServerName;


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EdgeServer other = (EdgeServer) obj;
        if (other.getEdgeServerId() == null) {
            return false;
        }
        if (edgeServerId == null) {
            if (other.edgeServerId != null)
                return false;
        } else if (!edgeServerId.equals(other.edgeServerId))
            return false;
        return true;
    }
    public Integer getEdgeServerId() {
        return edgeServerId;
    }

    public void setEdgeServerId(Integer edgeServerId) {
        this.edgeServerId = edgeServerId;
    }

    public String getEdgeServerName() {
        return edgeServerName;
    }

    public void setEdgeServerName(String edgeServerName) {
        this.edgeServerName = edgeServerName;
    }
}

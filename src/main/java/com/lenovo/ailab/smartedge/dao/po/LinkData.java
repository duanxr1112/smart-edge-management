package com.lenovo.ailab.smartedge.dao.po;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/1/10
 * @About:
 */
public class LinkData implements Serializable {
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer from;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private Integer to;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String fromPort;
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String toPort;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getFromPort() {
        return fromPort;
    }

    public void setFromPort(String fromPort) {
        this.fromPort = fromPort;
    }

    public String getToPort() {
        return toPort;
    }

    public void setToPort(String toPort) {
        this.toPort = toPort;
    }
}

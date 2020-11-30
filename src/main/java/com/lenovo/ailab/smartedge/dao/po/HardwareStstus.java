package com.lenovo.ailab.smartedge.dao.po;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/2/11
 * @About:
 */
public class HardwareStstus implements Serializable {
    @NotEmpty
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

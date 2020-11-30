package com.lenovo.ailab.smartedge.dao.po;

import java.io.Serializable;
import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/2/18
 * @About:
 */
public class AddDepartmentDevice implements Serializable {
    private Integer id;
    private Integer eId;
    private Integer dId;
    private List<AddDepartmentDevice> addDepartmentDeviceList;

    public List<AddDepartmentDevice> getAddDepartmentDeviceList() {
        return addDepartmentDeviceList;
    }

    public void setAddDepartmentDeviceList(List<AddDepartmentDevice> addDepartmentDeviceList) {
        this.addDepartmentDeviceList = addDepartmentDeviceList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}

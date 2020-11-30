package com.lenovo.ailab.smartedge.dao.po;


import java.io.Serializable;
import java.util.List;

public class DepartmentDeviceForUseType implements Serializable {
	
    private Integer id;
    /*
     * 用途类型
     */
    private Integer useType;

    private List<DepartmentDeviceForUseType> departmentDeviceForUseTypes;

    public List<DepartmentDeviceForUseType> getDepartmentDeviceForUseTypes() {
        return departmentDeviceForUseTypes;
    }

    public void setDepartmentDeviceForUseTypes(List<DepartmentDeviceForUseType> departmentDeviceForUseTypes) {
        this.departmentDeviceForUseTypes = departmentDeviceForUseTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }
}
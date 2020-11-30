package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.City;
import com.lenovo.ailab.smartedge.dao.po.Department;
import com.lenovo.ailab.smartedge.dao.po.ModelLog;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(@Param("id") Integer id);

    List<ModelLog> getLog(@Param("id") Integer id, @Param("startTime")String startTime, @Param("endTime")String endTime);

    Department selectByDcode(@Param("dCode") String dcode);

    List<Department> selectAll(@Param("name") String name,@Param("dCode") String dCode);

    int updateByPrimaryKey(Department record);

    List<City> getDepartmentAddress();

    Department downloadthumbnail(@Param("path") String path);

    Department selectByBtitDepartmentCode(@Param("departmentCode") String departmentCode);

    int updateDepertment(@Param("id") Integer id);
}
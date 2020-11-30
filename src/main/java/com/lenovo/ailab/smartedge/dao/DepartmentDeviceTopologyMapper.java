package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.*;
import org.apache.ibatis.annotations.Param;

public interface DepartmentDeviceTopologyMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(DepartmentDeviceTopology record);

    int insertDepartmentDeviceTopologyList(@Param("list") List<DepartmentDeviceTopology> record);

    List<DepartmentDeviceTopology> selectByPrimaryKey(@Param("id") Integer id);

    List<Department> selectAll();

    DepartmentDeviceTopology selectTopologyByDepartmentId(@Param("departmentId") Integer departmentId);

    List<DepartmentDeviceTopology> selectTopologyByDepartmentIds(@Param("dIds") List<Integer> departmentId);

    List<DepartmentDeviceTopology> selectTopologyBydId(@Param("departmentId") Integer departmentId);

    int updateByPrimaryKey(DepartmentDeviceTopology record);

    int insertNodeData(NodeData nodeData);

    int insertParam(DepartmentDeviceTopologyParam departmentDeviceTopologyParam);

    List<NodeData> selectAllTopology(@Param("dId") Integer id);

    int deleteNodeByPrimaryKey(@Param("dId") Integer id);

    int deleteLinkByPrimaryKey(@Param("dId") Integer id);

    List<DepartmentDeviceTopology> selectDepartmentDeviceTopology(@Param("dId") Integer id);

    List<DepartmentDeviceTopology> selectTopologyByDepartmentIdAndDeviceId(@Param("departmentId") Integer storeId, @Param("deviceId") Integer deviceId);

    DepartmentDeviceTopology selectTopologyByDeviceId(@Param("departmentId") Integer storeId, @Param("deviceId") Integer deviceId);
}
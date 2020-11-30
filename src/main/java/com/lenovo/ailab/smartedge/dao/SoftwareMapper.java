package com.lenovo.ailab.smartedge.dao;

import java.util.List;

import com.lenovo.ailab.smartedge.dao.po.ModelConfigLog;
import com.lenovo.ailab.smartedge.dao.po.ModelInitResult;
import com.lenovo.ailab.smartedge.dao.vo.SoftwareConfig;
import org.apache.ibatis.annotations.Param;

import com.lenovo.ailab.smartedge.dao.po.Software;

public interface SoftwareMapper {
    int deleteByPrimaryKey(Integer id);
    
    int insert(Software record);
     
    int insertversion(@Param("id") Integer id,@Param("version") String version,@Param("fileName") String fileName,@Param("filePath") String filePath);

    Software selectByPrimaryKey(@Param("id") Integer id);

    Software selectByCode(@Param("code") String code);

    List<Software> selectAll(@Param("sCode") String sCode,@Param("name") String name);
    
    /**
     * 分页查询用户
     * @return
     */
 //   List<Software> selectByPage();

    int updateByPrimaryKey(Software record);

    List<Software> selectPageSoftware();

    List<Software> selectEdgeServerDetail(@Param("departmentId") Integer departmentId, @Param("deviceId") Integer deviceId);

    Software selectByEdgeServerId(@Param("deviceId") Integer deviceId,@Param("name") String name);

    int insertSoftWareConfig(@Param("list") List<SoftwareConfig> softwareConfigs);

    int insertSoftWareConfigBean(SoftwareConfig softwareConfig);

    List<SoftwareConfig> selectSoftwareConfig(@Param("storeId") Integer storeId,@Param("deviceId") Integer deviceId);

    List<SoftwareConfig> selectSoftwareConfigForCanUse(@Param("storeId") Integer storeId,@Param("deviceId") Integer deviceId);

    List<SoftwareConfig> selectSoftwareConfigAndSoftware(@Param("storeId") Integer storeId,@Param("deviceId") Integer deviceId);

    List<SoftwareConfig> selectSoftwareConfigBysId(@Param("storeId") Integer storeId,@Param("deviceId") Integer deviceId,@Param("sId") Integer sId);

    int updateSoftwareConfigCanInit(@Param("storeId") Integer storeId, @Param("deviceId") Integer deviceId);

    int updateSoftwareConfig(@Param("list") List<ModelInitResult> modelInitResults, @Param("storeId") Integer storeId, @Param("deviceId") Integer deviceId);

    SoftwareConfig selectModelScript(@Param("code") String modelCode);

    SoftwareConfig selectSoftwareConfigById(@Param("deviceId") Integer deviceId);

    int updateSoftwareConfigByModelCode(@Param("modelCode") String modelCode,@Param("serverConfig") String serverConfig);

    int deleteModelById(@Param("modelCode") String modelCode);

    SoftwareConfig selectModelStopScript(@Param("modelCode") String modelCode);

    int updateVersion(@Param("storeId") Integer storeId,@Param("deviceId")  Integer deviceId,@Param("sId")  Integer id,@Param("version")  String version ,@Param("fileName")  String fileName,@Param("url")  String filePath);

    int insertModelConfigLog(ModelConfigLog modelConfigLog);
}
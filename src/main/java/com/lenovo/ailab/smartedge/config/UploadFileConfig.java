package com.lenovo.ailab.smartedge.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.ailab.smartedge.http.HttpResult;
import com.lenovo.ailab.smartedge.utils.StaticVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @CreateDate: 2020/01/19
 * @Author: duanxr2
 * @Email: duanxr2@lenovo.com
 * @Description: upload file
 */
@Service
public class UploadFileConfig {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileConfig.class);

    @Value("${file.server-url}")
    public String SERVICE_URL;
    @Value("${file.down-url}")
    public String DOWNURL;
    @Resource(name = "httpLogRunner")
    private HttpLogRunner httpLogRunner;


    public String fileUpload(String type,String fileName, byte[] bytes,String fileType) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put(StaticVariable.APINAME, StaticVariable.INTELLIGENT_EDGE_PLATFORM);
        jsonMap.put(StaticVariable.APPNAME, fileName);
      //  jsonMap.put(StaticVariable.BASE64DATA, base64Data);
        jsonMap.put(StaticVariable.EXTENSION, fileType);
        jsonMap.put(StaticVariable.BYTES, bytes);
        jsonMap.put(StaticVariable.FILENAME, fileName);
        jsonMap.put(StaticVariable.LEVEL, StaticVariable.PUBLIC);
        jsonMap.put(StaticVariable.METADATA, new HashMap<>());
        jsonMap.put(StaticVariable.USERID, fileName);
        jsonMap.put(StaticVariable.CONTENT_TYPE, fileType);

        HttpResult httpResult = httpLogRunner.callExecutor(SERVICE_URL+"transfer/to", JSON.toJSONString(jsonMap));
        logger.info(httpResult.getCode().toString() + "||1||" + httpResult.getBody(), "fileUpload");
        if (httpResult.getCode() == 200 || httpResult.getCode() == 0) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getBody());
            String fileId = jsonObject.getJSONObject(StaticVariable.RESULT).getString(StaticVariable.FILEID);
            return fileId;
        } else {
            logger.error("file upload error ", "fileUpload");
        }
        return null;
    }

}

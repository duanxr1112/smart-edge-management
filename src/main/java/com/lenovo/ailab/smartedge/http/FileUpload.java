package com.lenovo.ailab.smartedge.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * Title:        FileUpload.java
  *
  * @CreateDate:    2019/8/30
  * @Author:         yinxiangyang
  * @Email:          yinxy4@lenovo.com
  * @Description:
 */

public class FileUpload {

    private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    public static void upload2(String url,String fileUUID,File imgFile) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));

        //File file = new File("F:\\Ken\\1.PNG");
        //FileBody bin = new FileBody(file);
/*
        File file = new File("F:\\Ken\\abc.pdf");*/

        multipartEntityBuilder.addBinaryBody("file", imgFile, ContentType.create("image/jpeg"),imgFile.getName());
        //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
        //multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
//        multipartEntityBuilder.addBinaryBody("file",imgFile);
        //multipartEntityBuilder.addPart("comment", new StringBody("This is comment", ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addTextBody("filekey", fileUUID);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode= httpResponse.getStatusLine().getStatusCode();
        if(statusCode == 200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            StringBuffer buffer = new StringBuffer();
            String str = "";
            while(StringUtils.isNotBlank((str = reader.readLine()))) {
                buffer.append(str);
            }
            logger.error(buffer.toString());
           /* System.out.println(buffer.toString());*/
        }

        httpClient.close();
        if(httpResponse!=null){
            httpResponse.close();
        }

    }
}

package com.lenovo.ailab.smartedge.config;

import com.lenovo.ailab.smartedge.http.HttpClientHelper;
import com.lenovo.ailab.smartedge.http.HttpResult;
import com.lenovo.ailab.smartedge.utils.StaticVariable;
import com.lenovo.ailab.smartedge.utils.StatusCode;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
  * Title:        HttpClientRunner.java
  *
  * @CreateDate:    2019/9/26
  * @Author:         yinxiangyang
  * @Email:          yinxy4@lenovo.com
  * @Description:
 */
@Service("httpLogRunner")
public class HttpLogRunner {
    private static final Logger logger = LoggerFactory.getLogger(HttpLogRunner.class);

    @Autowired
    protected HttpClientHelper httpClient;

    @Resource(name = "httpLogExecutor")
    private ExecutorService threadPoolTaskExecutor;
    @Value("${file.lenovo-token}")
    public String LENOVOTOKEN;


    public HttpResult callExecutor(String url, String inputData)  {
        HttpResult httpResult=null;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(StaticVariable.CONTENT_TYPE, StaticVariable.APPLICATION_JSON);
        headers.put(StaticVariable.LENOVOTOKEN, LENOVOTOKEN);
        CallWorker callWorker = new CallWorker( url,  inputData,  headers);

        Future<HttpResult> future = threadPoolTaskExecutor
                .submit(callWorker);


        try {
            httpResult =future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return httpResult;
    }

    private final class CallWorker implements Callable<HttpResult> {
        String url;
        String inputData;
        Map<String, String> headers;

        private CallWorker(String url, String inputData, Map<String, String> headers) {
            this.url = url;
            this.inputData = inputData;
            this.headers = headers;
        }

        @Override
        public HttpResult call() {
            HttpResult httpResult = null;
            try {
                httpResult= httpClient.doPostJson(url,inputData,headers);
                if (httpResult.getCode() == HttpStatus.SC_OK || StatusCode.HTTP_OK == httpResult.getCode()) {
                 return httpResult;
                }/*else if (httpResult.getCode() == 401){
                    initToken();
                    headers.put(StaticVariable.TOKEN, getToken());
                    httpResult= httpClient.doPostJson(url,inputData,headers);
                    if (httpResult.getCode() == HttpStatus.SC_OK || StatusCode.HTTP_OK == httpResult.getCode()) {
                        return httpResult;
                    }else {
                        logger.error("response error code:{}\t url:{}\t inputData:{}\t responseData:{}\t " + httpResult.getCode()
                                ,url,inputData,httpResult.getBody());
                    }
                }*/else {
                    logger.error("response error code:{}\t url:{}\t inputData:{}\t responseData:{}\t " + httpResult.getCode()
                            ,url,inputData,httpResult.getBody());
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("runtime error! {}", e.getMessage());
            }
            return null;
        }
    }



}

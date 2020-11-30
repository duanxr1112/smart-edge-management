package com.lenovo.ailab.smartedge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/1/19
 * @About:
 */
@Configuration
public class ExecutorConfig {
    @Bean(name = "httpLogExecutor")
    public ExecutorService getHttpLogAsyncExecutor() {
        return new FixedThreadPoolExecutor(256, 1024, 30, "httpLogExecutor");
    }
}

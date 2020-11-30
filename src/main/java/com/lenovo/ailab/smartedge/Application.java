package com.lenovo.ailab.smartedge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//import groovy.util.logging.Slf4j;

@MapperScan("com.lenovo.ailab.smartedge.dao")
@ServletComponentScan
@SpringBootApplication
@EnableCaching
@EnableScheduling
//@Slf4j
public class Application 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
    }
}
package com.lenovo.ailab.smartedge.utils;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Title: SpringUtils.java
 * 
 * @author "chenpeng"
 * @date 2018年5月2日
 * @Email:chenpeng10@lenovo.com
 * @Description:  
 */
@Component
public class SpringUtils implements ApplicationContextAware {
	public static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);
	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtils.applicationContext == null) {
			SpringUtils.applicationContext = applicationContext;
		}
	}


	/**
	 * 获取applicationContext
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	 * 通过name获取 Bean.
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
	/**
	 * 通过class获取Bean.
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 通过name,以及Clazz返回指定的Bean
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
	
	public static Map<String,Object> getBeansWithAnnotation(Class<?> clazz){
		@SuppressWarnings("unchecked")
		Map<String, Object> beansWithAnnotationMap = getApplicationContext().getBeansWithAnnotation((Class<? extends Annotation>) clazz);
		return beansWithAnnotationMap;
	}

}

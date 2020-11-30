package com.lenovo.ailab.smartedge.config;

import com.lenovo.ailab.smartedge.filter.PreFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Title: CorsConfig.java
 * @Autohr "chenpeng"
 * @data 2018年11月12日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
@Configuration
public class CorsConfig {

	     @Bean
         public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedOrigin("*");
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", corsConfiguration);
	        return new CorsFilter(source);
	    }
	/*@Bean
	public FilterRegistrationBean ServletLoginFilterFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new PreFilter());
		registration.addUrlPatterns("/**"); //
		//registration.addInitParameter("paramName", "paramValue"); //
		registration.setName("preFilter");
		return registration;
	}*/
}

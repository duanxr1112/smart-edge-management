package com.lenovo.ailab.smartedge.http;

import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
  * Title:        HttpClient.java
  *
  * @CreateDate:    2019/8/29
  * @Author:         yinxiangyang
  * @Email:          yinxy4@lenovo.com
  * @Description:
 */

@Configuration
public class HttpClient {
	@Value("${http.max_total}")
	private int maxTotal = 800;
	
	@Value("${http.default_max_perRoute}")
	private int defaultMaxPerRoute = 80;
	
	@Value("${http.validate_after_inactivity}")
	private int validateAfterInactivity = 1000;
	
	@Value("${http.connection_request_timeout}")
	private int connectionRequestTimeout = 5000;
	
	@Value("${http.connection_timeout}")
	private int connectTimeout = 10000;
	
	@Value("${http.socket_timeout}")
	private int socketTimeout = 20000;
	
	@Value("${http.idleConnection_evictor_waitTime}")
	private Integer waitTime = 3600;
	
	@Bean
	public PoolingHttpClientConnectionManager createPoolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager poolmanager = new PoolingHttpClientConnectionManager();
		poolmanager.setMaxTotal(maxTotal);
		poolmanager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		poolmanager.setValidateAfterInactivity(validateAfterInactivity);

		
		return poolmanager;
	}
	
	@Bean
	public HttpClientBuilder createHttpClientBuilder(PoolingHttpClientConnectionManager poolManager) {
		return HttpClientBuilder.create().setConnectionManager(poolManager).setConnectionManagerShared(true);
	}
	
	@Bean
	public CloseableHttpClient createHttpClient(HttpClientBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public SSLContext createSSLContext() throws Exception {
		return SSLContexts.custom().loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustAllStrategy()).build();
	}
	
	@Bean
	public SSLConnectionSocketFactory createSSLConnectionSocketFactory(SSLContext sslContext) {
		return new SSLConnectionSocketFactory(sslContext,
				new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	}
	
	@Bean
	public RequestConfig createRequestConfig() {
		return RequestConfig.custom()
				.setConnectionRequestTimeout(connectionRequestTimeout) 	// 从连接池中取连接的超时时间
				.setConnectTimeout(connectTimeout)						// 连接超时时间
				.setSocketTimeout(socketTimeout)						// 请求超时时间
				.build();
	}
	
	@Bean
	public IdleConnectionEvictor createIdleConnectionEvictor(PoolingHttpClientConnectionManager poolManager) {
		IdleConnectionEvictor idleConnectionEvictor = new IdleConnectionEvictor(poolManager, waitTime, TimeUnit.SECONDS);
		return idleConnectionEvictor;
	}
	
	public void monitorExecutor(){
		
	}

}

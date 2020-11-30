package com.lenovo.ailab.smartedge.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
  * Title:        HttpClientHelper.java
  *
  * @CreateDate:    2019/8/29
  * @Author:         yinxiangyang
  * @Email:          yinxy4@lenovo.com
  * @Description:
 */

@Service
public class HttpClientHelper {

	@Autowired
	private CloseableHttpClient httpClient;

	@Autowired
	private RequestConfig config;

	/**
	 * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url) throws Exception {
		// 声明 http get 请求
		HttpGet httpGet = new HttpGet(url);

		// 装载配置信息
		httpGet.setConfig(config);

		// 发起请求
		CloseableHttpResponse response = this.httpClient.execute(httpGet);

		// 判断状态码是否为200
		if (response.getStatusLine().getStatusCode() == 200) {
			// 返回响应体的内容
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		}
		return null;
	}

	/**
	 * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Map<String, Object> map) throws Exception {
		URIBuilder uriBuilder = new URIBuilder(url);

		if (map != null) {
			// 遍历map,拼接请求参数
			for (Entry<String, Object> entry : map.entrySet()) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}

		// 调用不带参数的get请求
		return this.doGet(uriBuilder.build().toString());

	}

	public HttpResult doPostJson(String url, Map<String, Object> map) throws Exception {
		return doPostJson(url, JSONObject.toJSONString(map));
	}

	public HttpResult doPostJson(String url, String postData) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		headers.put("Accept", "application/json;charset=utf-8");
		return doPostJson(url, postData, headers);
	}

	public HttpResult doPostJson(String url, String postData, Map<String, String> headers) {
		HttpResult result = new HttpResult();
		HttpPost post = new HttpPost(url);
		// 加入配置信息
		post.setConfig(config);
		if (headers != null) {
			for (Entry<String, String> entry : headers.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
			}
		}
		CloseableHttpResponse response = null;
		try {
			StringEntity s = new StringEntity(postData, "UTF-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(s);
			response = this.httpClient.execute(post);

			result.setCode(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result.setBody(EntityUtils.toString(response.getEntity(), "UTF-8"));// 返回json格式：
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != response) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 带参数的post请求
	 *
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPostForm(String url, Map<String, Object> map) throws Exception {
		// 声明httpPost请求
		HttpPost httpPost = new HttpPost(url);
		// 加入配置信息
		httpPost.setConfig(config);

		// 判断map是否为空，不为空则进行遍历，封装from表单对象
		if (map != null) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : map.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			// 构造from表单对象
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

			// 把表单放到post里
			httpPost.setEntity(urlEncodedFormEntity);
		}

		// 发起请求
		CloseableHttpResponse response = this.httpClient.execute(httpPost);
		try {
			if (null != response) {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new HttpResult(response.getStatusLine().getStatusCode(),
				EntityUtils.toString(response.getEntity(), "UTF-8"));
	}

	/**
	 * 不带参数post请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url) throws Exception {
		return this.doPostForm(url, null);
	}



	public static String uploadFile(String url ,MultipartFile file,String fileParamName,Map<String,String>headerParams) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		try {
			String fileName = file.getOriginalFilename();
			HttpPost httpPost = new HttpPost(url);
			//添加header
			for (Map.Entry<String, String> e : headerParams.entrySet()) {
				httpPost.addHeader(e.getKey(), e.getValue());
			}
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setCharset(Charset.forName("utf-8"));
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//加上此行代码解决返回中文乱码问题
			builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
//			for (Map.Entry<String, String> e : otherParams.entrySet()) {
//				builder.addTextBody(e.getKey(), e.getValue());// 类似浏览器表单提交，对应input的name和value
//			}
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);// 执行提交
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// 将响应内容转换为字符串
				result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}



}

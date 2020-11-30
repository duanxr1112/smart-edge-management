package com.lenovo.ailab.smartStore.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSONObject;
import com.lenovo.ailab.smartStore.BaseApplicationTest;
import com.lenovo.ailab.smartStore.utils.TestResourcesData;

/**
 * Title: SmartStoreControllerTest.java
 * 
 * @Autohr "chenpeng"
 * @data 2019年7月3日
 * @Email chenpeng10@lenovo.com
 * @description:
 **/
public class SmartStoreControllerTest extends BaseApplicationTest {

	@Test
	public void smartStoreControllerTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/image/upload").contentType(MediaType.APPLICATION_JSON)
				.content(JSONObject.toJSONString(TestResourcesData.getBase64Data("", "jpg", "files/1.jpg"))));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/image/upload").contentType(MediaType.APPLICATION_JSON)
				.content(JSONObject.toJSONString(TestResourcesData.getBase64Data("", "jpg", "files/2.jpg"))));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/image/upload").contentType(MediaType.APPLICATION_JSON)
				.content(JSONObject.toJSONString(TestResourcesData.getBase64Data("", "jpg", "files/3.jpg"))));
		
	}

}

package com.lenovo.ailab.smartStore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Created by Administrator on 2018/12/21.
 */
public class TestResourcesData {

    public static final String ENTITY_LINKING_DATA = "Lenovo located Beijing。";
    public static final String ENTITY_LINKING_LANGUAGE = "english";
    public static final double ENTITY_LINKING_CONFIDENCE = 0.5;
    public static final String MOVIE_KG_DATA= "寒战" ;
    public static final String MOVIE_KG_LANGUAGE = "cn-zh";
    public static final String MUSIC_KG_DATA= "Million Reasons" ;
    public static final String MUSIC_KG_LANGUAGE = "en-us";

    public static Map<String, Object> getNLPInputMap(String text1, String data1, String text2, String data2) {
        Map<String, Object> mapText = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("data", data1);
        map2.put("data", data2);
        mapText.put(text1, map1);
        mapText.put(text2, map2);
        return mapText;
    }

    public static Map<String, Object> getNLPInputMap(String text, String data, String language) {
        Map<String, Object> mapText = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("language", language);
        mapText.put(text, map);
        return mapText;
    }

    public static Map<String, Object> getAudioData(Integer bit, Integer sampleRate, String resourcePath) {
        Map<String, Object> map = new HashMap<>();
        String data = getBase64Data(resourcePath);
        map.put("bit", bit);
        map.put("sampleRate", sampleRate);
        map.put("base64Data", data);
        return map;
    }

    public static Map<String, Object> getBaiduVoiceData(String base64Data, String language) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        String data = getBase64Data(base64Data);
        map.put("base64Data", data);
        map1.put("audio", map);
        map1.put("language", language);
        return map1;
    }

    public static Map<String, Object> getKnowledgeGraph(String data, String language, double confidence) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map.put("data", data);
        map.put("language", language);
        map.put("confidence", confidence);
        map1.put("text", map);
        return map1;
    }

    public static Map<String, Object> getBase64Data(String imageId, String format, String resourcePath) {
        Map<String, Object> map = new HashMap<>();
        String data = getBase64Data(resourcePath);
        map.put("base64Data", data);
        return map;
    }

    public static Map<String, Object> getBase64Data(String imageId1, String format1, String resourcePath1, String imageId2, String format2, String resourcePath2) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        String data1 = getBase64Data(resourcePath1);
        String data2 = getBase64Data(resourcePath2);
        map1.put("base64Data", data1);
        map1.put("format", format1);
        map1.put("imageId", imageId1);
        map2.put("base64Data", data2);
        map2.put("format", format2);
        map2.put("imageId", imageId2);
        map.put("image1", map1);
        map.put("image2", map2);
        return map;
    }

    @SuppressWarnings("restriction")
	public static String getBase64Data(String resourcePath) {
        InputStream in = null;
        try {
            in = new ClassPathResource(resourcePath).getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(in);
            return new String(Base64.encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

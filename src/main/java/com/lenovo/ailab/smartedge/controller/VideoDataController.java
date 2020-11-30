package com.lenovo.ailab.smartedge.controller;

import java.io.File;
import java.io.IOException;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lenovo.ailab.smartedge.service.EdgeDeviceService;

@RestController
@RequestMapping("/video")
public class VideoDataController {

	private static final Logger logger = LoggerFactory.getLogger(VideoDataController.class);
	@Autowired
	EdgeDeviceService edgeDeviceService;
	@Value("${filepath.video}")
	String VIDEO;

    @Value("${video.upload.url}")
    String VIDEO_UPLOAD_URL;


	@PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(VIDEO + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
        	logger.error(e.toString(), e);
        }
        return "上传失败！";
    }


    @PostMapping("/upload-forward")
    @ResponseBody
    public String uploadForward(@RequestParam(value = "file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        System.out.println(file.getOriginalFilename() + "==" + file.getName());
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getOriginalFilename(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                file.getBytes()))
                .build();
        Request request = new Request.Builder()
                .url(VIDEO_UPLOAD_URL)
                .method("POST", body)
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        Response response = client.newCall(request).execute();
        response.close();
        return "success！";
    }


}

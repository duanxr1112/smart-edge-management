package com.lenovo.ailab.smartedge.controller;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lenovo.ailab.smartedge.service.EdgeDeviceService;
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/download")
public class DownLoadController {

	private static final Logger logger = LoggerFactory.getLogger(DownLoadController.class);
	@Autowired
	EdgeDeviceService edgeDeviceService;
	@Value("${filepath.software}")
	String SOFTWARE_FILE_PATH;
	
	@RequestMapping(value = "/software/{scode}/{version}", method = RequestMethod.GET)
	@ResponseBody
	public String insertEdgeDevice(@PathVariable(value = "scode", required = true) String scode,
			@PathVariable(value = "version", required = true) String version, HttpServletRequest httpServletRequest,HttpServletResponse response) {
		String fileName=scode+"_"+version+".zip";
		
		 if (fileName != null) {
	            //设置文件路径
	            String realPath = SOFTWARE_FILE_PATH+scode+"/"+version+"/";;
	            File file = new File(realPath , fileName);
	            if (file.exists()) {
	                // 设置强制下载不打开
	                response.setContentType("application/force-download");
	                // 设置文件名
	                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
	                byte[] buffer = new byte[1024];
	                FileInputStream fis = null;
	                BufferedInputStream bis = null;
	                try {
	                    fis = new FileInputStream(file);
	                    bis = new BufferedInputStream(fis);
	                    OutputStream os = response.getOutputStream();
	                    int i = bis.read(buffer);
	                    while (i != -1) {
	                        os.write(buffer, 0, i);
	                        i = bis.read(buffer);
	                    }
	                    logger.error("success");
	                } catch (Exception e) {
						logger.error(e.toString(), e);
	                } finally {
	                    if (bis != null) {
	                        try {
	                            bis.close();
	                        } catch (IOException e) {
								logger.error(e.toString(), e);
	                        }
	                    }
	                    if (fis != null) {
	                        try {
	                            fis.close();
	                        } catch (IOException e) {
								logger.error(e.toString(), e);
	                        }
	                    }
	                }
	            }else{
	            	logger.error("file not exists");
	            }
	        }
	        return null;
	}


	@PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(SOFTWARE_FILE_PATH + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
        	logger.error(e.toString(), e);
        }
        return "上传失败！";
    }




}

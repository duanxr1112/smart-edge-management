package com.lenovo.ailab.smartedge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/3/24
 * @About: 解析压缩包文件夹里的文件
 */
public class ParseCompressedFile {

    private static final Logger logger = LoggerFactory.getLogger(ParseCompressedFile.class);

    /**
     * @param fileName - url
     * @return 解析压缩包
     * @throws IOException
     */
    public static String readZip(String fileName) throws IOException {
        ZipFile zip = new ZipFile(fileName);
        File file = new File(fileName);
        String parentZipParent = file.getParent();//获取上级文件夹解压到这里
        File temp = file;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
        ZipInputStream zis = new ZipInputStream(bis);
        ZipEntry entry;//用于获取压缩文件中的文件或文件夹
        StringBuffer sb = new StringBuffer();
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                logger.info("压缩包里含有文件夹！");
            } else {
                logger.info("file name is {} " + file.getName());
                if (entry.getName().equals("unknownmap.json")) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * @param urlStr
     * @return 获取文件服务器的文件并解析
     * @throws IOException
     */
    public static String readData(String urlStr,String fileName) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //得到输入流
        InputStream inputStream = conn.getInputStream();

        ZipInputStream zin = new ZipInputStream(inputStream, Charset.forName("utf-8"));
        BufferedInputStream bs = new BufferedInputStream(zin);
        byte[] bytes = null;
        ZipEntry ze;
        //   Map<String,String> jsonMap= new HashMap();
        StringBuilder orginJson = new StringBuilder();
        //循环读取压缩包里面的文件
        while ((ze = zin.getNextEntry()) != null) {

            if (ze.toString().equals(fileName)) {
                //读取每个文件的字节，并放进数组
            //    bytes = new byte[(int) ze.getSize()];
                bytes = new byte[201400];
                bs.read(bytes, 0, (int) 201400);
                //将文件转成流
                InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(byteArrayInputStream));
                //读取文件里面的内容
                String line;
                while ((line = br.readLine()) != null) {
                    orginJson.append(line);
                }
                //关闭流
                br.close();
                //   String name=new String(ze.getName().replace(".json",""));
                //   jsonMap.put(name,orginJson.toString());
            }
        }
        zin.closeEntry();
        inputStream.close();
        return orginJson.toString();
    }
}

package com.lenovo.ailab.smartedge.config;

import com.lenovo.ailab.smartedge.dao.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/4/2
 * @About:
 */
public class CompressionFile {

    private static final Logger logger = LoggerFactory.getLogger(CompressionFile.class);

    /**
     * @param urlPath
     * @return 通过地址下载文件
     */
    public static byte[] getImageFromURL(String urlPath) {
        byte[] data = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            is = conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                data = readInputStream(is);
            } else {
                data = null;
            }
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {

            }
            conn.disconnect();
        }
        return data;
    }

    /**
     * @param is
     * @return  下载文件最终返回 byte[]
     */
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {

        }
        byte[] data = baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {

        }
        return data;
    }

    /**
     * @param bytes
     * @param filePath
     * @param fileName
     * @return  本地创建文件
     */
    public static File getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
          //  if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在 -- 目前使用都创建
                dir.mkdirs();
           // }
            file = new File(filePath + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
    /**
     * 解压zip到指定的路径
     *
     * @param zipFileString ZIP的名称
     * @param outPathString 要解压缩路径
     * @throws Exception
     */
    public static FileVo UnZipFolder(String zipFileString, String outPathString) throws Exception {
        FileVo fileVo = new FileVo();
        File file = null;
        StringBuilder sb = new StringBuilder();
        if (!isFileExists(new File(zipFileString))) {
            return null;
        }
        long zipLength = getZipSize(zipFileString);
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
        ZipEntry zipEntry;
        String szName = "";
        long count = 0;
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                //获取部件的文件夹名
                szName = szName.substring(0, szName.length() - 1);
                file = new File(outPathString + File.separator + szName);
                file.mkdirs();
            } else {
                file = new File(outPathString + File.separator + szName);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                FileInputStream inputStream = new FileInputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                // 读取（字节）字节到缓冲区
                while ((len = inZip.read(buffer)) != -1) {
                    count += len;
                    int progress = (int)((count * 100)/zipLength) ;
                    // 从缓冲区（0）位置写入（字节）字节
                    out.write(buffer, 0, len);
                    out.flush();
                }
                //  读取指定文件内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                if (szName.contains("media.json") || szName.contains("paintermap.json") ) {
                    try {
                        while ((line = reader.readLine()) != null){
                            sb.append(line).append("\n");
                        }
                        reader.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                out.close();
            }
        }
        inZip.close();
        fileVo.setFile(file);
        fileVo.setWrite(sb.toString());
        return fileVo;
    }

    /**
     * 解压zip到指定的路径
     *
     * @param zipFileString ZIP的名称
     * @param outPathString 要解压缩路径
     * @throws Exception
     */
    public static FileVo UnZipFolderForLine(String zipFileString, String outPathString, String fileName) throws Exception {
        FileVo fileVo = new FileVo();
        File file = null;
        List<String> names = new ArrayList<>();
        if (!isFileExists(new File(zipFileString))) {
            return null;
        }
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString),Charset.forName("GBK"));
        long zipLength = getZipSize(zipFileString);
        ZipEntry zipEntry;
        String szName = "";
        long count = 0;
        try{
            while ((zipEntry = inZip.getNextEntry()) != null) {
                szName = zipEntry.getName();
                names.add(szName);
                if (zipEntry.isDirectory()) {
                    //获取部件的文件夹名
                    szName = szName.substring(0, szName.length() - 1);
                    file = new File(outPathString + File.separator + szName);
                    file.mkdirs();
                } else {
                    file = new File(outPathString + File.separator + szName);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream out = new FileOutputStream(file);
                    FileInputStream inputStream = new FileInputStream(file);
                    int len;
                    byte[] buffer = new byte[1024];
                    // 读取（字节）字节到缓冲区
                    while ((len = inZip.read(buffer)) != -1) {
                        count += len;
                        int progress = (int)((count * 100)/zipLength) ;
                        // 从缓冲区（0）位置写入（字节）字节
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                    out.close();
                }
            }
            if(!names.contains(fileName)){
                file = new File(outPathString + File.separator + fileName);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            }
            inZip.close();
            fileVo.setFile(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileVo;
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    /**
     * @param filePath
     * @return 获取文件大小
     */
    private static long getZipSize(String filePath){
        long size = 0;
        ZipFile f;
        try {
            f = new ZipFile(filePath);
            Enumeration<? extends ZipEntry> en = f.entries();
            while (en.hasMoreElements()) {
                size += en.nextElement().getSize();
            }
        } catch (IOException e) {
            size = 0;
        }
        return size;
    }

    /**
     * 压缩文件和文件夹
     *
     * @param srcFileString 要压缩的文件或文件夹
     * @param zipFileString 解压完成的Zip路径
     * @throws Exception
     */
    public static File ZipFolder(String srcFileString, String zipFileString) throws Exception {
        //创建ZIP
        ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(zipFileString));
        //创建文件
        File file = new File(srcFileString);
        //压缩
        //  Log.e("压缩===","---->"+file.getParent()+"==="+file.getAbsolutePath());
        ZipFiles(file.getParent()+ File.separator, file.getName(), outZip);
        //完成和关闭
        outZip.finish();
        outZip.close();
        return file;
    }

    /**
     * 压缩文件
     *
     * @param folderString
     * @param fileString
     * @param zipOutputSteam
     * @throws Exception
     */
    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam) throws Exception {
        logger.info("zipFiles","folderString:" + folderString + "\n" + "fileString:" + fileString + "\n==========================");
        if (zipOutputSteam == null)
            return;
        File file = new File(folderString + fileString);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(fileString);
            FileInputStream inputStream = new FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }
            zipOutputSteam.closeEntry();
        } else {
            //文件夹
            String fileList[] = file.list();
            //没有子文件和压缩
            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }
            //子文件和递归
            for (int i = 0; i < fileList.length; i++) {
                ZipFiles(folderString+fileString+"/",  fileList[i], zipOutputSteam);
            }
        }
    }


     /*   public static void main(String args[]) {

            delFolder("C:\\CompressionFile");
            System.out.println("deleted");
        }*/


    /** 删除文件夹
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path
     * @return 删除指定文件夹下所有文件 文件夹完整绝对路径
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }



}

package com.lenovo.ailab.smartedge.dao.po;

/**
 * @Author: Duanxr
 * @Date: 2020/7/14  18:03
 * @Description:
 */
public class UploadImageBack {
    private Integer id;
    private String fileId;
    private String filePath;
    private String filename;
    private String extension;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

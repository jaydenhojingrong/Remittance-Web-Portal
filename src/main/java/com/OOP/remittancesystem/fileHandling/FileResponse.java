package com.OOP.remittancesystem.fileHandling;

import java.util.List;

public class FileResponse {

    private String fileName;
    private String fileDownloadURL;
    private String fileType;
    private Long size;
    private List<String> headers;
    

    public FileResponse(String fileName, String fileDownloadURL, String fileType, Long size, List<String> headers){
        this.fileName = fileName;
        this.fileDownloadURL = fileDownloadURL;
        this.fileType = fileType;
        this.size = size;
        this.headers = headers;   
    } 

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadURL() {
        return fileDownloadURL;
    }

    public void setFileDownloadURL(String fileDownloadURL) {
        this.fileDownloadURL = fileDownloadURL;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
    
    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}

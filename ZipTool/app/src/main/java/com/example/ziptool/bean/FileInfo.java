package com.example.ziptool.bean;

import java.util.Objects;

public class FileInfo {
    private String fileName;
    private String fileDate;
    private String fileSize;
    private String filePath;
    private String fileType;

    public FileInfo(String fileName, String fileDate, String fileSize, String filePath) {
        this.fileName = fileName;
        this.fileDate = fileDate;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(fileName, fileInfo.fileName) &&
                Objects.equals(fileDate, fileInfo.fileDate) &&
                Objects.equals(fileSize, fileInfo.fileSize) &&
                Objects.equals(filePath, fileInfo.filePath) &&
                Objects.equals(fileType, fileInfo.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileDate, fileSize, filePath, fileType);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileDate='" + fileDate + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}

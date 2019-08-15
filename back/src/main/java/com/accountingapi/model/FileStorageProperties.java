package com.accountingapi.model;


import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@Entity
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uploadDir",unique=false,nullable = false)
    private String uploadDir;

    @Column(name = "fileType",unique=false,nullable = false)
    private String fileType;

    @Column(name = "size",unique=false,nullable = true)
    private long size;


    //Constructor


    public FileStorageProperties() {
    }

    public FileStorageProperties(String uploadDir, String fileType, long size) {
        this.uploadDir = uploadDir;
        this.fileType = fileType;
        this.size = size;
    }


    //Getter & Setters


    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

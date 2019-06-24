package com.accountingapi.model;


import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@Entity
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uploadDir",unique=false,nullable = true)
    private String uploadDir;

    @ManyToOne()
    @JoinColumn(name="BillId")
    private Bill bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}

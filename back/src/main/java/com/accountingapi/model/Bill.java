package com.accountingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Bill {

    @Id
    @Column(name = "billId",unique=true,nullable = false)
    private String billId;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Double totalHT= Double.valueOf(0);

    @Column(nullable = false)
    private Double totalTTC=Double.valueOf(0);

    @Column(nullable = false)
    private Double totalTVA=Double.valueOf(0);

    @Column(nullable = false)
    private Double taxStamp;

    @Column(nullable = false)
    private Double checkReference= Double.valueOf(0);

    @Column(nullable = false)
    private Boolean checkPayment;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean isDeleted=false;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bill")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(targetEntity=Historical.class, mappedBy="bill",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Historical> historicals = new ArrayList<>();


    @OneToMany(targetEntity=FileStorageProperties.class, mappedBy="bill",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileStorageProperties> fileStorageProperties= new ArrayList<>();




    //************Getters & Setters************


    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Double totalHT) {
        this.totalHT = totalHT;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Double getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(Double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Double getTaxStamp() {
        return taxStamp;
    }

    public void setTaxStamp(Double taxStamp) {
        this.taxStamp = taxStamp;
    }

    public Double getCheckReference() {
        return checkReference;
    }

    public void setCheckReference(Double checkReference) {
        this.checkReference = checkReference;
    }

    public Boolean getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(Boolean checkPayment) {
        this.checkPayment = checkPayment;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Historical> getHistoricals() {
        return historicals;
    }

    public void setHistoricals(List<Historical> historicals) {
        this.historicals = historicals;
    }

    public List<FileStorageProperties> getFileStorageProperties() {
        return fileStorageProperties;
    }

    public void setFileStorageProperties(List<FileStorageProperties> fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", provider='" + provider + '\'' +
                ", date=" + date +
                ", totalHT=" + totalHT +
                ", totalTTC=" + totalTTC +
                ", totalTVA=" + totalTVA +
                ", taxStamp=" + taxStamp +
                ", checkReference=" + checkReference +
                ", checkPayment=" + checkPayment +
                ", isDeleted=" + isDeleted +
                ", products=" + products +
                '}';
    }
}

package com.accountingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Bill {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Double checkReference = Double.valueOf(0);

    @Column(nullable = false)
    private Boolean checkPayment;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean isDeleted = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    private Quotation quotation;


    @JsonIgnore
    @OneToMany(targetEntity = Historical.class, mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Historical> historicals = new ArrayList<>();


    @OneToMany(targetEntity = FileStorageProperties.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileStorageProperties> fileStorageProperties = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
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
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", checkReference=" + checkReference +
                ", checkPayment=" + checkPayment +
                ", isDeleted=" + isDeleted +
                ", quotation=" + quotation +
                ", historicals=" + historicals +
                ", fileStorageProperties=" + fileStorageProperties +
                '}';
    }
}

package com.accountingapi.model;

import com.accountingapi.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(nullable = false)
    private Double totalHT= Double.valueOf(0);

    @Column(nullable = false)
    private Double totalTTC=Double.valueOf(0);

    @Column(nullable = false)
    private Double totalTVA=Double.valueOf(0);

    @Column(nullable = false)
    private Double taxStamp;

    @Column
    private Boolean isConfirmed=null;


    @OneToOne(mappedBy = "quotation", cascade = CascadeType.MERGE)
    private Bill bill;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "createdBy")
    private User createdBy;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "acceptedBy", nullable = true)
    private User acceptedBy;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "provider_id")
    private Provider provider;


    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases;

    @JsonIgnore
    @OneToMany(targetEntity = FileStorageProperties.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileStorageProperties> fileStorageProperties = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }


    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(User acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<FileStorageProperties> getFileStorageProperties() {
        return fileStorageProperties;
    }

    public void setFileStorageProperties(List<FileStorageProperties> fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }


    @Override
    public String toString() {
        return "Quotation{" +
                "id=" + id +
                ", creattionDate=" + creationDate +
                ", totalHT=" + totalHT +
                ", totalTTC=" + totalTTC +
                ", totalTVA=" + totalTVA +
                ", taxStamp=" + taxStamp +
                ", isConfirmed=" + isConfirmed +
                ", bill=" + bill +
                ", createdBy=" + createdBy +
                ", acceptedBy=" + acceptedBy +
                ", provider=" + provider +
                ", purchases=" + purchases +
                '}';
    }
}
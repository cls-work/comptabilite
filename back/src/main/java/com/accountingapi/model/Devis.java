/*package com.accountingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Devis {

    @Id
    @Column(name = "id",unique=true,nullable = false)
    private String id;

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
    private Boolean isConfirmed=false;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bill")
    private List<Product> products;


    @OneToMany(targetEntity=FileStorageProperties.class,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileStorageProperties> fileStorageProperties= new ArrayList<>();




    //************Getters & Setters************



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

}
*/
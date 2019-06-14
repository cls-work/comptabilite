package com.accounting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;


@Entity
public class Bill {

    @Id
    @Column(unique=true,nullable = false)
    private String id;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long totalHT;

    @Column(nullable = false)
    private Long totalTTC;

    @Column(nullable = false)
    private Long totalTVA;

    @Column(nullable = false)
    private Long taxStamp;

    @Column(nullable = false)
    private Long checkReference;

    @Size(min = 7, max = 7, message = "Check reference length must be 7")
    @Column(nullable = false)
    private Boolean checkPayment;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Long totalHT) {
        this.totalHT = totalHT;
    }

    public Long getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Long totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Long getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(Long totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Long getTaxStamp() {
        return taxStamp;
    }

    public void setTaxStamp(Long taxStamp) {
        this.taxStamp = taxStamp;
    }

    public Long getCheckReference() {
        return checkReference;
    }

    public void setCheckReference(Long checkReference) {
        this.checkReference = checkReference;
    }

    public Boolean getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(Boolean checkPayment) {
        this.checkPayment = checkPayment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

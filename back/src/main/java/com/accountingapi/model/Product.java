package com.accountingapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;


@Entity

public class Product {

    @Id
    @Column(unique=true,nullable = false)
    private String id;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long unitPrice;

    @Column(nullable = false)
    private Long discount;

    @Column(nullable = false)
    private Long TVA;

    @Column(nullable = false)
    private Long unitPriceAfterDiscount;

    @Column(nullable = false)
    private Long amountHT;

    @Column(nullable = false)
    private Long amountTVA;

    @Column(nullable = false)
    private Long amountTTC;

    @Size(min = 7, max = 7, message = "Check reference length must be 7")
    @Column(nullable = false)
    private Boolean checkPayment;

    @ManyToOne
    private Bill bill;


    //************Getters & Setters************

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getTVA() {
        return TVA;
    }

    public void setTVA(Long TVA) {
        this.TVA = TVA;
    }

    public Long getUnitPriceAfterDiscount() {
        return unitPriceAfterDiscount;
    }

    public void setUnitPriceAfterDiscount(Long unitPriceAfterDiscount) {
        this.unitPriceAfterDiscount = unitPriceAfterDiscount;
    }

    public Long getAmountHT() {
        return amountHT;
    }

    public void setAmountHT(Long amountHT) {
        this.amountHT = amountHT;
    }

    public Long getAmountTVA() {
        return amountTVA;
    }

    public void setAmountTVA(Long amountTVA) {
        this.amountTVA = amountTVA;
    }

    public Long getAmountTTC() {
        return amountTTC;
    }

    public void setAmountTTC(Long amountTTC) {
        this.amountTTC = amountTTC;
    }

    public Boolean getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(Boolean checkPayment) {
        this.checkPayment = checkPayment;
    }
}

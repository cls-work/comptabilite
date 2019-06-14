package com.accountingapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity

public class Product {

    @Id
    @Column(unique=true,nullable = false)
    private String product_id;

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

    @JsonIgnore
    @ManyToOne
    @MapsId("bill_id")
    @JoinColumn(name="bill_id")
    private Bill bill;


    //************Getters & Setters************


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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


package com.accountingapi.model;


import javax.persistence.*;

@Entity

public class Product {

    @Id
    @Column(name="product_id",unique=true,nullable = false)
    private String productId;

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
    private Long amountHT= Long.valueOf(0);

    @Column(nullable = false)
    private Long amountTVA;

    @Column(nullable = false)
    private Long amountTTC;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill= new Bill();

    //************Constructor*****************


    public Product() {
    }

    public Product(String productId, String designation, Long quantity, Long unitPrice, Long discount, Long TVA, Long unitPriceAfterDiscount, Long amountHT, Long amountTVA, Long amountTTC, Boolean checkPayment, Bill bill) {
        this.productId = productId;
        this.designation = designation;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.TVA = TVA;
        this.unitPriceAfterDiscount = unitPriceAfterDiscount;
        this.amountHT = amountHT;
        this.amountTVA = amountTVA;
        this.amountTTC = amountTTC;
        this.bill = bill;
    }


    //************Getters & Setters************


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

}


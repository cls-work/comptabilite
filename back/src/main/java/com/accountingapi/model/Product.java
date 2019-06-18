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
    private Double unitPrice;

    @Column(nullable = false)
    private Long discount;

    @Column(nullable = false)
    private Long TVA;

    @Column(nullable = false)
    private Double unitPriceAfterDiscount;

    @Column(nullable = false)
    private Double amountHT= Double.valueOf(0);

    @Column(nullable = false)
    private Double amountTVA;

    @Column(nullable = false)
    private Double amountTTC;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill= new Bill();

    //************Constructor*****************


    public Product() {
    }

    public Product(String productId, String designation, Long quantity, Double unitPrice, Long discount, Long TVA, Double unitPriceAfterDiscount, Double amountHT, Double amountTVA, Double amountTTC, Boolean checkPayment, Bill bill) {
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
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

    public Double getUnitPriceAfterDiscount() {
        return unitPriceAfterDiscount;
    }

    public void setUnitPriceAfterDiscount(Double unitPriceAfterDiscount) {
        this.unitPriceAfterDiscount = unitPriceAfterDiscount;
    }

    public Double getAmountHT() {
        return amountHT;
    }

    public void setAmountHT(Double amountHT) {
        this.amountHT = amountHT;
    }

    public Double getAmountTVA() {
        return amountTVA;
    }

    public void setAmountTVA(Double amountTVA) {
        this.amountTVA = amountTVA;
    }

    public Double getAmountTTC() {
        return amountTTC;
    }

    public void setAmountTTC(Double amountTTC) {
        this.amountTTC = amountTTC;
    }

}


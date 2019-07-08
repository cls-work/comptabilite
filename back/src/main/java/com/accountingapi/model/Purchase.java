package com.accountingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double TVA;

    @Column(nullable = false)
    private Double unitPriceAfterDiscount;

    @Column(nullable = false)
    private Double amountHT= Double.valueOf(0);

    @Column(nullable = false)
    private Double amountTVA;

    @Column(nullable = false)
    private Double amountTTC;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id")
    private Quotation quotation;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTVA() {
        return TVA;
    }

    public void setTVA(Double TVA) {
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

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", TVA=" + TVA +
                ", unitPriceAfterDiscount=" + unitPriceAfterDiscount +
                ", amountHT=" + amountHT +
                ", amountTVA=" + amountTVA +
                ", amountTTC=" + amountTTC +
                ", quotation=" + quotation +
                ", product=" + product +
                '}';
    }
}

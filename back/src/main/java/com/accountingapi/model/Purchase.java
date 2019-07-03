package com.accountingapi.model;

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

    @ManyToOne
    @JoinColumn(name="quotation_id", nullable=false)
    private Quotation quotation;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;


}

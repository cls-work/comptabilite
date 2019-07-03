package com.accountingapi.model;


import javax.persistence.*;

@Entity

public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="refrence",unique=true,nullable = false)
    private String refrence;

    @Column(nullable = false)
    private String designation;


    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;


}


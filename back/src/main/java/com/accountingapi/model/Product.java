package com.accountingapi.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Entity

@JsonAutoDetect
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", unique = true, nullable = false)
    private String reference;

    @Column(nullable = false)
    private String designation;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", reference='" + reference + '\'' +
                ", designation='" + designation + '\'' +
                ", category=" + category +
                '}';
    }
}


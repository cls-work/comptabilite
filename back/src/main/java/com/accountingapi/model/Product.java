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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefrence() {
        return refrence;
    }

    public void setRefrence(String refrence) {
        this.refrence = refrence;
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
                ", refrence='" + refrence + '\'' +
                ", designation='" + designation + '\'' +
                ", category=" + category +
                '}';
    }
}


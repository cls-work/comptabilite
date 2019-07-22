package com.accountingapi.model;

import java.util.List;

public class ProductsCategory {

    private Category category;

    private List<Product> products;

    public ProductsCategory(Category category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

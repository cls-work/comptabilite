package com.accountingapi.model;

import java.util.List;

public class PurchasesCategory {

    private Category category;

    private List<Purchase> purchases;

    public PurchasesCategory(Category category, List<Purchase> purchases) {
        this.category = category;
        this.purchases = purchases;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}

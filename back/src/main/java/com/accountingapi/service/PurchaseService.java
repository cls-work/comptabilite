package com.accountingapi.service;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;

import java.util.List;

public interface PurchaseService {

    Purchase addPurchase(Purchase purchase);

    void deletePurchaseById(Long purchaseId);

    Purchase updatePurchase(Purchase purchase);

    Purchase findPurchaseById(Long purchaseId);

    List<Purchase> findAllPurchasesByQuotation(Long quotationId);

    boolean existsById(Long purchaseId);

    List<Purchase> findAllPurchases();
}

package com.accountingapi.service.impl;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;
import com.accountingapi.repository.PurchaseRepository;
import com.accountingapi.service.PurchaseService;
import com.accountingapi.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    QuotationServiceImpl quotationService;


    @Override
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchaseById(Long purchaseId) {
        Purchase purchase = findPurchaseById(purchaseId);
        Quotation quotation = purchase.getQuotation();
        quotation.setTotalHT(quotation.getTotalHT() - purchase.getAmountHT());
        quotation.setTotalTVA(quotation.getTotalTVA() - purchase.getAmountTVA());
        quotation.setTotalTTC(quotation.getTotalTTC() - purchase.getAmountTTC());
        quotationService.updateQuotation(quotation);
        purchaseRepository.delete(purchase);
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId).get();
    }

    @Override
    public List<Purchase> findAllPurchasesByQuotation(Long quotationId) {
        Quotation quotation = quotationService.getQuotationById(quotationId);
        return purchaseRepository.findAllByQuotation(quotation);
    }

}

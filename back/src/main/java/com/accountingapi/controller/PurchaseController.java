package com.accountingapi.controller;

import com.accountingapi.model.Purchase;
import com.accountingapi.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseServiceImpl purchaseService;

    @GetMapping("/quotation/{quotationId}")
    public List<Purchase> findAllPurchasesByQuotation(@PathVariable("quotationId") Long quotationId) {
        return purchaseService.findAllPurchasesByQuotation(quotationId);
    }

    @DeleteMapping("/{purchaseId}")
    public void deletePurchaseFromQuotation(@PathVariable("purchaseId") Long purchaseId) {
        purchaseService.deletePurchaseById(purchaseId);
    }

    @PostMapping
    public Purchase addPurchase(@Valid @RequestBody Purchase purchase) {
        return purchaseService.addPurchase(purchase);
    }

    @PutMapping
    public Purchase updatePurchase(@Valid @RequestBody Purchase purchase) {
        return purchaseService.updatePurchase(purchase);
    }

    @GetMapping("/{purchaseId}")
    public Purchase findPurchaseById(@PathVariable("purchaseId") Long purchaseId) {
        return purchaseService.findPurchaseById(purchaseId);
    }


}

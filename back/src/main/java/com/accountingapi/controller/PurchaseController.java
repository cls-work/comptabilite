package com.accountingapi.controller;

import com.accountingapi.model.Category;
import com.accountingapi.model.Product;
import com.accountingapi.model.Purchase;
import com.accountingapi.model.PurchasesCategory;
import com.accountingapi.service.impl.CategoryServiceImpl;
import com.accountingapi.service.impl.ProductServiceImpl;
import com.accountingapi.service.impl.PurchaseServiceImpl;
import com.accountingapi.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseServiceImpl purchaseService;

    @Autowired
    QuotationServiceImpl quotationService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    ProductServiceImpl productService;

    // -------------------Retrieve All Purchases By QUOTATION ID---------------------------------------------
    @GetMapping("/quotation/{quotationId}")
    public ResponseEntity<List<Purchase>> findAllPurchasesByQuotation(@PathVariable("quotationId") Long quotationId) {
        if (quotationService.existsById(quotationId)) {
            List<Purchase> purchases = purchaseService.findAllPurchasesByQuotation(quotationId);
            if (purchases.isEmpty()) {
                System.out.println("*****No Puchases for this quotation");
                return new ResponseEntity("No Purchases for this quotation", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(purchases, HttpStatus.OK);
            }
        } else {
            System.out.println("*****No Quotation with this ID");
            return new ResponseEntity("Quotation with id " + quotationId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    // -------------------Retrieve All Purchases---------------------------------------------
    @GetMapping
    public ResponseEntity<List<Purchase>> findAllPurchases() {
        List<Purchase> purchases = purchaseService.findAllPurchases();
        if (purchases.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    // -------------------Delete a Purchase---------------------------------------------
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<?> deletePurchaseFromQuotation(@PathVariable("purchaseId") Long purchaseId) {
        if (purchaseService.existsById(purchaseId)) {
            purchaseService.deletePurchaseById(purchaseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Purchase with id" + purchaseId + " not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Create a Purchase---------------------------------------------
    @PostMapping
    public ResponseEntity<?> addPurchase(@Valid @RequestBody Purchase purchase) {
        purchaseService.addPurchase(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    // -------------------Update a Purchase---------------------------------------------
    @PutMapping
    public ResponseEntity<?> updatePurchase(@Valid @RequestBody Purchase purchase) {
        if (purchaseService.existsById(purchase.getId())) {
            purchaseService.updatePurchase(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }
        return new ResponseEntity("Purchase with id" + purchase.getId() + " not found",
                HttpStatus.NOT_FOUND);
    }

    // -------------------Retrieve One Purchase By ID---------------------------------------------
    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> findPurchaseById(@PathVariable("purchaseId") Long purchaseId) {
        if (purchaseService.existsById(purchaseId))
            return new ResponseEntity<Purchase>(purchaseService.findPurchaseById(purchaseId), HttpStatus.OK);
        else return new ResponseEntity("Purchase not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Retrieve All Purchases By All Categories---------------------------------------------
    @GetMapping("/purchasesByCategory")
    public ResponseEntity<?> FindAllPurchasesByCategories() {
        List<PurchasesCategory> purchasesCategoryList = new ArrayList<>();
        List<Category> categories = categoryService.findAllCategories();
        for (Category category : categories) {
            List<Product> products = productService.findProductsByCategory(category);
            PurchasesCategory purchasesCategory = new PurchasesCategory();
            purchasesCategory.setCategory(category);
            purchasesCategory.setPurchases(new ArrayList<>());
            for (Product product : products) {
                List<Purchase> purchases = purchaseService.findAllPurchasesByProduct(product);
                purchasesCategory.addPurchases(purchases);

            }
            purchasesCategoryList.add(purchasesCategory);
        }
        return new ResponseEntity<>(purchasesCategoryList, HttpStatus.OK);

    }


}

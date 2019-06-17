package com.accountingapi.controller;


import com.accountingapi.model.Product;
import com.accountingapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;


    //****************A VERIFIER POST OU GET
    @GetMapping("/{billId}")
    public List<Product> getProductsByBillId(@PathVariable("billId") String billId){
        return productService.getProductsByBillId(billId);
    }

    @PostMapping("/{billId}")
    public void addProduct (@PathVariable("billId") String billId,@RequestBody List<Product> products) {
        productService.addProduct(billId,products);
    }

    @DeleteMapping("/{productId}")
    public boolean deleteProduct (@PathVariable("productId") String productId){
        return productService.deleteProduct(productId);
    }


}
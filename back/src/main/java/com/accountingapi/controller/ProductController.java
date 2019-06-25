package com.accountingapi.controller;


import com.accountingapi.model.Product;
import com.accountingapi.service.BillService;
import com.accountingapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    BillService billService;


    /*
        Get product by its id
    */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{billId}")
    public List<Product> getProductsByBillId(@PathVariable("billId") String billId){
        return productService.getProductsByBillId(billId);
    }

    /*
        add a new product
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{billId}")
    public void addProduct (@PathVariable("billId") String billId,@Valid  @RequestBody List<Product> products) {
        productService.addProduct(billId,products);
    }


    /*
        Delete product by its id
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{productId}")
    @Transactional
    public void deleteProductById (@PathVariable("productId") String productId){
         productService.deleteProductById(productId);
    }

    /*
        update product by its id
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{productId}")
    public Product editProduct (@PathVariable String productId,@Valid @RequestBody Product product){

        product.setProductId(productId);
        return productService.updateProduct(product);
    }

}
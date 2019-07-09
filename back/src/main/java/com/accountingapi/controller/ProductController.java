package com.accountingapi.controller;

import com.accountingapi.model.Product;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {


    @Autowired
    ProductServiceImpl productService;


    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }


    @PostMapping()
    public void addProduct(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody Product product) {
        productService.addProduct(product);
    }


    @DeleteMapping("/{productId}")
    @Transactional
    public void deleteProductById(@PathVariable("productId") Long productId) {
        productService.deleteProductById(productId);
    }


    @PutMapping("/{productId}")
    public Product updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }


}

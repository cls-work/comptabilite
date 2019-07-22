package com.accountingapi.controller;

import com.accountingapi.model.Category;
import com.accountingapi.model.Product;
import com.accountingapi.model.PurchasesCategory;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.service.impl.CategoryServiceImpl;
import com.accountingapi.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {


    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CategoryServiceImpl categoryService;

    // -------------------Retrieve All Products---------------------------------------------
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // -------------------Retrieve One Product By ID---------------------------------------------
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable("productId") Long productId) {
        if (productService.existsById(productId))
            return new ResponseEntity<Product>(productService.findProductById(productId), HttpStatus.OK);
        else return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Create a Product---------------------------------------------
    @PostMapping()
    public ResponseEntity<?> addProduct(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody Product product) {
        if (productService.existsByReference(product.getReference()))
            return new ResponseEntity("Product with same reference already exists", HttpStatus.CONFLICT);
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // -------------------Delete a Product---------------------------------------------
    @DeleteMapping("/{productId}")
    @Transactional
    public ResponseEntity<?> deleteProductById(@PathVariable("productId") Long productId) {
        if (productService.existsById(productId)) {
            productService.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Product with id" + productId + " not found", HttpStatus.NOT_FOUND);

    }

    // -------------------Update a Product---------------------------------------------
    @PutMapping
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product) {
        if (productService.existsById(product.getId())) {
            productService.updateProduct(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity("Product with id" + product.getId() + " not found",
                HttpStatus.NOT_FOUND);
    }





}
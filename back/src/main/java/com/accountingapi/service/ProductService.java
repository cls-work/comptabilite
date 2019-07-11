package com.accountingapi.service;

import com.accountingapi.model.Product;

import java.util.List;

public interface ProductService {


    Product addProduct(Product product);

    Product updateProduct(Product product);

    Product findProductById(Long productId);

    void deleteProductById(Long productId);

    List<Product> findAllProducts();

    boolean existsById(Long productId);

    boolean existsByReference(String reference);
}

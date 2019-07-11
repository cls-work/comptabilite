package com.accountingapi.service.impl;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Product;
import com.accountingapi.repository.ProductRepository;
import com.accountingapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    //Add Product
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }


    //Update Product
    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    //Find Product by its id
    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    //Delete product by its id
    @Override
    public void deleteProductById(Long productId) {
        Product product = findProductById(productId);
        productRepository.delete(product);

    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean existsById(Long productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public boolean existsByReference(String reference) {
        return productRepository.existsByReference(reference);
    }

}
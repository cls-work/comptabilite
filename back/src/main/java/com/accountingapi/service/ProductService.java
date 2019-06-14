package com.accountingapi.service;


import com.accountingapi.model.Product;
import com.accountingapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product){

        productRepository.save(product);
    }

    public List<Product> getProductsByBillId(String id){
        return productRepository.getProductsByBillId(id);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
}

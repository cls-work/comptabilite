package com.accountingapi.service;

import com.accountingapi.model.Product;
import com.accountingapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product){

        productRepository.save(product);
    }

   /* public List<Product> getProductsByBillId(String id){
        return productRepository.findProductsByBill_Bill_id(id);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }*/
}

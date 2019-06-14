package com.accountingapi.repository;

import com.accountingapi.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,String> {

    public List<Product> findProductsByBill(String id );

}

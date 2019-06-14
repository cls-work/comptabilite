package com.accountingapi.repository;

import com.accountingapi.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,String> {

    List<Product> findProductsByBill_Bill_id(String id);

}

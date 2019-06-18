package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findProductsByBill(Bill bill);

    Product findByProductId(String id);

    void deleteByProductId(String id);


}

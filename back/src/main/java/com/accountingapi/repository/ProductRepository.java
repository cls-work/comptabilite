package com.accountingapi.repository;

import com.accountingapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {


    Product findByProductId(String id);

    void deleteByProductId(String id);


}

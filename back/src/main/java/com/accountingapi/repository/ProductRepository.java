package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findProductsByBill(Bill bill);

    Product findByProductId(String id);

    boolean deleteByProductId(String id);

    @Query(value = "INSERT INTO PRODUCT (tva,amounttht,amountttc,amounttva,check_payment,designation,discount,quantity,unit_price_after_discount_product_id,bill_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", nativeQuery = true)
    Product savee(Product product);



}

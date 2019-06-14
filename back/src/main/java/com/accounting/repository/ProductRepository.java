package com.accounting.repository;

import com.accounting.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,String > {
}

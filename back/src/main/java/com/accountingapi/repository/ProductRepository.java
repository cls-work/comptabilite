package com.accountingapi.repository;

import com.accountingapi.model.Category;
import com.accountingapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    List<Product> findAllByCategory(Category category);


    boolean existsByReference(String reference);
}

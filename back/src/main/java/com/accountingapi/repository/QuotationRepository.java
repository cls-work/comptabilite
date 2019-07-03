package com.accountingapi.repository;

import com.accountingapi.model.Product;
import com.accountingapi.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {

    List<Quotation> findAll();

    Quotation findByQuotationId(Long id);

    List<Quotation> findByProducts(List<Product> products);

}
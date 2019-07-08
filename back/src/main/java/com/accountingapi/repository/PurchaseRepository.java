package com.accountingapi.repository;

import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    List<Purchase> findAllByQuotation(Quotation quotation);
}

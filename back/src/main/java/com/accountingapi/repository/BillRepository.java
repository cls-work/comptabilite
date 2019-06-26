package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<Bill,String> {

     List<Bill> findAll();

     Bill findByBillId(String id);

}

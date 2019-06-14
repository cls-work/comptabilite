package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillRepository extends CrudRepository<Bill,String> {

    public List<Bill> findAll();

    public Bill findByBillId(String id);
}

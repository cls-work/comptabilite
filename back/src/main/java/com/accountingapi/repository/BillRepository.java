package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository extends CrudRepository<Bill,String> {

}

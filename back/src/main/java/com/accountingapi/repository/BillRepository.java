package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,String> {

}

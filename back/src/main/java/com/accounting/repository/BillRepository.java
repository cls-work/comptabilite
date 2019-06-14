package com.accounting.repository;

import com.accounting.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,String> {

}

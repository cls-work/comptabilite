package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalRepository extends CrudRepository<Historical,Long> {

    List<Historical> findAll();
    Historical getById(Long id);
    List<Historical> findAllByUser(User user);
    List<Historical> findAllByBill(Bill bill);
}

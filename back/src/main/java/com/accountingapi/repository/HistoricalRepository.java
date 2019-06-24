package com.accountingapi.repository;

import com.accountingapi.model.Historical;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalRepository extends CrudRepository<Historical,Long> {

    //public List<Historical> histories
}

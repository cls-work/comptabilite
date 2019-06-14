package com.accountingapi.repository;

import com.accountingapi.model.Historical;

import org.springframework.data.repository.CrudRepository;

public interface HistoricalRepository extends CrudRepository<Historical,Long> {
}

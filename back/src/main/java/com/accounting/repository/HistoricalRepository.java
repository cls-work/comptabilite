package com.accounting.repository;

import com.accounting.model.Historical;
import org.springframework.data.repository.CrudRepository;

public interface HistoricalRepository extends CrudRepository<Historical,Long> {
}

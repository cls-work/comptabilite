package com.accountingapi.repository;

import com.accountingapi.model.Category;
import com.accountingapi.model.Check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CheckRepository extends JpaRepository<Category,Long>{

    List<Check> findAll();

    Check findByCheckId(String id);

}


package com.accountingapi.repository;

import com.accountingapi.model.Check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {

}


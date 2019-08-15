
package com.accountingapi.repository;


import com.accountingapi.model.CheckPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CheckRepository extends JpaRepository<CheckPayment, Long> {

}


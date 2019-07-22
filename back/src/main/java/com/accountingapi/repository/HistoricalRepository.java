package com.accountingapi.repository;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalRepository extends JpaRepository<Historical, Long> {

    List<Historical> findAllByUser(User user);

}

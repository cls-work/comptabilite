package com.accountingapi.service;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.security.model.User;

import java.util.List;

public interface HistoricalService {

    Historical addHistorical(Historical historical);

    List<Historical> findAllHistoricals();

    List<Historical> findAllHistoricalsByUser(User user);

    List<Historical> findAllHistoricalsByBill(Bill bill);

    Historical findById(Long historicalId);
}

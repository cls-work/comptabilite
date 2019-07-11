/*
package com.accountingapi.service.impl;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.repository.HistoricalRepository;
import com.accountingapi.security.model.User;
import com.accountingapi.service.HistoricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalServiceImpl implements HistoricalService {

    @Autowired
    HistoricalRepository historicalRepository;

    @Override
    public Historical addHistorical(Historical historical) {
        return historicalRepository.save(historical);
    }

    public List<Historical> findAllHistoricals() {
        return historicalRepository.findAll();
    }

    public List<Historical> findAllHistoricalsByUser(User user) {
        return historicalRepository.findAllByUser(user);
    }

    public List<Historical> findAllHistoricalsByBill(Bill bill) {
        return historicalRepository.findAllByBill(bill);
    }

    public Historical findById(Long historicalId) {
        return historicalRepository.findById(historicalId).get();
    }

}
*/

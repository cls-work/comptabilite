
package com.accountingapi.service.impl;

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

    @Override
    public List<Historical> findAllHistoricals() {
        return historicalRepository.findAll();
    }

    @Override
    public List<Historical> findAllHistoricalsByUser(User user) {
        return historicalRepository.findAllByUser(user);
    }

    @Override
    public Historical findHistoricalById(Long historicalId) {
        return historicalRepository.findById(historicalId).get();
    }

    @Override
    public boolean existsById(Long historicalId) {
        return historicalRepository.existsById(historicalId);
    }
}


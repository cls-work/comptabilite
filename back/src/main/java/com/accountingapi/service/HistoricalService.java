package com.accountingapi.service;

import com.accountingapi.model.Historical;
import com.accountingapi.repository.HistoricalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricalService {

    @Autowired
    private HistoricalRepository historicalRepository;

    public Historical addHistorical(Historical historical){
        historicalRepository.save(historical);
        return historical;
    }

    public String addComment(String comment){
        return "this is a comment";
    }

}

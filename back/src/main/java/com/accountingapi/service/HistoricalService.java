package com.accountingapi.service;

import com.accountingapi.model.Historical;
import com.accountingapi.repository.HistoricalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricalService {

    @Autowired
    private HistoricalRepository historicalRepository;

    public void addHistorical(Historical historical){
        historicalRepository.save(historical);
    }

    /*public void addComment(String comment){
        return
    }*/

}

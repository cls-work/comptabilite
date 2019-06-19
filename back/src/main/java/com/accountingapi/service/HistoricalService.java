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
        System.out.println("add historical 1");
        System.out.println(historical.getBill().getBillId());
        System.out.println(historical.getUser().getId());
        historicalRepository.save(historical);
        System.out.println("add historical 2");
    }

    public String addComment(String comment){
        return "this is a comment";
    }

}

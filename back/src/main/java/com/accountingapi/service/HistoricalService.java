package com.accountingapi.service;

import com.accountingapi.model.BillHistorical;
import com.accountingapi.model.Historical;
import com.accountingapi.repository.HistoricalRepository;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricalService {

    @Autowired
    private HistoricalRepository historicalRepository;

    @Autowired
    private UserRepository userRepository;

    public void addHistorical(UserPrincipal currentUser, BillHistorical billHistorical){
        Long userId=currentUser.getId();
        User user=userRepository.getById(userId);
        Historical historical=billHistorical.getHistorical();
        historical.setBill(billHistorical.getBill());
        historical.setUser(user);
        historicalRepository.save(historical);
    }

    public String addComment(String comment){
        return "this is a comment";
    }

}

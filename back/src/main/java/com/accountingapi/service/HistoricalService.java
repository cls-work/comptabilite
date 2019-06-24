package com.accountingapi.service;

import com.accountingapi.dto.BillHistoricalDto;
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

    public void addHistorical(UserPrincipal currentUser, BillHistoricalDto billHistoricalDto){
        Long userId=currentUser.getId();
        User user=userRepository.getById(userId);
        Historical historical= billHistoricalDto.getHistorical();
        historical.setBill(billHistoricalDto.getBill());
        historical.setUser(user);
        historical.setDate(LogicService.getCurrentTimeUsingCalendar());
        historicalRepository.save(historical);
    }

    public String addComment(String comment){
        return "this is a comment";
    }

}

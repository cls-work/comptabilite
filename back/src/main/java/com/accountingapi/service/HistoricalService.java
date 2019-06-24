package com.accountingapi.service;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.repository.HistoricalRepository;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalService {

    @Autowired
    private HistoricalRepository historicalRepository;

    @Autowired
    private UserRepository userRepository;

    public void addHistorical(UserPrincipal currentUser, String message, Bill bill){
        Long userId=currentUser.getId();
        User user=userRepository.getById(userId);
        Historical historical= new Historical();
        historical.setBill(bill);
        historical.setUser(user);
        historical.setMessage("User "+user.getName()+" "+message);
        historical.setDate(LogicService.getCurrentTimeUsingCalendar());
        historicalRepository.save(historical);
    }

    public List<Historical> findAll(){
        return historicalRepository.findAll();
    }

    public List<Historical> findAllByUser(User user){
        return historicalRepository.findAllByUser(user);
    }

    public List<Historical> findAllByBill(Bill bill){
        return historicalRepository.findAllByBill(bill);
    }

    public Historical findById(Long historicalId){
        return historicalRepository.getById(historicalId);
    }

}

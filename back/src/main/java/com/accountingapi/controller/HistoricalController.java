package com.accountingapi.controller;


import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.BillService;
import com.accountingapi.service.impl.BillService;
import com.accountingapi.service.HistoricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/historicals")
public class HistoricalController {


    @Autowired
    private HistoricalService historicalService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillService billService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<Historical> displayAllHistorical(){
        return historicalService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{historicalId}")
    public Historical getHistoricalById(@PathVariable("historicalId") Long historicalId){
        return historicalService.findById(historicalId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    public List<Historical> getHistoricalByUserId(@PathVariable("userId") Long userId){
        User user = userRepository.findById(userId).get();
        return historicalService.findAllByUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{billId}")
    public List<Historical> getHistoricalByUserId(@PathVariable("billId") String billId){
        Bill bill = billService.getBillById(billId);
        return historicalService.findAllByBill(bill);
    }
}
*/

package com.accountingapi.controller;


import com.accountingapi.model.Bill;
import com.accountingapi.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("")
    public List<Bill> displayAllBills(){
        return billService.findAll();
    }

    @GetMapping("/{id}")
    public Bill displayAllBills(@PathVariable("id") String id){
        return billService.findById(id);
    }
}

package com.accountingapi.controller;


import com.accountingapi.dto.BillHistoricalDto;
import com.accountingapi.model.Bill;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.BillService;
import com.accountingapi.service.HistoricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    BillService billService;
    @Autowired
    HistoricalService historicalService;

    @Autowired
    UserRepository userRepository;

   // @PreAuthorize("hasRole('USER')")

    @GetMapping("")
    public List<Bill> displayAllBills(){
        return billService.findAll();
    }// na7i les bills isdeleted=true

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable("id") String id){
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteBill( @PathVariable String id) {

        Bill bill = billService.getBillById(id);
        bill.setDeleted(true);
        billService.updateBill(bill);
        return bill.getDeleted();
    }

    @PostMapping("")
    public Bill addBill(@CurrentUser UserPrincipal currentUser, @RequestBody BillHistoricalDto billHistoricalDto){

        Bill newBill=billService.addBill(billHistoricalDto.getBill());
        billHistoricalDto.setBill(newBill);
        historicalService.addHistorical(currentUser, billHistoricalDto);
        return newBill;
    }

    @PutMapping("/{billId}")
    public Bill editBill(@PathVariable String billId,@Valid @RequestBody Bill bill){

        bill.setBillId(billId);
        return billService.updateBill(bill);
    }


}

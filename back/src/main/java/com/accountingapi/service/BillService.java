
package com.accountingapi.service;

import com.accountingapi.model.Bill;
import com.accountingapi.repository.BillRepository;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private HistoricalService historicalService;

    @Autowired
    private UserRepository userRepository;

    //Bill save into database
    public Bill addBill(Bill bill){
        bill.setBillId(LogicService.getAlphaNumericString(5));
        return(billRepository.save(bill));

    }

    public List<Bill> findAll(){
        List<Bill> allbills = new ArrayList<>();
        allbills=billRepository.findAll();
        List<Bill> bills = new ArrayList<>();
        for(Bill bill:allbills){
            if(!bill.getDeleted()){
                bills.add(bill);
            }
        }

        return bills;
    }

    public Bill getBillById(String id){
        return billRepository.findByBillId(id);
    }

    //Bill update
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    public void deleteBill(Bill bill){
        bill.setDeleted(true);
    }
}

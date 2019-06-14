
package com.accountingapi.service;
import com.accountingapi.model.Bill;


import com.accountingapi.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    //Bill save into database
    public void addBill(Bill bill){
        billRepository.save(bill);
    }

    //Bill update
    public void updateBill(Bill bill){
        billRepository.save(bill);
    }

    public void deleteBill(Bill bill){
        bill.setDeleted(true);
    }
}

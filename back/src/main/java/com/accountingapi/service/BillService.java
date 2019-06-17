
package com.accountingapi.service;
import com.accountingapi.model.Bill;


import com.accountingapi.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    //Bill save into database
    public Bill addBill(Bill bill){
        bill.setBillId(IdService.getAlphaNumericString(20));
        return(billRepository.save(bill));

    }

    public List<Bill> findAll(){
        List<Bill> allbills = new ArrayList<>();
        allbills=billRepository.findAll();
        List<Bill> bills = new ArrayList<>();
        for(Bill bill:allbills){
            if(bill.getDeleted()){
                bills.add(bill);
            }
        }

        return bills;
    }

    public Bill getBillById(String id){
        return billRepository.findByBillId(id);
    }

    //Bill update
    public Bill updateBill(String billId,Bill bill){

        Bill oldBill = billRepository.findByBillId(billId);
        oldBill =bill;
        return billRepository.save(oldBill);
    }

    public void deleteBill(Bill bill){
        bill.setDeleted(true);
    }
}

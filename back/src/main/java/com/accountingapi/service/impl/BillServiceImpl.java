package com.accountingapi.service.impl;

import com.accountingapi.model.Bill;
import com.accountingapi.repository.BillRepository;
import com.accountingapi.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;


    @Override
    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> findAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(String billId) {
        return billRepository.findById(billId).get();
    }

}

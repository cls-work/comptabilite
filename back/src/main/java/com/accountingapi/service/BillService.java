package com.accountingapi.service;

import com.accountingapi.model.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    Bill addBill(Bill bill);

    List<Bill> findAllBills();

    Bill findBillById(Long billId);

    boolean existsById(Long billId);

    Bill updateBill(Bill bill);

}

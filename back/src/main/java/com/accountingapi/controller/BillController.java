package com.accountingapi.controller;


import com.accountingapi.model.Bill;
import com.accountingapi.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    BillService billService;

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
    public Bill addBill(@RequestBody Bill bill ){
        billService.addBill(bill);
        return bill;
    }

    @PutMapping("/{billId}")
    public Bill editBill(@PathVariable String billId,@RequestBody Bill bill){

        Bill oldBill = billService.getBillById(billId);
        oldBill.setCheckPayment(bill.getCheckPayment()?bill.getCheckPayment():oldBill.getCheckPayment());
        oldBill.setCheckReference(bill.getCheckReference()!=null?bill.getCheckReference(): oldBill.getCheckReference());
        oldBill.setProvider(bill.getProvider()!=null?bill.getProvider():oldBill.getProvider());
        oldBill.setTaxStamp(bill.getTaxStamp()!=null?bill.getTaxStamp():oldBill.getTaxStamp());
        oldBill.setDate(bill.getDate()!=null?bill.getDate():oldBill.getDate());
        oldBill.setTotalHT(bill.getTotalHT()!=null?bill.getTotalHT():oldBill.getTotalHT());
        oldBill.setTotalTTC(bill.getTotalTTC()!=null?bill.getTotalTTC():oldBill.getTotalTTC());
        oldBill.setTotalTVA(bill.getTotalTVA()!=null?bill.getTotalTVA():oldBill.getTotalTVA());
        oldBill.setProducts(bill.getProducts()!=null?bill.getProducts():oldBill.getProducts());
        
        System.out.println("************************"+bill.toString());
        return billService.updateBill(oldBill);
    }

}

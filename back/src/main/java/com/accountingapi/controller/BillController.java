
package com.accountingapi.controller;



import com.accountingapi.dto.BillRequestDto;
import com.accountingapi.model.Bill;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.repository.FileStoragePropertiesRepository;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.impl.BillServiceImpl;
import com.accountingapi.service.impl.FileStorageServiceImpl;
import com.accountingapi.service.impl.HistoricalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    BillServiceImpl billService;

    @Autowired
    HistoricalServiceImpl historicalService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileStorageServiceImpl fileStorageService;


    @GetMapping
    public List<Bill> displayAllBills() {
        return billService.findAllBills();
    }



    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable("id") String id) {
        return billService.getBillById(id);
    }



    @DeleteMapping("/{id}")
    public void deleteBill(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {

        Bill bill = billService.getBillById(id);
        bill.setDeleted(true);
        /*historicalService.addHistoricalForBill(currentUser, "deleted a Bill", bill);
        billService.updateBill(bill);
        return bill.getDeleted();
        */
    }


    @PostMapping
    public Bill addBill(@CurrentUser UserPrincipal currentUser, @RequestBody BillRequestDto billRequestDto) {
        Bill bill = billRequestDto.toBill();


        if (billRequestDto.getDocumentIds() != null) {

            List<Long> documentsIds = billRequestDto.getDocumentIds();
            List<FileStorageProperties> documents = (List<FileStorageProperties>) fileStorageService.findAllById(documentsIds);
            bill.setFileStorageProperties(documents);

        }
        Bill newBill = billService.addBill(bill);
        // historicalService.addHistoricalForBill(currentUser, "added a new BillId= "+bill.getId(), bill);
        return newBill;


    }

/*
    @PutMapping("/{billId}")
    public Bill editBill(@CurrentUser UserPrincipal currentUser, @PathVariable String billId, @Valid @RequestBody BillRequestDto billRequestDto) {

        Bill bill = billRequestDto.toBill();
        bill.setId(billId);

        if (billRequestDto.getDocumentIds() != null) {

            List<Long> documentsIds = billRequestDto.getDocumentIds();
            List<FileStorageProperties> documents = (List<FileStorageProperties>) fileStorageService.findAllById(documentsIds);
            bill.setFileStorageProperties(documents);
            //documents.forEach(elt -> elt.setBill(bill));

        }

        Bill newBill = billService.a(bill);
        //historicalService.addHistoricalForBill(currentUser, "updated a Bill", bill);
        return newBill;

    }
    */


}

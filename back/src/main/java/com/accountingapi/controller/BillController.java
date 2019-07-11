
package com.accountingapi.controller;

import com.accountingapi.dto.BillRequestDto;
import com.accountingapi.model.Bill;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.model.Historical;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.impl.BillServiceImpl;
import com.accountingapi.service.impl.FileStorageServiceImpl;
import com.accountingapi.service.impl.HistoricalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // -------------------Retrieve All Bills---------------------------------------------
    @GetMapping
    public ResponseEntity<List<Bill>> findAllBills() {
        List<Bill> bills = billService.findAllBills();
        if (bills.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }


    // -------------------Retrieve One Bill By ID---------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") String id) {
        if (billService.existsById(id))
            return new ResponseEntity<Bill>(billService.findBillById(id), HttpStatus.OK);
        else return new ResponseEntity("Bill not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Delete a Bill---------------------------------------------


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {
        if (billService.existsById(id)) {
            Bill bill = billService.findBillById(id);
            bill.setDeleted(true);
            historicalService.addHistorical(new Historical());
            billService.updateBill(bill);
            return new ResponseEntity<>("Bill isDeleted setted to true and added to historical", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Quotation with id" + id + " not found", HttpStatus.NOT_FOUND);


    }


    // -------------------Create a Bill---------------------------------------------
    @PostMapping
    public ResponseEntity<Bill> addBill(@CurrentUser UserPrincipal currentUser, @RequestBody BillRequestDto billRequestDto) {
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


}



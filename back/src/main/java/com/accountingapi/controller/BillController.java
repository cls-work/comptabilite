
package com.accountingapi.controller;
import com.accountingapi.model.Bill;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.model.Historical;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.impl.BillServiceImpl;
import com.accountingapi.service.impl.FileStorageServiceImpl;
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
    public ResponseEntity<Bill> getBillById(@PathVariable("id") Long id) {
        if (billService.existsById(id))
            return new ResponseEntity<Bill>(billService.findBillById(id), HttpStatus.OK);
        else return new ResponseEntity("Bill not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Delete a Bill---------------------------------------------


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
        if (billService.existsById(id)) {
            Bill bill = billService.findBillById(id);
            bill.setDeleted(true);
            billService.updateBill(bill);
            return new ResponseEntity<>("Bill isDeleted setted to true and added to historical", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Quotation with id" + id + " not found", HttpStatus.NOT_FOUND);


    }


    // -------------------Create a Bill---------------------------------------------
    @PostMapping
    public ResponseEntity<Bill> addBill(@CurrentUser UserPrincipal currentUser, @RequestBody Bill bill) {
        billService.addBill(bill);
        return new ResponseEntity<Bill>(bill, HttpStatus.CREATED);

    }


}



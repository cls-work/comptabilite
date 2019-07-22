
package com.accountingapi.controller;
import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.model.Quotation;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.security.service.impl.UserServiceImpl;
import com.accountingapi.service.impl.BillServiceImpl;
import com.accountingapi.service.impl.FileStorageServiceImpl;
import com.accountingapi.service.impl.HistoricalServiceImpl;
import com.accountingapi.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    QuotationServiceImpl quotationService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    HistoricalServiceImpl historicalService;

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
    public ResponseEntity<Bill> findBillById(@PathVariable("id") Long id) {
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
            User currUser = userService.findUserByUsername(currentUser.getUsername());
            historicalService.addHistorical(new Historical("Bill with id " + id + " Deleted", currUser));
            return new ResponseEntity<>("Bill's attribute isDeleted setted to true", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Quotation with id" + id + " not found", HttpStatus.NOT_FOUND);


    }


    // -------------------Create a Bill---------------------------------------------
    @PostMapping
    public ResponseEntity<Bill> addBill(@CurrentUser UserPrincipal currentUser, @RequestBody Bill bill) {
        billService.addBill(bill);
        Quotation quotation = quotationService.findQuotationById(bill.getQuotation().getId());
        quotation.setHasBill(true);
        quotationService.updateQuotation(quotation);
        User currUser = userService.findUserByUsername(currentUser.getUsername());
        historicalService.addHistorical(new Historical("New bill Created", currUser));
        return new ResponseEntity<Bill>(bill, HttpStatus.CREATED);

    }


}



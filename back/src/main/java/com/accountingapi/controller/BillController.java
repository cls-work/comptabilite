package com.accountingapi.controller;


import com.accountingapi.dto.BillDocumentDto;
import com.accountingapi.model.Bill;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.repository.FileStorageRepository;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.BillService;
import com.accountingapi.service.HistoricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    BillService billService;
    @Autowired
    HistoricalService historicalService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileStorageRepository fileStorageRepository;


    /*
        Displaying all bills
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<Bill> displayAllBills(){
        return billService.findAll();
    }


    /*
        Get Bill by its id
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable("id") String id){
        return billService.getBillById(id);
    }


    /*
        Delete bill by its id
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Boolean deleteBill( @CurrentUser UserPrincipal currentUser,@PathVariable String id) {

        Bill bill = billService.getBillById(id);
        bill.setDeleted(true);
        historicalService.addHistorical(currentUser, "deleted a Bill",bill);
        billService.updateBill(bill);
        return bill.getDeleted();
    }

    /*
        add a new Bill
    */

    /*@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public Bill addBill(@CurrentUser UserPrincipal currentUser, @RequestBody Bill bill){

        Bill newBill=billService.addBill(bill);
        historicalService.addHistorical(currentUser, "added a new Bill",bill);
        return newBill;
    }*/

    @PostMapping("")
    public Bill addBill(@CurrentUser UserPrincipal currentUser, @RequestBody BillDocumentDto billDocumentDto){
        List<Long> documentsIds = billDocumentDto.getDocumentIds();
        List<FileStorageProperties> documents= (List<FileStorageProperties>) fileStorageRepository.findAllById(documentsIds);
        billDocumentDto.getBill().setFileStorageProperties(documents);
        documents.forEach(elt->elt.setBill(billDocumentDto.getBill()));
        Bill newBill=billService.addBill(billDocumentDto.getBill());
        historicalService.addHistorical(currentUser, "added a new Bill",billDocumentDto.getBill());
        return newBill;
    }

    /*
        update a bill
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{billId}")
    public Bill editBill(@CurrentUser UserPrincipal currentUser,@PathVariable String billId,@Valid @RequestBody Bill bill){

        bill.setBillId(billId);
        historicalService.addHistorical(currentUser, "updated a Bill",bill);
        return billService.updateBill(bill);
    }


}

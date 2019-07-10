
package com.accountingapi.controller;



import com.accountingapi.dto.BillRequestDto;
import com.accountingapi.model.Bill;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.repository.FileStoragePropertiesRepository;
import com.accountingapi.repository.FileStorageRepository;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.BillService;
import com.accountingapi.service.impl.BillService;
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
    FileStoragePropertiesRepository fileStorageRepository;



    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<Bill> displayAllBills() {
        return billService.findAll();
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable("id") String id) {
        return billService.getBillById(id);
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Boolean deleteBill(@CurrentUser UserPrincipal currentUser, @PathVariable String id) {

        Bill bill = billService.getBillById(id);
        bill.setDeleted(true);
        historicalService.addHistoricalForBill(currentUser, "deleted a Bill", bill);
        billService.updateBill(bill);
        return bill.getDeleted();
    }




    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public Bill addBill(@CurrentUser UserPrincipal currentUser, @RequestBody BillRequestDto billRequestDto) {
        Bill bill = billRequestDto.toBill();


        if (billRequestDto.getDocumentIds() != null) {

            List<Long> documentsIds = billRequestDto.getDocumentIds();
            List<FileStorageProperties> documents = (List<FileStorageProperties>) fileStorageRepository.findAllById(documentsIds);
            bill.setFileStorageProperties(documents);
           // documents.forEach(elt -> elt.setBill(bill));

        }
        Bill newBill = billService.addBill(bill);
        historicalService.addHistoricalForBill(currentUser, "added a new BillId= "+bill.getId(), bill);
        return newBill;


    }




    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{billId}")
    public Bill editBill(@CurrentUser UserPrincipal currentUser, @PathVariable String billId, @Valid @RequestBody BillRequestDto billRequestDto) {

        Bill bill = billRequestDto.toBill();
        bill.setId(billId);

        if (billRequestDto.getDocumentIds() != null) {

            List<Long> documentsIds = billRequestDto.getDocumentIds();
            List<FileStorageProperties> documents = (List<FileStorageProperties>) fileStorageRepository.findAllById(documentsIds);
            bill.setFileStorageProperties(documents);
            //documents.forEach(elt -> elt.setBill(bill));

        }

        Bill newBill = billService.updateBill(bill);
        historicalService.addHistoricalForBill(currentUser, "updated a Bill", bill);
        return newBill;

    }


}
*/

package com.accountingapi.controller;

import com.accountingapi.dto.QuotationRequestDto;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.RoleRepository;
import com.accountingapi.security.service.impl.UserServiceImpl;
import com.accountingapi.service.impl.EmailServiceImpl;
import com.accountingapi.service.impl.FileStorageServiceImpl;
import com.accountingapi.service.impl.PurchaseServiceImpl;
import com.accountingapi.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/quotations")

public class QuotationController {
    @Autowired
    QuotationServiceImpl quotationService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PurchaseServiceImpl purchaseService;

    @Autowired
    FileStorageServiceImpl fileStorageService;

    // -------------------Retrieve All Quotations---------------------------------------------
    @GetMapping
    public ResponseEntity<List<Quotation>> findAllQuotations() {
        List<Quotation> quotations = quotationService.findAllQuotations();
        if (quotations.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quotations, HttpStatus.OK);
    }

    // -------------------Create a Quotation and sending an email to Admin---------------------------------------------
    @PostMapping
    public ResponseEntity<Quotation> addQuotation(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody QuotationRequestDto quotationRequestDto) throws IOException, MessagingException {
        /*User quotationCreator = userService.findUserById((long) 1);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById((long) 1).get());
        User admin = (User) userService.findAllByRoles(roles).get(0);
        //EmailService.createQuotationMail(currentUser,admin);
        //emailService.createQuotationMail(quotationCreator,admin);
        */
        System.out.println("************************");
        Quotation quotation = quotationRequestDto.getQuotation();
        List<Purchase> purchases = quotation.getPurchases();
        for (Purchase purchase : purchases) purchase.setQuotation(quotation);

        if (quotationRequestDto.getDocumentIds() != null) {

            List<Long> documentsIds = quotationRequestDto.getDocumentIds();
            System.out.println("Test");
            List<FileStorageProperties> documents = new ArrayList<>();
            for (Long id : documentsIds) documents.add(fileStorageService.findById(id));
            quotation.setFileStorageProperties(documents);

        }
        quotationService.addQuotation(quotation);
        return new ResponseEntity<Quotation>(quotation, HttpStatus.CREATED);
    }

    // -------------------Retrieve One Quotation By ID---------------------------------------------

    @GetMapping("/{quotationId}")
    public ResponseEntity<Quotation> findQuotationById(@PathVariable("quotationId") Long quotationId) {
        if (quotationService.existsById(quotationId))
            return new ResponseEntity<Quotation>(quotationService.findQuotationById(quotationId), HttpStatus.OK);
        else return new ResponseEntity("Quotation not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Delete a Quotation---------------------------------------------

    @DeleteMapping("/{quotationId}")
    public ResponseEntity<?> deleteQuotationById(@PathVariable("quotationId") Long quotationId) {
        if (quotationService.existsById(quotationId)) {
            Quotation quotation = quotationService.findQuotationById(quotationId);
            if (quotation.getConfirmed() != null) {
                List<Purchase> purchases = quotation.getPurchases();
                //for(Purchase purchase:purchases) purchaseService.deletePurchaseById(purchase.getId());
                quotationService.deleteQuotationById(quotationId);
                System.out.println("Quotation deleted");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>("Quotation not yet confirmed", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity("Quotation with id" + quotationId + " not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Confirm Quotation By ID---------------------------------------------

    @PutMapping("/confirm/{quotationId}")
    public ResponseEntity<?> confirmQuotation(@PathVariable("quotationId") Long quotationId) {
        if (quotationService.existsById(quotationId)) {
            Quotation quotation = quotationService.findQuotationById(quotationId);
            if (quotation.getConfirmed() == null) {
                quotation.setConfirmed(true);
                System.out.println("TEST");
                quotationService.updateQuotation(quotation);
                return new ResponseEntity("Quotation with id " + quotationId + " Confirmed", HttpStatus.OK);
            } else
                return new ResponseEntity("Quotation with id " + quotationId + " already treated", HttpStatus.CONFLICT);
        }
        return new ResponseEntity("Quotation with id " + quotationId + " not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Reject Quotation By ID---------------------------------------------

    @PutMapping("/reject/{quotationId}")
    public ResponseEntity<?> rejectQuotation(@PathVariable("quotationId") Long quotationId) {
        if (quotationService.existsById(quotationId)) {
            Quotation quotation = quotationService.findQuotationById(quotationId);
            if (quotation.getConfirmed() == null) {
                quotation.setConfirmed(false);
                quotationService.updateQuotation(quotation);
                new ResponseEntity("Quotation with id " + quotationId + " Rejected", HttpStatus.OK);
            } else
                return new ResponseEntity("Quotation with id " + quotationId + " already treated", HttpStatus.CONFLICT);
        }
        return new ResponseEntity("Quotation with id " + quotationId + " not found", HttpStatus.NOT_FOUND);
    }
}
package com.accountingapi.controller;

import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.ApiResponse;
import com.accountingapi.security.repository.RoleRepository;
import com.accountingapi.security.service.impl.UserServiceImpl;
import com.accountingapi.service.impl.EmailServiceImpl;
import com.accountingapi.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping
    public List<Quotation> findAllQuotations() {
        return quotationService.findAllQuotations();
    }

    @PostMapping
    public ResponseEntity<Quotation> addQuotation(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody Quotation quotation) throws IOException, MessagingException {
        User quotationCreator = userService.findUserById((long) 1);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById((long) 1).get());
        User admin = (User) userService.findAllByRoles(roles).get(0);
        //EmailService.createQuotationMail(currentUser,admin);
        //emailService.createQuotationMail(quotationCreator,admin);
        quotationService.addQuotation(quotation);
        return new ResponseEntity<Quotation>(quotation, HttpStatus.OK);
    }

    @GetMapping("/{quotationId}")
    public Quotation getQuotationById(@PathVariable("quotationId") Long quotationId) {

        return quotationService.getQuotationById(quotationId);
    }


    @DeleteMapping("/{quotationId}")
    public void deleteQuotationById(@PathVariable("quotationId") Long quotationId) {
        Quotation quotation = quotationService.getQuotationById(quotationId);
        if (quotation.getConfirmed() == null)
            quotationService.deleteQuotationById(quotationId);
        else System.out.println("Unauthorized");
    }


}
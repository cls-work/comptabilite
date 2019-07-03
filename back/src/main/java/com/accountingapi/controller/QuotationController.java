package com.accountingapi.controller;

import com.accountingapi.model.Quotation;
import com.accountingapi.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/quotations")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    @GetMapping("")
    public List<Quotation> displayAllQuotations() {
        return quotationService.findAll();
    }
    @GetMapping("/{id}")
    public Quotation getQuotationById(@PathVariable("id") Long id) {
        return quotationService.getQuotationById(id);
    }
}

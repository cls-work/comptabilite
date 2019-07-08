package com.accountingapi.service;

import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;

import java.util.List;

public interface QuotationService {

    Quotation addQuotation(Quotation quotation);

    List<Quotation> findAllQuotations();

    Quotation getQuotationById(Long quotationId);

    void deleteQuotationById(Long quotationId);
}

package com.accountingapi.dto;

import com.accountingapi.model.Quotation;

import java.util.ArrayList;
import java.util.List;

public class QuotationRequestDto {

    private Quotation quotation;

    private List<Long> documentIds ;

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public List<Long> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<Long> documentIds) {
        this.documentIds = documentIds;
    }
}

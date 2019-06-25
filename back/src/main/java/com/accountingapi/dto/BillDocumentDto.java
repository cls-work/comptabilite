package com.accountingapi.dto;

import com.accountingapi.model.Bill;

import java.util.List;

public class BillDocumentDto {

    private Bill bill ;

    private List<Long> documentIds;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public List<Long> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<Long> documentIds) {
        this.documentIds = documentIds;
    }
}

package com.accountingapi.dto;

import com.accountingapi.model.Bill;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class BillRequestDto {


    private String billId="";

    private String provider="";

    private Date date= new Date(System.currentTimeMillis());

    private Double totalHT = Double.valueOf(0);

    private Double totalTTC = Double.valueOf(0);

    private Double totalTVA = Double.valueOf(0);

    private Double taxStamp=Double.valueOf(0);

    private Double checkReference = Double.valueOf(0);

    private Boolean checkPayment=false;

    private Boolean isDeleted = false;

    private List<Long> documentIds = new ArrayList<>();



    //Methods

    public Bill toBill(){
        Bill bill = new Bill();

        bill.setCheckReference(this.checkReference);
        bill.setCheckPayment(this.checkPayment);
        bill.setDeleted(this.isDeleted);
        return bill;
    }


    //Getters & setters

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Double totalHT) {
        this.totalHT = totalHT;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Double getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(Double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Double getTaxStamp() {
        return taxStamp;
    }

    public void setTaxStamp(Double taxStamp) {
        this.taxStamp = taxStamp;
    }

    public Double getCheckReference() {
        return checkReference;
    }

    public void setCheckReference(Double checkReference) {
        this.checkReference = checkReference;
    }

    public Boolean getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(Boolean checkPayment) {
        this.checkPayment = checkPayment;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Long> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<Long> documentIds) {
        this.documentIds = documentIds;
    }
}
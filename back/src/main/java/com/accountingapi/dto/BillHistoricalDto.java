package com.accountingapi.dto;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;

public class BillHistoricalDto {

    private Bill bill;
    private Historical historical;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Historical getHistorical() {
        return historical;
    }

    public void setHistorical(Historical historical) {
        this.historical = historical;
    }
}

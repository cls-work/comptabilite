package com.accountingapi.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HistoricalKey implements Serializable {

    private Long user_id;

    private Long bill_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBill_id() {
        return bill_id;
    }

    public void setBill_id(Long bill_id) {
        this.bill_id = bill_id;
    }
}

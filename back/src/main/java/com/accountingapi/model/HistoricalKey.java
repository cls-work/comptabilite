package com.accountingapi.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalKey that = (HistoricalKey) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(bill_id, that.bill_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, bill_id);
    }
}

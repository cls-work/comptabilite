package com.accountingapi.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HistoricalKey implements Serializable {

    private Long user_id;

    private Long billId;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalKey that = (HistoricalKey) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(billId, that.billId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, billId);
    }
}

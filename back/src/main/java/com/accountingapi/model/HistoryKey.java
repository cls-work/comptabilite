package com.accountingapi.model;

import com.accountingapi.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HistoryKey implements Serializable {


    @JsonIgnore
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "billId")
    private Bill bill;


    private int id_historical;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public int getId_historical() {
        return id_historical;
    }

    public void setId_historical(int id_historical) {
        this.id_historical = id_historical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryKey that = (HistoryKey) o;
        return id_historical == that.id_historical &&
                Objects.equals(user, that.user) &&
                Objects.equals(bill, that.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, bill, id_historical);
    }
}

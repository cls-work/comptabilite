package com.accountingapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Historical {

    @EmbeddedId
    private HistoricalKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("bill_id")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    private String message;

    @Size(min = 4, max = 255, message = "Minimum note length: 4 characters")
    private String comment;



    //************Getters & Setters************
    public HistoricalKey getId() {
        return id;
    }

    public void setId(HistoricalKey id) {
        this.id = id;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

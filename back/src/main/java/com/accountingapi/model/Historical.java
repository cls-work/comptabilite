package com.accountingapi.model;
import com.accountingapi.security.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Historical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message",unique=false,nullable = true)
    private String message;

    @ManyToOne()
    @JoinColumn(name="BillId")
    private Bill bill;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "date",unique=false,nullable = false)
    private String date;


    //************Getters & Setters************

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historical that = (Historical) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }
}

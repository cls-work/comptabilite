package com.accountingapi.model;
import com.accountingapi.security.model.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Historical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message",unique=false,nullable = true)
    private String message;

    @Column(name = "comment",unique=false,nullable = true)
    @Size(min = 4, max = 255, message = "Minimum note length: 4 characters")
    private String comment;

    @ManyToOne()
    @JoinColumn(name="bill_id", insertable = false, updatable = false)
    private Bill bill;

    @ManyToOne()
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;


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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historical that = (Historical) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, comment);
    }
}

package com.accountingapi.model;

import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Historical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message",unique=false,nullable = true)
    private String message;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "date")
    @CreationTimestamp
    private Date date;

    //************Constructors************

    public Historical(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public Historical(String message, User user, Date date) {
        this.message = message;
        this.user = user;
        this.date = date;
    }

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

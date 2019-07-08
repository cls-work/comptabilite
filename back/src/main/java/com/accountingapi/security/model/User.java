package com.accountingapi.security.model;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.model.Quotation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String lang;

    @Size(max = 40)
    private String name;

    @Size(max = 15)
    private String username;

    //@NaturalId
    @Size(max = 40)
    @Email
    private String email;

    //@NotBlank
    @JsonIgnore
    @Size(max = 100)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(targetEntity= Historical.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Historical> historicals = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Bill> bills;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Quotation> quotationsCreated;

    @JsonIgnore
    @OneToMany(mappedBy = "acceptedBy", cascade = CascadeType.ALL)
    private List<Quotation> quotationsAccepted;


    public User() {

    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Historical> getHistoricals() {
        return historicals;
    }

    public void setHistoricals(List<Historical> historicals) {
        this.historicals = historicals;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Quotation> getQuotationsCreated() {
        return quotationsCreated;
    }

    public void setQuotationsCreated(List<Quotation> quotationsCreated) {
        this.quotationsCreated = quotationsCreated;
    }

    public List<Quotation> getQuotationsAccepted() {
        return quotationsAccepted;
    }

    public void setQuotationsAccepted(List<Quotation> quotationsAccepted) {
        this.quotationsAccepted = quotationsAccepted;
    }
}
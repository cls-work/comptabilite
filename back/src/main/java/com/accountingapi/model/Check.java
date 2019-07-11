package com.accountingapi.model;

import com.accountingapi.model.Bill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="refrence", unique = true, nullable = false)
    private String reference;

    @Column (name="amount", unique = false, nullable = false)
    private double amount;

    @Column(name="profilOf", unique = false, nullable = true)
    private String profilOf;

    @Column(name="transactionDate",unique = false, nullable = false)
    private Date transactionDate;

    @Column(name ="bankAccount",unique = false,nullable = true)
    private Long bankAccount;

    @Column(name = "state", unique = false, nullable = false)
    private String state;

    @OneToOne(mappedBy = "check")
    private Bill bill;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProfilOf() {
        return profilOf;
    }

    public void setProfilOf(String profilOf) {
        this.profilOf = profilOf;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", amount=" + amount +
                ", profilOf='" + profilOf + '\'' +
                ", transactionDate=" + transactionDate +
                ", bankAccount=" + bankAccount +
                ", state='" + state + '\'' +
                '}';
    }
}
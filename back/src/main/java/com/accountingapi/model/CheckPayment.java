package com.accountingapi.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CheckPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref", unique = true)
    private String reference;

    @Column(name = "checkAmount")
    private double amount;

    @Column(name = "valueTo")
    private String profilOf;


    @Column(name = "checkDate")
    private Date checkDate;

    @Column(name = "bankAccountNumber")
    private Long bankAccount;

    @Column(name = "checkState")
    private String checkState;

    @OneToOne(mappedBy = "checkPayment")
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


    public Long getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", amount=" + amount +
                ", profilOf='" + profilOf + '\'' +
                ", checkDate=" + checkDate +
                ", bankAccount=" + bankAccount +
                ", checkState='" + checkState + '\'' +
                ", bill=" + bill +
                '}';
    }
}

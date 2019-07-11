
package com.accountingapi.model;

import com.accountingapi.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Bill {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    @CreationTimestamp
    private Date creationDate;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean isDeleted = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    private Quotation quotation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkPayment_id", referencedColumnName = "id")
    private CheckPayment checkPayment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }


    public CheckPayment getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(CheckPayment checkPayment) {
        this.checkPayment = checkPayment;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", isDeleted=" + isDeleted +
                ", quotation=" + quotation +
                ", checkPayment=" + checkPayment +
                '}';
    }
}

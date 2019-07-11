
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    private Quotation quotation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    private CheckPayment check;


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

    public CheckPayment getCheck() {
        return check;
    }

    public void setCheck(CheckPayment check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", isDeleted=" + isDeleted +
                ", quotation=" + quotation +
                ", check=" + check +
                '}';
    }
}

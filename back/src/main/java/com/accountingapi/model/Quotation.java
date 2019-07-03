package com.accountingapi.model;

import com.accountingapi.security.model.User;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Quotation {

    @Id
    @Column(name = "id",unique=true,nullable = false)
    private Long id;

    @Column(nullable = false)
    private Date creattionDate;

    @Column(nullable = false)
    private Double totalHT= Double.valueOf(0);

    @Column(nullable = false)
    private Double totalTTC=Double.valueOf(0);

    @Column(nullable = false)
    private Double totalTVA=Double.valueOf(0);

    @Column(nullable = false)
    private Double taxStamp;

    @Column(nullable = false)
    private Boolean isConfirmed=false;


    @Column(nullable = false)
    private Boolean isChecked=false;

    @OneToOne(mappedBy = "quotation")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name="createdBy", nullable=false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="acceptedBy", nullable=false)
    private User acceptedBy;

    @ManyToOne
    @JoinColumn(name="provider_id", nullable=false)
    private Provider provider;

    @OneToMany(mappedBy="user")
    private List<Purchase> purchases;




}
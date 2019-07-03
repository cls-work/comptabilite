package com.accountingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Date creationDate;

    @Column(nullable = false)
    private Double checkReference = Double.valueOf(0);

    @Column(nullable = false)
    private Boolean checkPayment;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean isDeleted = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    private Quotation quotation;


    @JsonIgnore
    @OneToMany(targetEntity = Historical.class, mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Historical> historicals = new ArrayList<>();


    @OneToMany(targetEntity = FileStorageProperties.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileStorageProperties> fileStorageProperties = new ArrayList<>();


}

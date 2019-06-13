package com.accounting.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public class Bill {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String provider;

    @Column(unique = true, nullable = false)
    private Date date;

    @Column(unique = true, nullable = false)
    private Long totalHT;

    @Column(nullable = false)
    private Long totalTTC;



}

package com.accountingapi.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String adresse;

    @OneToMany(mappedBy="provider")
    private List<Quotation> quotations;


}

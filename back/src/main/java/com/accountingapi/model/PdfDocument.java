package com.accountingapi.model;


import javax.persistence.*;

@Entity
public class PdfDocument{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documentPath",unique=false,nullable = true)
    private String documentPath;

    @ManyToOne()
    @JoinColumn(name="BillId")
    private Bill bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
}

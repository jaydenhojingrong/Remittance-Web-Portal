package com.OOP.remittancesystem.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "transactions")
@Entity
public class Transactions {

    //Retrieve the transactions by username
    @Id
    @Column(name = "transactionid", nullable = false)
    private String transactionid;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "transactionstatus", nullable = false)
    private String transactionstatus;

    public Transactions(){}

    public String getTransactionId() {
        return transactionid;
    }

    public String getUsername() {
        return username;
    }

    public String getFilename() {
        return filename;
    }

    public String getCompany() {
        return company;
    }

    public String getTransactionStatus() {
        return transactionstatus;
    }
}
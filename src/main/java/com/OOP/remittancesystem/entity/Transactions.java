package com.OOP.remittancesystem.entity;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "transactions")
@Entity
public class Transactions {

    //Retrieve the transactions by username
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public Transactions(String username, String filename, String company, String transactionstatus){
        this.username = username;
        this.filename = filename;
        this.company = company;
        this.transactionstatus = transactionstatus;
    }

    public String getTransactionId() {
        return transactionid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTransactionStatus() {
        return transactionstatus;
    }

    public void setTransactionStatus(String transactionstatus) {
        this.transactionstatus = transactionstatus;
    }
}
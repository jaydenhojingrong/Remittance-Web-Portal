package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class PaymentGo extends Remittance{
    
     //stores only the columns that do not exist in all three companies BUT exist in PaymentGo
    //e.g. does not store sender first name since it exist in all three

    
    @CsvBindByName(column = "sCurrency", required = false)
    private String sCurrency;

    @CsvBindByName(column = "rMobileNumber", required = false)
    private String rMobileNo;

    // @CsvBindByName(column = "amount", required = false)
    // private String amount;

    @CsvBindByName(column = "rIDNumber", required = false)
    private String rIDNumber;

    @CsvBindByName(column = "rIDType", required = false)
    private String rIDType;

    @CsvBindByName(column = "rDOB", required = false)
    private String rDOB;

    @CsvBindByName(column = "rBank", required = false)
    private String rBank;

    @CsvBindByName(column = "rBranch", required = false)
    private String rBranch;

    @CsvBindByName(column = "sAccountNumber", required = false)
    private String sAccountNumber;

    public String getsCurrency() {
        return sCurrency;
    }

    public void setsCurrency(String sCurrency) {
        this.sCurrency = sCurrency;
    }

    public String getrMobileNo() {
        return rMobileNo;
    }

    public void setrMobileNo(String rMobileNo) {
        this.rMobileNo = rMobileNo;
    }

    public String getrIDNumber() {
        return rIDNumber;
    }

    public void setrIDNumber(String rIDNumber) {
        this.rIDNumber = rIDNumber;
    }

    public String getrIDType() {
        return rIDType;
    }

    public void setrIDType(String rIDType) {
        this.rIDType = rIDType;
    }

    public String getrDOB() {
        return rDOB;
    }

    public void setrDOB(String rDOB) {
        this.rDOB = rDOB;
    }

    public String getrBank() {
        return rBank;
    }

    public void setrBank(String rBank) {
        this.rBank = rBank;
    }

    public String getrBranch() {
        return rBranch;
    }

    public void setrBranch(String rBranch) {
        this.rBranch = rBranch;
    }

    public String getsAccountNumber() {
        return sAccountNumber;
    }

    public void setsAccountNumber(String sAccountNumber) {
        this.sAccountNumber = sAccountNumber;
    }

    
}

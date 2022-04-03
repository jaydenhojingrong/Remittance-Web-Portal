package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class Remittance {

    //stores only the columns that are present in all three companies
    //e.g. sender first name, sender country, currency etc...
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowID;

    @CsvBindByName(column = "sCountry", required = false)
    private String sCountry;
    @CsvBindByName(column = "sFirstName", required = false)
    private String sFirstName;
    @CsvBindByName(column = "sLastName", required = false)
    private String sLastName;
    @CsvBindByName(column = "sNationality", required = false)
    private String sNationality;
    @CsvBindByName(column = "sIDType", required = false)
    private String sIDType;
    @CsvBindByName(column = "sIDNumber", required = false)
    private String sIDNumber;
    @CsvBindByName(column = "sAddress", required = false)
    private String sAddress;
    @CsvBindByName(column = "rFirstName", required = false)
    private String rFirstName;
    @CsvBindByName(column = "rLastName", required = false)
    private String rLastName;
    @CsvBindByName(column = "rAccountNumber",required = false)
    private String rAccountNum;
    @CsvBindByName(column = "rCurrency",required = false)
    private String rCurrency;
    @CsvBindByName(column = "sSourceOfFund",required = false)
    private String sSourceOfFund;
    @CsvBindByName(column = "remitPurpose", required = false)
    private String remitPurpose;
    @CsvBindByName(column = "amount", required = true)
    private String amount;

  
    public Remittance(){
    }

    public String getsCountry() {
        return sCountry;
    }

    public void setsCountry(String sCountry) {
        this.sCountry = sCountry;
    }

    public String getsFirstName() {
        return sFirstName;
    }

    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsNationality() {
        return sNationality;
    }

    public void setsNationality(String sNationality) {
        this.sNationality = sNationality;
    }

    public String getsIDType() {
        return sIDType;
    }

    public void setsIDType(String sIDType) {
        this.sIDType = sIDType;
    }

    public String getsIDNumber() {
        return sIDNumber;
    }

    public void setsIDNumber(String sIDNumber) {
        this.sIDNumber = sIDNumber;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getrFirstName() {
        return rFirstName;
    }

    public void setrFirstName(String rFirstName) {
        this.rFirstName = rFirstName;
    }

    public String getrLastName() {
        return rLastName;
    }

    public void setrLastName(String rLastName) {
        this.rLastName = rLastName;
    }

    public String getrAccountNum() {
        return rAccountNum;
    }

    public void setrAccountNum(String rAccountNum) {
        this.rAccountNum = rAccountNum;
    }

    public String getrCurrency() {
        return rCurrency;
    }

    public void setrCurrency(String rCurrency) {
        this.rCurrency = rCurrency;
    }

    public String getsSourceOfFund() {
        return sSourceOfFund;
    }

    public void setsSourceOfFund(String sSourceOfFund) {
        this.sSourceOfFund = sSourceOfFund;
    }

    public String getRemitPurpose() {
        return remitPurpose;
    }

    public void setRemitPurpose(String remitPurpose) {
        this.remitPurpose = remitPurpose;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    

    
    


    
}
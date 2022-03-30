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

    @CsvBindByName(column = "sCountry", required = true)
    private String sCountry;
    @CsvBindByName(column = "sFirstName", required = true)
    private String sFirstName;
    @CsvBindByName(column = "sLastName", required = true)
    private String sLastName;
    @CsvBindByName(column = "sNationality", required = true)
    private String sNationality;
    @CsvBindByName(column = "sIDType", required = true)
    private String sIDType;
    @CsvBindByName(column = "sIDNumber", required = true)
    private String sIDNumber;
    @CsvBindByName(column = "sAddress", required = true)
    private String sAddress;
    @CsvBindByName(column = "rFirstName", required = true)
    private String rFirstName;
    @CsvBindByName(column = "rLastName", required = true)
    private String rLastName;
    @CsvBindByName(column = "rAccountNumber",required = true)
    private String rAccountNum;
    @CsvBindByName(column = "rCurrency",required = true)
    private String rCurrency;
    @CsvBindByName(column = "sSourceOfFund",required = true)
    private String sSourceOfFund;
    @CsvBindByName(column = "remitPurpose", required = true)
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

    
    


    
}
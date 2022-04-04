package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name = "remittancetransaction")
@Entity
public class FinanceNow extends Remittance {
    
    //stores only the columns that do not exist in all three companies BUT exist in FinanceNow
    //e.g. does not store sender first name since it exist in all three


    @CsvBindByName(column = "sDOB", required = false)
    private String sDOB;

    @CsvBindByName(column = "sCity", required = true)
    private String sCity;
    
    @CsvBindByName(column = "rCountry", required = false)
    private String rCountry;

    @CsvBindByName(column = "paymentMode", required = false)
    private String paymentMode;

    @CsvBindByName(column = "rAddress", required = false)
    private String rAddress;

    @CsvBindByName(column = "rCity", required = false)
    private String rCity;

    @CsvBindByName(column = "rIDNumber", required = false)
    private String rIDNumber;

    @CsvBindByName(column = "rIDType", required = false)
    private String rIDType;

    @CsvBindByName(column = "relationship", required = false)
    private String sRelation;

    @CsvBindByName(column = "sState", required = false)
    private String sState;

    @CsvBindByName(column = "rNationality", required = false)
    private String rNationality;


    public String getsDOB() {
        return sDOB;
    }

    public void setsDOB(String sDOB) {
        this.sDOB = sDOB;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public String getrCountry() {
        return rCountry;
    }

    public void setrCountry(String rCountry) {
        this.rCountry = rCountry;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getrCity() {
        return rCity;
    }

    public void setrCity(String rCity) {
        this.rCity = rCity;
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

    public String getsRelation() {
        return sRelation;
    }

    public void setsRelation(String sRelation) {
        this.sRelation = sRelation;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public String getrNationality() {
        return rNationality;
    }

    public void setrNationality(String rNationality) {
        this.rNationality = rNationality;
    }

    
}

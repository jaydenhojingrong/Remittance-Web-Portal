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

    // date type field validation:
    @CsvBindByName(column = "sDOB", required = false)
    private String sDOB;


    // required|regex:/^[A-Za-z0-9 ,.-]+$/"
    @CsvBindByName(column = "sCity", required = true)
    private String sCity;
    
    @Size(min = 3, max = 3, message = "country name must be 3 characters")
    @Pattern(regexp = "01|02|05|99")  
    @CsvBindByName(column = "rCountry", required = false)
    private String rCountry;

    @CsvBindByName(column = "paymentMode", required = false)
    private String paymentMode;

    // @Pattern(regexp = "/^[A-Za-z0-9 ,.-]+$/") 
    @CsvBindByName(column = "rAddress", required = false)
    private String rAddress;

     @Size(min = 1, max = 25, message = "rCity must be between 1 and 25 characters")
    @CsvBindByName(column = "rCity", required = false)
    private String rCity;

    @CsvBindByName(column = "rIDNumber", required = false)
    private String rIDNumber;

    // it must be passport or nationality 
    //TODO check if this is correct
    @Pattern(regexp = "01|02|05|99")  
    @CsvBindByName(column = "rIDType", required = false)
    private String rIDType;

    @CsvBindByName(column = "relationship", required = false)
    private String sRelation;

    @CsvBindByName(column = "sState", required = false)
    private String sState;

    @Size(min = 3, max = 3, message = "rNationality must be between 3 and 3 characters")
    @CsvBindByName(column = "rNationality", required = false)
    private String rNationality;

    // NEW FIELD ADDED: -- will it conflict with sql columns???
    @Size(min = 3, max = 3, message = "Sender Id Country must be between 3 and 3 characters")
    @CsvBindByName(column = "sIdCountry", required = false)
    private String sIdCountry;

    // NEW FIELD ADDED: -- will it conflict with sql columns???
    @Pattern(regexp = "EUR|SGD|USD")  
    @CsvBindByName(column = "sCurrency", required = false)
    private String sCurrency;

    // NEW FIELD ADDED: -- will it conflict with sql columns??? required|in:individual
    @Pattern(regexp = "individual")  
    @CsvBindByName(column = "sSegment", required = true)
    private String sSegment;

    // NEW FIELD ADDED: -- will it conflict with sql columns??? required|in:PHP
    @Pattern(regexp = "PHP")  
    @CsvBindByName(column = "dCurrency", required = true)
    private String dCurrency;

    // NEW FIELD ADDED: -- will it conflict with sql columns??? required|in:individual and field type is number
    @CsvBindByName(column = "sUnits", required = true)
    private Integer sUnits;

    // NEW FIELD ADDED: -- will it conflict with sql columns??? 
    @Pattern(regexp = "bank_account")  
    @CsvBindByName(column = "rType", required = true)
    private String rType;

    // NEW FIELD ADDED: -- will it conflict with sql columns???  whats select type
    @CsvBindByName(column = "rbank", required = true)
    private String rbank;

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


package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name = "remittancetransaction")
@Entity
public class EverywhereRemit extends Remittance{

    //stores only the columns that do not exist in all three companies BUT exist in EverywhereRemit
    //e.g. does not store sender first name since it exist in all three

    @CsvBindByName(column = "sourceType", required = false)
    private String sourceType;

    @CsvBindByName(column = "segment", required = false)
    private String segment;

    @CsvBindByName(column = "sDOB", required = false)
    private String sDOB;

    @CsvBindByName(column = "sCountryID", required = false)
    private String sIDCountry;

    @CsvBindByName(column = "sCurrency", required = false)
    private String sCurrency;

    @CsvBindByName(column = "sCity", required = false)
    private String sCity;

    @CsvBindByName(column = "sAddressCountry", required = false)
    private String sAddressCountry;

    @CsvBindByName(column = "rType", required = false)
    private String rType;

    @CsvBindByName(column = "rCountry", required = false)
    private String rCountry;

    @CsvBindByName(column = "rMobileNumber", required = false)
    private String rMobileNumber;

    public String getSourceType() {
        return sourceType;
    }
    
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getsDOB() {
        return sDOB;
    }

    public void setsDOB(String sDOB) {
        this.sDOB = sDOB;
    }

    public String getsIDCountry() {
        return sIDCountry;
    }

    public void setsIDCountry(String sIDCountry) {
        this.sIDCountry = sIDCountry;
    }

    public String getsCurrency() {
        return sCurrency;
    }

    public void setsCurrency(String sCurrency) {
        this.sCurrency = sCurrency;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public String getsAddressCountry() {
        return sAddressCountry;
    }

    public void setsAddressCountry(String sAddressCountry) {
        this.sAddressCountry = sAddressCountry;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public String getrCountry() {
        return rCountry;
    }

    public void setrCountry(String rCountry) {
        this.rCountry = rCountry;
    }

    public String getrMobileNumber() {
        return rMobileNumber;
    }

    public void setrMobileNumber(String rMobileNumber) {
        this.rMobileNumber = rMobileNumber;
    }

    // @CsvBindByName(column = "amount", required = false)
    // private String amount;
    
}
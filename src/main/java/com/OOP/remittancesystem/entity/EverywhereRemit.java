
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

    @Size(min = 1, max = 30, message = "source_type must be between 1 and 30 characters")
    @CsvBindByName(column = "sourceType", required = false)
    private String sourceType;

    @CsvBindByName(column = "segment", required = false)
    private String segment;

    @Size(min = 1, max = 10, message = "Sender Date of Birth must be between 1 and 10 characters")
    @CsvBindByName(column = "sDOB", required = false)
    private String sDOB;

    @Size(min = 1, max = 20, message = "ID Country of Issue must be between 1 and 20 characters")
    @CsvBindByName(column = "sCountryID", required = false)
    private String sIDCountry;

    @CsvBindByName(column = "sCurrency", required = false)
    private String sCurrency;

    @Size(min = 1, max = 20, message = "Sender city must be between 1 and 20 characters")
    @CsvBindByName(column = "sCity", required = false)
    private String sCity;

    @Size(min = 0, max = 3, message = "Sender Country must be between 0 and 3 characters")
    @CsvBindByName(column = "sAddressCountry", required = false)
    private String sAddressCountry;


    @Size(min = 2, max = 2, message = "recipient_type must be between 2 and 2 characters")
    @CsvBindByName(column = "rType", required = false)
    private String rType;

    @Size(min = 4, max = 50, message = "recipient_country must be between 4 and 50 characters")
    @CsvBindByName(column = "rCountry", required = false)
    private String rCountry;

    @Size(min = 8, max = 15, message = "receiver Mobile Number must be between 8 and 15 characters")
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
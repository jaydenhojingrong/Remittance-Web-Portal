
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

    // NEW FIELD ADDED: -- will it conflict with sql columns???
    @Size(min = 1, max = 25, message = "rAddress must be between 1 and 25 characters")
    @CsvBindByName(column = "rAddress", required = false)
    private String rAddress;

    // NEW FIELD ADDED: -- will it conflict with sql columns???
    @Size(min = 0, max = 50, message = "paymentMode must be between 0 and 50 characters")
    @CsvBindByName(column = "paymentMode", required = false)
    private String paymentMode;

    // NEW FIELD ADDED: -- will it conflict with sql columns???
    @Size(min = 1, max = 50, message = "rCity must be between 1 and 50 characters")
    @CsvBindByName(column = "rCity", required = false)
    private String rCity;

     // NEW FIELD ADDED: -- will it conflict with sql columns???
     @Size(min = 1, max = 30, message = "rIdNumber must be between 1 and 30 characters")
     @CsvBindByName(column = "rIdNumber", required = false)
     private String rIdNumber;

     // NEW FIELD ADDED: -- will it conflict with sql columns???
     @Size(min = 2, max = 2, message = "rIdType must be between 2 and 2 characters")
     @CsvBindByName(column = "rIdType", required = false)
     private String rIdType;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 25, message = "rState must be between 1 and 25 characters")
      @CsvBindByName(column = "rState", required = false)
      private String rState;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 8, message = "rZipCode must be between 1 and 8 characters")
      @CsvBindByName(column = "rZipCode", required = false)
      private String rZipCode;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 3, max = 3, message = "Receiver Nationality must be between 3 and 3 characters")
      @CsvBindByName(column = "rNationality", required = false)
      private String rNationality;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 15, message = "senderBeneficiaryRelationship must be between 1 and 15 characters")
      @CsvBindByName(column = "senderBeneficiaryRelationship", required = false)
      private String senderBeneficiaryRelationship;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 10, message = "sIdExpireDate must be between 1 and 10 characters")
      @CsvBindByName(column = "sIdExpireDate", required = false)
      private String sIdExpireDate;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 10, message = "sIdIssueDate must be between 1 and 10 characters")
      @CsvBindByName(column = "sIdIssueDate", required = false)
      private String sIdIssueDate;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 10, message = "sIdType must be between 1 and 10 characters")
      @CsvBindByName(column = "sIdType", required = false)
      private String sIdType;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 8, max = 15, message = "Sender Mobile Number must be between 8 and 15 characters")
      @CsvBindByName(column = "sMobileNumber", required = false)
      private String sMobileNumber;

      // NEW FIELD ADDED: -- will it conflict with sql columns???
      @Size(min = 1, max = 25, message = "Sender Mobile Number must be between 1 and 25 characters")
      @CsvBindByName(column = "sOccupation", required = false)
      private String sOccupation;

       // NEW FIELD ADDED: -- will it conflict with sql columns???
       @Size(min = 1, max = 20, message = "Sender Mobile Number must be between 1 and 20 characters")
       @CsvBindByName(column = "sState", required = false)
       private String sState;

       // NEW FIELD ADDED: -- will it conflict with sql columns???
       @Size(min = 3, max = 8, message = "Sender Zip Code must be between 3 and 8 characters")
       @CsvBindByName(column = "sZipCode", required = false)
       private String sZipCode;

       


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
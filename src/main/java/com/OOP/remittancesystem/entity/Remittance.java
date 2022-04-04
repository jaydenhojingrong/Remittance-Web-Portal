package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name = "remittancetransaction")
@Entity
public class Remittance {

    //stores only the columns that are present in all three companies
    //e.g. sender first name, sender country, currency etc...
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowID;
    
    // according to FinanceGo only!!!!:
    // @Size(min = 3, max = 3, message = "sCountry must be between 3 and 3 characters")
    @CsvBindByName(column = "sCountry", required = false)
    private String sCountry;

    //according to FinanceGo only: sender first name: required|max:50|regex:/^[A-Za-z0-9 .-]+$/
    // according to EverywhereRemit only!!!!:
    // @Size(min = 1, max = 50, message = "sFirstName must be between 1 and 50 characters")
    @CsvBindByName(column = "sFirstName", required = false)
    private String sFirstName;

    //according to FinanceGo only: sender first name: required|max:50|regex:/^[A-Za-z0-9 .-]+$/
    // according to EverywhereRemit only!!!!:
    // @Size(min = 1, max = 50, message = "sLastName must be between 1 and 50 characters")
    @CsvBindByName(column = "sLastName", required = false)
    private String sLastName;

    // according to EverywhereRemit and FinanceGo!!!!:
    // @Size(min = 3, max = 3, message = "sNationality must be between 3 and 3 characters")
    @CsvBindByName(column = "sNationality", required = false)
    private String sNationality;

    // according to FinanceGo only!!!!:
    // @Pattern(regexp = "national|passport")  
    @CsvBindByName(column = "sIDType", required = false)
    private String sIDType;


    // according to FinanceGo: n supposed to be text
    @CsvBindByName(column = "sIDNumber", required = true)
    private String sIDNumber;

    // according to financego only!!!!: required|regex:/^[A-Za-z0-9 ,.-]+$/
    // @Size(min = 1, max = 25, message = "sAddress must be between 1 and 25 characters")
    @CsvBindByName(column = "sAddress", required = true)
    private String sAddress;

    // according to FinanceGo: required|max:50|regex:/^[A-Za-z0-9 .-]+$/
    // according to EverywhereRemit only!!!!:
    // @Size(min = 1, max = 50, message = "rFirstName must be between 1 and 50 characters")
    @CsvBindByName(column = "rFirstName", required = false)
    private String rFirstName;

    // according to FinanceGo: required|max:50|regex:/^[A-Za-z0-9 .-]+$/ CONFLICTING!!!
    // according to EverywhereRemit only!!!!:
    // @Size(min = 1, max = 20, message = "rLastName must be between 1 and 20 characters")
    @CsvBindByName(column = "rLastName", required = false)
    private String rLastName;

    // according to FinanceNow only: required|max:16|regex:/^[0-9]+$/ --  number type
    // according to EverywhereRemit only!!!!:
    // @Size(min = 1, max = 50, message = "rAccountNumber must be between 1 and 50 characters")
    @CsvBindByName(column = "rAccountNumber",required = true)
    private String rAccountNum;

    @CsvBindByName(column = "rCurrency",required = false)
    private String rCurrency;

    // according to EverywhereRemit only!!!!:
    // @Size(min = 1, max = 30, message = "rAccountNumber must be between 1 and 30 characters")
    @CsvBindByName(column = "sSourceOfFund",required = false)
    private String sSourceOfFund;

    // no remittance purpose requirements for paymentgo:
    // @Size(min = 1, max = 50, message = "remitPurpose must be between 1 and 50 characters")
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
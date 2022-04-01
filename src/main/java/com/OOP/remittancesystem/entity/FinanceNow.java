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

    @CsvBindByName(column = "sDOB", required = true)
    private String sDOB;

    @CsvBindByName(column = "sCity", required = true)
    private String sCity;
    
    // @Size(min = 3, max = 3, message = "country name must be 3 characters")
    @CsvBindByName(column = "rCountry", required = true)
    private String rCountry;

    @CsvBindByName(column = "paymentMode", required = true)
    private String paymentMode;

    // @Pattern(regexp = "/^[A-Za-z0-9 ,.-]+$/") 
    @CsvBindByName(column = "rAddress", required = true)
    private String rAddress;

     @Size(min = 3, max = 3, message = "rNationality must be between 3 and 3 characters")
    @CsvBindByName(column = "rCity", required = true)
    private String rCity;

    @CsvBindByName(column = "rIDNumber", required = true)
    private String rIDNumber;

    // it must be passport or nationality 
    @Pattern(regexp = "passport|national")  
    @CsvBindByName(column = "rIDType", required = true)
    private String rIDType;

    @CsvBindByName(column = "relationship", required = true)
    private String sRelation;

    @CsvBindByName(column = "sState", required = true)
    private String sState;

    @Size(min = 3, max = 3, message = "rNationality must be between 3 and 3 characters")
    @CsvBindByName(column = "rNationality", required = true)
    private String rNationality;
}

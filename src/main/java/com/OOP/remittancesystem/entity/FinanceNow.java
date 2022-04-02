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

    @CsvBindByName(column = "sCity", required = false)
    private String sCity;
    
    // @Size(min = 3, max = 3, message = "country name must be 3 characters")
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
}

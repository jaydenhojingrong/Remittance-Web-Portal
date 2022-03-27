package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class FinanceNow extends Remittance {
    
    //stores only the columns that do not exist in all three companies BUT exist in FinanceNow
    //e.g. does not store sender first name since it exist in all three

    @CsvBindByName(column = "sDOB", required = true)
    private String sDOB;

    @CsvBindByName(column = "sCity", required = true)
    private String sCity;

    @CsvBindByName(column = "rCountry", required = true)
    private String rCountry;

    @CsvBindByName(column = "paymentMode", required = true)
    private String paymentMode;

    @CsvBindByName(column = "rAddress", required = true)
    private String rAddress;

    @CsvBindByName(column = "rCity", required = true)
    private String rCity;

    @CsvBindByName(column = "rIDNumber", required = true)
    private String rIDNumber;

    @CsvBindByName(column = "rIDType", required = true)
    private String rIDType;

    @CsvBindByName(column = "relationship", required = true)
    private String sRelation;

    @CsvBindByName(column = "sState", required = true)
    private String sState;

    @CsvBindByName(column = "rNationality", required = true)
    private String rNationality;
}

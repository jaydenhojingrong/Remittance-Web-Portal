package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class FinanceNow extends Remittance {
    
    //stores only the columns that do not exist in all three companies BUT exist in FinanceNow
    //e.g. does not store sender first name since it exist in all three

    @CsvBindByName(column = "Sender DOB", required = true)
    private String sDOB;

    @CsvBindByName(column = "Sender City", required = true)
    private String sCity;

    @CsvBindByName(column = "Receiver Country", required = true)
    private String rCountry;

    @CsvBindByName(column = "Payment Mode", required = true)
    private String paymentMode;

    @CsvBindByName(column = "Receiver Address", required = true)
    private String rAddress;

    @CsvBindByName(column = "Receiver City", required = true)
    private String rCity;

    @CsvBindByName(column = "Receiver ID Number", required = true)
    private String rIDNumber;

    @CsvBindByName(column = "Receiver ID Type", required = true)
    private String rIDType;

    @CsvBindByName(column = "Sender Relation", required = true)
    private String sRelation;

    @CsvBindByName(column = "Sender State/Province", required = true)
    private String sState;

    @CsvBindByName(column = "Receiver Nationality", required = true)
    private String rNationality;
}

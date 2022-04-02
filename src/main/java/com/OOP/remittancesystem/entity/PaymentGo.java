package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class PaymentGo extends Remittance{
    
     //stores only the columns that do not exist in all three companies BUT exist in PaymentGo
    //e.g. does not store sender first name since it exist in all three

    
    @CsvBindByName(column = "sCurrency", required = false)
    private String sCurrency;

    @CsvBindByName(column = "rMobileNumber", required = false)
    private String rMobileNo;

    // @CsvBindByName(column = "amount", required = false)
    // private String amount;

    @CsvBindByName(column = "rIDNumber", required = false)
    private String rIDNumber;

    @CsvBindByName(column = "rIDType", required = false)
    private String rIDType;

    @CsvBindByName(column = "rDOB", required = false)
    private String rDOB;

    @CsvBindByName(column = "rBank", required = false)
    private String rBank;

    @CsvBindByName(column = "rBranch", required = false)
    private String rBranch;

    @CsvBindByName(column = "sAccountNumber", required = false)
    private String sAccountNumber;

}

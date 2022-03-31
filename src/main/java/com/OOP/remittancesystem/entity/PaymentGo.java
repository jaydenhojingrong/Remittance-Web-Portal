package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class PaymentGo extends Remittance{
    
     //stores only the columns that do not exist in all three companies BUT exist in PaymentGo
    //e.g. does not store sender first name since it exist in all three

    
    @CsvBindByName(column = "sCurrency", required = true)
    private String sCurrency;

    @CsvBindByName(column = "rMobileNumber", required = true)
    private String rMobileNo;

    @CsvBindByName(column = "amount", required = true)
    private String amount;

    @CsvBindByName(column = "rIDNumber", required = true)
    private String rIDNumber;

    @CsvBindByName(column = "rIDType", required = true)
    private String rIDType;

    @CsvBindByName(column = "rDOB", required = true)
    private String rDOB;

    @CsvBindByName(column = "rBank", required = true)
    private String rBank;

    @CsvBindByName(column = "rBranch", required = true)
    private String rBranch;

    @CsvBindByName(column = "sAccountNumber", required = true)
    private String sAccountNumber;

}

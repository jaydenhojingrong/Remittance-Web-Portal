package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class PaymentGo extends Remittance{
    
     //stores only the columns that do not exist in all three companies BUT exist in PaymentGo
    //e.g. does not store sender first name since it exist in all three

    @CsvBindByName(column = "Beneficiary Send Currency", required = true)
    private String sCurrency;

    @CsvBindByName(column = "Beneficiary Mobile Number", required = true)
    private String rMobileNo;

    @CsvBindByName(column = "Destination Amount", required = true)
    private String amount;

    @CsvBindByName(column = "Beneficiary ID Number", required = true)
    private String rIDNumber;

    @CsvBindByName(column = "Beneficiary ID Type", required = true)
    private String rIDType;

    @CsvBindByName(column = "Beneficiary Date of Birth", required = true)
    private String rDOB;

    @CsvBindByName(column = "Beneficiary Bank", required = true)
    private String rBank;

    @CsvBindByName(column = "Beneficiary Branch", required = true)
    private String rBranch;

    @CsvBindByName(column = "Sender Account Number", required = true)
    private String sAccountNumber;

}

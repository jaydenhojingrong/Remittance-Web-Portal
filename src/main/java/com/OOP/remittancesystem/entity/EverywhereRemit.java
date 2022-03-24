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

    @CsvBindByName(column = "source_type", required = true) 
    private String sourceType;
    @CsvBindByName(column = "segment", required = true)
    private String segment;
    @CsvBindByName(column = "Date of Birth", required = true)
    private String sDOB;
    @CsvBindByName(column = "ID Country of Issue", required = true)
    private String sIDCountry;
    @CsvBindByName(column = "sender_currency", required = true)
    private String sCurrency;
    @CsvBindByName(column = "Address City", required = true)
    private String sCity;
    @CsvBindByName(column = "Country of Address", required = true)
    private String sCountryAddress;
    @CsvBindByName(column = "recipient_type", required = true)
    private String rType;

    @Size(min = 4, max = 50, message = "recipient_country must be between 10 and 200 characters")
    @CsvBindByName(column = "recipient_country", required = true)
    private String rCountry;
    @CsvBindByName(column = "Mobile Number", required = true)
    private String rMobile;
    @CsvBindByName(column = "Amount", required = true)
    private String amount;

}





// ReceiverCountry -- > recipient_country
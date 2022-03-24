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

    @Size(min = 1, max = 10, message = "Date of Birth must be between 1 and 10 characters")
    @CsvBindByName(column = "Date of Birth", required = true)
    private String sDOB;
    @CsvBindByName(column = "ID Country of Issue", required = true)
    private String sIDCountry;
    @CsvBindByName(column = "sender_currency", required = true)
    private String sCurrency;
    @CsvBindByName(column = "Address City", required = true)
    private String sCity;

    @Size(min = 0, max = 50, message = "Country of Address must be between 0 and 50 characters")
    @CsvBindByName(column = "Country of Address", required = true)
    private String sCountryAddress;


    @Size(min = 2, max = 2, message = "recipient_type must be between 2 and 2 characters")
    @CsvBindByName(column = "recipient_type", required = true)
    private String rType;

    @Size(min = 4, max = 50, message = "recipient_country must be between 10 and 200 characters")
    @CsvBindByName(column = "recipient_country", required = true)
    private String rCountry;

    @Size(min = 8, max = 15, message = "Mobile Number must be between 8 and 15 characters")
    @CsvBindByName(column = "Mobile Number", required = true)
    private String rMobile;


    @CsvBindByName(column = "Amount", required = true)
    private String amount;

}





// ReceiverCountry -- > recipient_country
// ReceiverContactNumber --> Mobile Number
// SenderDateOfBirth --> Date of Birth
// ReceiverIdType --> recipient_type
// ReceiverCountry ---> Country of Address
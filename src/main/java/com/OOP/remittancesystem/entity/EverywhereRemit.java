
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

    @Size(min = 1, max = 30, message = "source_type must be between 1 and 10 characters")
    @CsvBindByName(column = "sourceType", required = false)
    private String sourceType;

    @CsvBindByName(column = "segment", required = false)
    private String segment;

    @Size(min = 1, max = 10, message = "Date of Birth must be between 1 and 10 characters")
    @CsvBindByName(column = "sDOB", required = false)
    private String sDOB;

    @Size(min = 1, max = 20, message = "ID Country of Issue must be between 1 and 10 characters")
    @CsvBindByName(column = "sCountryID", required = false)
    private String sIDCountry;

    @CsvBindByName(column = "sCurrency", required = false)
    private String sCurrency;

    @Size(min = 1, max = 50, message = "Address city must be between 1 and 50 characters")
    @CsvBindByName(column = "sCity", required = false)
    private String sCity;

    @Size(min = 0, max = 50, message = "Country of Address must be between 0 and 50 characters")
    @CsvBindByName(column = "sAddressCountry", required = false)
    private String sAddressCountry;


    @Size(min = 2, max = 2, message = "recipient_type must be between 2 and 2 characters")
    @CsvBindByName(column = "rType", required = false)
    private String rType;

    @Size(min = 4, max = 50, message = "recipient_country must be between 10 and 200 characters")
    @CsvBindByName(column = "rCountry", required = false)
    private String rCountry;

    @Size(min = 8, max = 15, message = "Mobile Number must be between 8 and 15 characters")
    @CsvBindByName(column = "rMobileNumber", required = false)
    private String rMobileNumber;

    // @CsvBindByName(column = "amount", required = false)
    // private String amount;

}
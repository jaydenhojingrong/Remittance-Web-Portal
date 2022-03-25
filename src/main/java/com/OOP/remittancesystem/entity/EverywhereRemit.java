
package com.OOP.remittancesystem.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "remittancetransaction")
@Entity
public class EverywhereRemit extends Remittance{

    //stores only the columns that do not exist in all three companies BUT exist in EverywhereRemit
    //e.g. does not store sender first name since it exist in all three
    // private String company = "EverywhereRemit";

    @CsvBindByName(column = "sourceType", required = true)
    private String sourceType;
    @CsvBindByName(column = "segment", required = true)
    private String segment;
    @CsvBindByName(column = "sDOB", required = true)
    private String sDOB;
    @CsvBindByName(column = "sCountryID", required = true)
    private String sIDCountry;
    @CsvBindByName(column = "sCurrency", required = true)
    private String sCurrency;
    @CsvBindByName(column = "sCity", required = true)
    private String sCity;
    @CsvBindByName(column = "sAddressCountry", required = true)
    private String sAddressCountry;
    @CsvBindByName(column = "rType", required = true)
    private String rType;
    @CsvBindByName(column = "rCountry", required = true)
    private String rCountry;
    @CsvBindByName(column = "rMobileNumber", required = true)
    private String rMobileNumber;
    @CsvBindByName(column = "amount", required = true)
    private String amount;

}

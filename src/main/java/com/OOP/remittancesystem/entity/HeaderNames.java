package com.OOP.remittancesystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@IdClass(headerNamesKey.class)

@Table(name = "headernames")
public class HeaderNames {
    @Id
    @Column(name = "currentHeader", nullable = false)
    private String currentHeader;

    @Id
    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "ssotHeader", nullable = false)
    private String ssotHeader;
    
    @Column(name = "apiHeader", nullable = false)
    private String apiHeader;

    @Column(name = "size", nullable = false)
    private String size;
    
    @Column(name = "regex", nullable = false)
    private String regex;

    public HeaderNames(){

    }
    
    public HeaderNames(String currentHeader, String ssotHeader, String company, String apiHeader, String size, String regex){
        this.currentHeader = currentHeader;
        this.ssotHeader = ssotHeader;
        this.company = company;
        this.apiHeader = apiHeader;
        this.size = size;
        this.regex = regex;
    }

    public String getCurrentHeader() {
        return currentHeader;
    }

    public void setCurrentHeader(String currentHeader) {
        this.currentHeader = currentHeader;
    }

    public String getSsotHeader() {
        return ssotHeader;
    }

    public void setSsotHeader(String ssotHeader) {
        this.ssotHeader = ssotHeader;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getApiHeader() {
        return apiHeader;
    }

    public void setApiHeader(String apiHeader) {
        this.apiHeader = apiHeader;
    }
    
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String toString(){
        return (this.currentHeader + " " + this.ssotHeader + " " + this.company);
    }

}

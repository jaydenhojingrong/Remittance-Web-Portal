package com.OOP.remittancesystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "headernames")
public class HeaderNames {
    @Id
    @Column(name = "currentHeader", nullable = false)
    private String currentHeader;

    @Column(name = "ssotHeader", nullable = false)
    private String ssotHeader;
    
    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "apiHeader", nullable = false)
    private String apiHeader;

    public HeaderNames(){

    }
    
    public HeaderNames(String currentHeader, String ssotHeader, String company, String apiHeader){
        this.currentHeader = currentHeader;
        this.ssotHeader = ssotHeader;
        this.company = company;
        this.apiHeader = apiHeader;
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

    public String toString(){
        return (this.currentHeader + " " + this.ssotHeader + " " + this.company);
    }

}

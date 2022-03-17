package com.OOP.studentsystem.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currentHeader", nullable = false)
    private String currentHeader;

    @Column(name = "ssotHeader", nullable = false)
    private String ssotHeader;

    @Column(name = "company", nullable = true)
    private String company;

    public HeaderNames(){
        
    }
    public HeaderNames(String currentHeader, String ssotHeader, String company){
        super();
        this.currentHeader = currentHeader;
        this.ssotHeader = ssotHeader;
        this.company = company;
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

    

}

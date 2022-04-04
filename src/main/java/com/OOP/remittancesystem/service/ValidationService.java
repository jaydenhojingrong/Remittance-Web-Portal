package com.OOP.remittancesystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

import com.OOP.remittancesystem.entity.HeaderNames;
import com.OOP.remittancesystem.service.HeaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

	@Autowired
	private HeaderService headerService;
	

    private boolean spoil = false;
    private ArrayList<String> whatSpoilList = new ArrayList<String>();

    public String getSsotHeader(String company,String columnName){
        
        HeaderNames ssotHeaderEntity = headerService.getSsotByCurrentHeaderAndCompany(columnName,  company);
        String ssotHeader = ssotHeaderEntity.getSsotHeader();
        return ssotHeader;
    }

    public String getSize(String ssotHeader, String company){
        String sizeReq = headerService.getSizeByApiHeaderAndCompany(ssotHeader,  company);
        return sizeReq;
        // return "0|20";
    }

    public String getValidation(String ssotHeader, String company ){
        String sizeReq = headerService.getSizeByApiHeaderAndCompany(ssotHeader,  company);
        return sizeReq;
    }

    public boolean sizeValidation(String value, String ssotHeader, String company){

        String size = getSize(ssotHeader, company);

        if (size == null){
            return true;
        }

        String[] sizeArray = size.split("\\|");

        int min = Integer.parseInt(sizeArray[0]);

        int max = Integer.parseInt(sizeArray[1]);

        
        System.out.println(value);
        if (value.length() >= min & value.length() <= max){
            System.out.println(max);
            return true;
        } else {
            setSpoil();
            setWhatSpoil(ssotHeader);
            return false;
        }
    }
    
    public boolean regexValidation(String value, String ssotHeader, String company)  {

        String validation = getValidation(ssotHeader, company);
        
        if (validation == null){
            return true;
        }
        if (Pattern.matches(validation, value)){
            System.out.println(value);
            return true;
        } else {
            setSpoil();
            setWhatSpoil(ssotHeader);
            return false;
        }
    }

    public void setSpoil(){
        this.spoil = true;
    }
    
    public boolean getSpoil(){
        return this.spoil;
    }

    public void setWhatSpoil(String msg){
        this.whatSpoilList.add(msg);
    }

    public ArrayList<String> getWhatSpoil(){
        return this.whatSpoilList;
    }

}

package com.OOP.remittancesystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;  

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private boolean spoil = false;
    private ArrayList<String> whatSpoilList = new ArrayList<String>();

    public String getSsotHeader(String company,String columnName){
        // get SSOT header
        String ssotHeader = "sFirstName";
        return columnName;
    }

    public String getSize(String ssotHeader){
        // get size validation
        return "0|20";
    }

    public String getValidation(String ssotHeader ){
        // get regex
        return "^[A-Za-z0-9 .-]+$";
    }

    public boolean sizeValidation(String value, String ssotHeader){

        String size = getSize(ssotHeader);

        if (size == null){
            return true;
        }

        Scanner s = new Scanner(size);
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
    
    public boolean regexValidation(String value, String ssotHeader)  {

        String validation = getValidation(ssotHeader);
        
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

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
	
    // default value of spoil  
    private boolean spoil = false;
    // stores value of spoiled columns this will be returned as a JSON object under the "spoil" header
    private ArrayList<String> whatSpoilList = new ArrayList<String>();

    // calls headerservice get size criteria saved in DB
    public String getSize(String apiHeader, String company){
        
        String sizeReq;

        try{
            sizeReq = headerService.getSizeByApiHeaderAndCompany(apiHeader,  company);
        } catch (Exception e){
            return null;
        }
        return sizeReq;
    }

    // calls headerservice get regex criteria saved in DB
    public String getValidation(String apiHeader, String company ){

        String validationReq;

        try{
            validationReq = headerService.getRegexByApiHeaderAndCompany(apiHeader,  company);
        } catch (Exception e){
            return null;
        }

        return validationReq;
    }

    // executes the actual size validation by using the split method to get minimum and maximum values
    public boolean sizeValidation(String value, String apiHeader, String company){

        String size = getSize(apiHeader, company);

        if (size == null){
            return true;
        }

        if (size == ""){
            return true;
        }

        String[] sizeArray = size.split("\\|");
        int min;
        int max;

        try{
            min = Integer.parseInt(sizeArray[0]);

            max = Integer.parseInt(sizeArray[1]);
        } catch (Exception e) {
            return true;
        }

        if (value.length() >= min & value.length() <= max){
            return true;
        } else {
            setSpoil();
            setWhatSpoil(apiHeader);
            return false;
        }
    }
    
    // executes the actual regex validation using java pattern matches
    public boolean regexValidation(String value, String apiHeader, String company)  {

        String validation = getValidation(apiHeader, company);
        
        if (validation.equals("\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}") ){
            validation = "\\d{4}-\\d{1,2}-\\d{1,2}";
        }

        if (validation == null || validation.isEmpty()){
            return true;
        }

        if (Pattern.matches(validation, value)){
            return true;
        } else {
            setSpoil();
            setWhatSpoil(apiHeader);
            return false;
        }
    }

    // set spoil is used to set the default boolean value "spoil" value to true as long as there is ONE error in either regex or size 
    public void setSpoil(){
        this.spoil = true;
    }
    
    public boolean getSpoil(){
        return this.spoil;
    }

    // add the spoilt column values into the spoilt list  
    public void setWhatSpoil(String msg){
        this.whatSpoilList.add(msg);
    }

    // get the entire spoiled column values to return as a JSON value
    public ArrayList<String> getWhatSpoil(){
        return this.whatSpoilList;
    }

    // clear cache of object
    public void clearWhatSpoil(){
        this.whatSpoilList = new ArrayList<String>();
    }

    // reset default spoil boolean back to false
    public void resetSpoil(){
        this.spoil = false; 
    }
}

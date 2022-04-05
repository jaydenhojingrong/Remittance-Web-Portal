package com.OOP.remittancesystem.service;

import java.util.ArrayList;
import java.util.regex.*;

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

        // catch nullpoint exception
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

         // catch nullpoint exception
        try{
            validationReq = headerService.getRegexByApiHeaderAndCompany(apiHeader,  company);
        } catch (Exception e){
            return null;
        }

        return validationReq;
    }

    // executes the actual size validation by using the split method to get minimum and maximum values
    public void sizeValidation(String value, String apiHeader, String company){

        String size = getSize(apiHeader, company);

         // catching null to return out of method 
        if (size == null){
            return;
        }

        // catching empty string to return out of method
        if (size == ""){
            return;
        }

        // custom split for string in our DB for storing size EG [(minimum) | (maximum)]
        String[] sizeArray = size.split("\\|");
        int min = 0;
        int max = Integer.MAX_VALUE;

        try{
            min = Integer.parseInt(sizeArray[0]);

            max = Integer.parseInt(sizeArray[1]);
        } catch (Exception e) {
            return;
        }

        if (value.length() >= min & value.length() <= max){
            return;
        } else {
            setSpoil();
            setWhatSpoil(apiHeader);
            return;
        }
    }
    
    // executes the actual regex validation using java pattern matches
    public void regexValidation(String value, String apiHeader, String company)  {

        String validation = getValidation(apiHeader, company);
        
        // catch outlier regex due to the slashes not working normally with method
        if (validation.equals("\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}") ){
            validation = "\\d{4}-\\d{1,2}-\\d{1,2}";
        }

        if (validation == null || validation.isEmpty()){
            return;
        }

        if (Pattern.matches(validation, value)){
            return;
        } else {
            setSpoil();
            setWhatSpoil(apiHeader);
            return;
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

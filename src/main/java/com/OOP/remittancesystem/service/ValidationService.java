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

        // System.out.println("CURRENT COMP"+ company);
        String validationReq;

        try{
            validationReq = headerService.getRegexByApiHeaderAndCompany(apiHeader,  company);
            // System.out.println("HERE"+ validationReq + "    "+ company);
        } catch (Exception e){
            return null;
        }

        return validationReq;
    }

    // executes the actual size validation 
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
    
    public boolean regexValidation(String value, String apiHeader, String company)  {

        String validation = getValidation(apiHeader, company);
        
        if (validation.equals("\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}") ){
            validation = "\\d{4}-\\d{1,2}-\\d{1,2}";
        }

        if (validation == null || validation.isEmpty()){
            return true;
        }
        if (Pattern.matches(validation, value)){
            // System.out.println(value);
            return true;
        } else {
            System.out.println(company);
            System.out.println("real validation");
            System.out.println(Pattern.matches(validation,value));
            System.out.println(validation);
            System.out.println(value);
            System.out.println("fuck");
            setSpoil();
            setWhatSpoil(apiHeader);
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

    // todo clear cache of object
    public void clearWhatSpoil(){
        this.whatSpoilList = new ArrayList<String>();
    }

}

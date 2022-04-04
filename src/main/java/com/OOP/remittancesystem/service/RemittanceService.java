package com.OOP.remittancesystem.service;

import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.Remittance;
import com.OOP.remittancesystem.dao.RemittanceDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@Service
public class RemittanceService {
    
    @Autowired
    private RemittanceDAO remittanceDAO;
    
    public List<Remittance> getAllRemittance() {
        return remittanceDAO.findAll();
    }

    //     @GetMapping("/convert")
    public String toJSON(Map<String,List<String>> map) {
        String body = "{";
        int i = 0;
        for(Map.Entry<String,List<String>> e : map.entrySet()) {
            if (!e.getKey().equals("null")){
                body += "\"" + e.getKey() + "\"" + ": [ " ;
                int j = 0;
                for (String val : e.getValue()) {
                    System.out.println(val);
                    if (j < e.getValue().size() - 1){
                        body += "\"" + val + "\"" + ",";
                    }else {body += "\"" + val + "\"";}
                    j++;                    
                }
                body += "]";
                if (i < map.size() - 1){
                    body += ",";
                }
            }
            i++;
        }
        body += "}";
        return body;
    }
}




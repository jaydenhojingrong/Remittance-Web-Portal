package com.OOP.remittancesystem.service;

import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.Remittance;
import com.OOP.remittancesystem.dao.RemittanceDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// import org.hibernate.mapping.Set;
import org.json.*;
import java.util.ArrayList;

@Service
public class RemittanceService {

    @Autowired
    private RemittanceDAO remittanceDAO;

    public List<Remittance> getAllRemittance() {
        return remittanceDAO.findAll();
    }

    // @GetMapping("/convert")
    public ArrayList<String> toJSON(Map<String, List<String>> map) throws JSONException {
        ArrayList<String> apiAndPayloads = new ArrayList<String>();
        Iterator<String> iterator = map.keySet().iterator();

        String key = null;
        int numloops = 0;
        while (iterator.hasNext()) {
            key = iterator.next();
            if (key.equals("null")) {
                iterator.remove();
            }
            
        }
        numloops = map.get(key).size();
        String body = "{";
        for (int j = 0; j < numloops; j++) {
            body = "{";
            int i = 0;
            for (Map.Entry<String, List<String>> e : map.entrySet()) {
                body += "\"" + e.getKey() + "\": ";
                if (i < map.entrySet().size() - 1) {
               
                    body += "\"" + e.getValue().get(j) + "\", ";
                } else {
                    body += "\"" + e.getValue().get(j) + "\"";
                }
                i++;
            }

            body += "}";
            
            apiAndPayloads.add(body);
        }
        return apiAndPayloads;
    }
}
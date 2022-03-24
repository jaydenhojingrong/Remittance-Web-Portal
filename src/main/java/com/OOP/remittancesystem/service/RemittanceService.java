package com.OOP.remittancesystem.service;

import com.OOP.remittancesystem.dao.RemittanceDAO;

import org.springframework.stereotype.Service;
import com.OOP.remittancesystem.entity.EverywhereRemit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RemittanceService {
    
    @Autowired
    private RemittanceDAO remittanceDAO;

    public List<EverywhereRemit> getAllRemittance() {
        return remittanceDAO.findAll();
    }
}




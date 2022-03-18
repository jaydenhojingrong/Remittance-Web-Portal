package com.OOP.remittancesystem.fileHandling;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OOP.remittancesystem.entity.EverywhereRemit;
import java.util.List;

@Service
public class RemittanceServiceImplementation implements RemittanceService {

    @Autowired
    private RemittanceRepository remittanceRepository;


    // overwriting the interface
    @Override
    public EverywhereRemit saveRemittance(EverywhereRemit remittance) {
        return remittanceRepository.save(remittance);
    }

    @Override
    public List<EverywhereRemit> getAllRemittance() {
        return remittanceRepository.findAll();
    }
}

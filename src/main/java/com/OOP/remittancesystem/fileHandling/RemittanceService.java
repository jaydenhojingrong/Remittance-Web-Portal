package com.OOP.remittancesystem.fileHandling;


import java.util.List;

import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.Remittance;


public interface RemittanceService {
    public EverywhereRemit saveRemittance(EverywhereRemit remittance);

    public List<EverywhereRemit> getAllRemittance();

}

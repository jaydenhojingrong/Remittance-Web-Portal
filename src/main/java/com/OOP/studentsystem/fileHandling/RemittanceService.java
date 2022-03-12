package com.OOP.studentsystem.fileHandling;


import com.OOP.studentsystem.fileHandling.Remittance;

import java.util.List;


public interface RemittanceService {
    public EverywhereRemit saveRemittance(EverywhereRemit remittance);

    public List<EverywhereRemit> getAllRemittance();

}

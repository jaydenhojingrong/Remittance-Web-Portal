package com.OOP.studentsystem.fileHandling;

import com.OOP.studentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

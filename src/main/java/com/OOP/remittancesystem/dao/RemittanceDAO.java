package com.OOP.remittancesystem.dao;

import java.util.List;

import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.Remittance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemittanceDAO extends JpaRepository<Remittance, Integer>{
    //public EverywhereRemit saveRemittance(EverywhereRemit remittance);
    //need to change
    public Remittance save(Remittance remittance);
    public List<Remittance> findAll();
}   
// public interface RemittanceDAO extends JpaRepository<EverywhereRemit, Integer>{
//     //public EverywhereRemit saveRemittance(EverywhereRemit remittance);

//     public EverywhereRemit save(EverywhereRemit remittance);
//     public List<EverywhereRemit> findAll();
// }   





package com.OOP.remittancesystem.dao;

import java.util.List;

import com.OOP.remittancesystem.entity.Remittance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemittanceDAO extends JpaRepository<Remittance, Integer>{
    public Remittance save(Remittance remittance);
    public List<Remittance> findAll();
}   






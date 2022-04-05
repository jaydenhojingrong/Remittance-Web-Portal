package com.OOP.remittancesystem.dao;

import java.util.List;

import com.OOP.remittancesystem.entity.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transactions, Integer> {
    public List<Transactions> findByUsername(String username);
    
    public Transactions save(Transactions transactions);
}

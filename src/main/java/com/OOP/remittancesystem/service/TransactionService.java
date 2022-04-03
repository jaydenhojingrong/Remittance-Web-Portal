package com.OOP.remittancesystem.service;

import com.OOP.remittancesystem.dao.TransactionDAO;
import com.OOP.remittancesystem.entity.Transactions;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import javax.transaction.Transaction;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionDAO transactionDAO;
    
    public List<Transactions> getTransactionsByUsername(String username) {
        return transactionDAO.findByUsername(username);
    }
}
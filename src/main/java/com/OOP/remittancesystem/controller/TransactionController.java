package com.OOP.remittancesystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.OOP.remittancesystem.dao.TransactionDAO;
import com.OOP.remittancesystem.entity.Transactions;
import com.OOP.remittancesystem.service.TransactionService;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

	@Autowired
    private TransactionDAO transactionDAO;

    // handler method to handle list headers and return mode and view
	/**
	 * Get all transactions
	 * @return List of transactions
	 */
	@GetMapping("/transactions/{username}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List <Transactions> getAllTransactions(@PathVariable String username) {
		return transactionService.getTransactionsByUsername(username);
	}
}

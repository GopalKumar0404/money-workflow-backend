package com.gopal.MoneyWorkflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.MoneyWorkflow.Services.TransactionDetailService;
import com.gopal.MoneyWorkflow.entities.TransactionDetail;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

	@Autowired
	private TransactionDetailService transactionService;
	
	@GetMapping("/")
	public List<TransactionDetail> getAllTransactions() {
		
		return this.transactionService.getAllTransactionDetail();
	}
	
	@GetMapping("/user/{userId}")
	public List<TransactionDetail> getAllTransactionOfUser(@PathVariable Long userId) {
		
		return this.transactionService.getAllTransactionDetailOfUser(userId);
	}
	
	@GetMapping("/{transactionId}")
	public TransactionDetail getAllTransactionById(@PathVariable Long transactionId) {
		
		return this.transactionService.getTransactionById(transactionId);
	}
}

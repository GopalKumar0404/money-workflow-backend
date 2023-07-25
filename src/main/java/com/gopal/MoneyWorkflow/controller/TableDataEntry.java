package com.gopal.MoneyWorkflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.MoneyWorkflow.Services.TransactionDetailService;
import com.gopal.MoneyWorkflow.Services.UserService;
import com.gopal.MoneyWorkflow.entities.TransactionDetail;
import com.gopal.MoneyWorkflow.entities.User;

@RestController
@RequestMapping("/tableData")
public class TableDataEntry {

	@Autowired
	UserService userService;
	
	@Autowired
	TransactionDetailService transactionService;
	
	@PostMapping("/user")
	public String createEntryInUser(@RequestBody User[] user) {
		
		System.out.println("hi");
		userService.createEntryOfUser(user);
		
		return "All data inserted into the table";
	}
	
	@PostMapping("/transaction")
	public String createEntryInTransaction(@RequestBody TransactionDetail[] transaction) {
		
		System.out.println("hi");
		transactionService.createEntryOfTransaction(transaction);
		
		return "All data inserted into the table";
	}
}

package com.gopal.MoneyWorkflow.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.MoneyWorkflow.Services.TransactionDetailService;
import com.gopal.MoneyWorkflow.entities.TransactionDetail;
import com.gopal.MoneyWorkflow.utility.AppConstants;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

	@Autowired
	private TransactionDetailService transactionService;

	@GetMapping("/")
	public List<TransactionDetail> getAllTransactions(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.TRANSACTION_SORT, required = false) String sortBy,
			@RequestParam(value = "sortByDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortByDirection) {

		return this.transactionService.getAllTransactionDetail(pageNumber, pageSize, sortBy, sortByDirection);
	}

	@GetMapping("/user/{userId}")
	public List<TransactionDetail> getAllTransactionOfUser(@PathVariable Long userId) {

		return this.transactionService.getAllTransactionDetailOfUser(userId);
	}

	@GetMapping("/user/{userId}/download")
	public ResponseEntity<byte[]> getExcelById(@PathVariable Long userId) throws Exception {

		byte[] outputStream = null;

		outputStream = this.transactionService.createExcelwithUserId(userId);

		// Prepare the response with the Excel content
		HttpHeaders headers1 = new HttpHeaders();
		headers1.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers1.setContentDispositionFormData("attachment", "data.xlsx");

		return new ResponseEntity<byte[]>(outputStream, headers1, 201);
	}

	@GetMapping("/{transactionId}")
	public TransactionDetail getAllTransactionById(@PathVariable Long transactionId) {

		return this.transactionService.getTransactionById(transactionId);
	}
}

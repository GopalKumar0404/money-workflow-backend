package com.gopal.MoneyWorkflow.Services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopal.MoneyWorkflow.Exceptions.RequestBodyNotSupported;
import com.gopal.MoneyWorkflow.Exceptions.ResourceNotFoundException;
import com.gopal.MoneyWorkflow.Repositories.TransactionDetailRepo;
import com.gopal.MoneyWorkflow.Repositories.UserRepo;
import com.gopal.MoneyWorkflow.Services.TransactionDetailService;
import com.gopal.MoneyWorkflow.entities.TransactionDetail;
import com.gopal.MoneyWorkflow.entities.User;

@Service
public class TransactionDetailServiceImplementation implements TransactionDetailService {

	@Autowired
	private TransactionDetailRepo transactionRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public TransactionDetail createTransactionDetail(TransactionDetail transactionDetail) {
		// TODO Auto-generated method stub
		if(transactionDetail.getTransactionId()!=null) {
			throw new RequestBodyNotSupported("Request Body with Id not supported.");
		}
		return this.transactionRepo.save(transactionDetail);
	}

	@Override
	public TransactionDetail getTransactionById(Long transactionId) {
		// TODO Auto-generated method stub
		return this.transactionRepo.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transactin", "transactionId", transactionId));
	}

	@Override
	public List<TransactionDetail> getAllTransactionDetailOfUser(Long userId) {
		// TODO Auto-generated method stub

		User tempUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		return this.transactionRepo.findByUser(tempUser);
	}

	@Override
	public List<TransactionDetail> getAllTransactionDetail() {
		// TODO Auto-generated method stub
		return this.transactionRepo.findAll();
	}

	@Override
	public TransactionDetail deleteTransaction(Long transactionId) {
		// TODO Auto-generated method stub
		
		TransactionDetail deletedTransaction = this.transactionRepo.findById(transactionId)
		.orElseThrow(() -> new ResourceNotFoundException("Transactin", "transactionId", transactionId));
		return deletedTransaction;
	}

//	@Override
	public TransactionDetail updateTransaction(TransactionDetail transactionDetail) {
		// TODO Auto-generated method stub
		transactionDetail.setTransactionDate(new Date());
		return this.transactionRepo.save(transactionDetail);
	}

}

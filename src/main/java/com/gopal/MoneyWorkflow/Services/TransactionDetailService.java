package com.gopal.MoneyWorkflow.Services;

import java.util.List;

import com.gopal.MoneyWorkflow.entities.TransactionDetail;

public interface TransactionDetailService {
	
	TransactionDetail createTransactionDetail(TransactionDetail transactionDetail);
	TransactionDetail getTransactionById(Long transactionId);
	List<TransactionDetail> getAllTransactionDetailOfUser(Long userId);
	List<TransactionDetail> getAllTransactionDetail();
	TransactionDetail deleteTransaction(Long transactionId);
//	TransactionDetail updateTransaction(TransactionDetail transactionDetail);


}

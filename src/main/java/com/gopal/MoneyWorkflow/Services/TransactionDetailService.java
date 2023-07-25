package com.gopal.MoneyWorkflow.Services;

import java.util.List;

import com.gopal.MoneyWorkflow.entities.TransactionDetail;

public interface TransactionDetailService {
	
	TransactionDetail createTransactionDetail(TransactionDetail transactionDetail);
	TransactionDetail getTransactionById(Long transactionId);
	List<TransactionDetail> getAllTransactionDetailOfUser(Integer pageNumber, Integer pageSize, String sortBy,String sortByDirection, Long userId);
	List<TransactionDetail> getAllTransactionDetailOfUser(Long userId);
	List<TransactionDetail> getAllTransactionDetail(Integer pageNumber, Integer pageSize, String sortBy,String sortByDirection);
	TransactionDetail deleteTransaction(Long transactionId);
//	TransactionDetail updateTransaction(TransactionDetail transactionDetail);
	byte[] createExcelwithUserId(Long userId) throws Exception;
	void createEntryOfTransaction(TransactionDetail[] transaction);


}

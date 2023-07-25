package com.gopal.MoneyWorkflow.Services.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gopal.MoneyWorkflow.Exceptions.RequestBodyNotSupported;
import com.gopal.MoneyWorkflow.Exceptions.ResourceNotFoundException;
import com.gopal.MoneyWorkflow.Repositories.TransactionDetailRepo;
import com.gopal.MoneyWorkflow.Repositories.UserRepo;
import com.gopal.MoneyWorkflow.Services.TransactionDetailService;
import com.gopal.MoneyWorkflow.entities.TransactionDetail;
import com.gopal.MoneyWorkflow.entities.User;
import com.gopal.MoneyWorkflow.utility.ExcelHelper;

@Service
public class TransactionDetailServiceImplementation implements TransactionDetailService {

	@Autowired
	private TransactionDetailRepo transactionRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ExcelHelper excel;

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
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
	public List<TransactionDetail> getAllTransactionDetailOfUser(Integer pageNumber, Integer pageSize, String sortBy,String sortByDirection, Long userId) {
		// TODO Auto-generated method stub

		
		User tempUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sortByDirection.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		
		List<TransactionDetail> UserDetailsOfTransaction = this.transactionRepo.findByUser(tempUser,p);
		return UserDetailsOfTransaction;
	}
	
	@Override
	public List<TransactionDetail> getAllTransactionDetailOfUser(Long userId) {
		// TODO Auto-generated method stub

		User tempUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		return this.transactionRepo.findByUser(tempUser);
	}

	@Override
	public List<TransactionDetail> getAllTransactionDetail(Integer pageNumber, Integer pageSize, String sortBy,String sortByDirection) {
		// TODO Auto-generated method stub
		Pageable p = PageRequest.of(pageNumber, pageSize, sortByDirection.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		
		return this.transactionRepo.findAll(p).getContent();
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
		transactionDetail.setTransactionDate(sdf.format(new Date()));
		return this.transactionRepo.save(transactionDetail);
	}
	
	@Override
	public byte[] createExcelwithUserId(Long userId) {
		
		System.out.println("userId : "+ userId);
		byte[] excelData = null;
		
		
		
		List<TransactionDetail> allTransactionDetailOfUser = getAllTransactionDetailOfUser(userId);
		
        System.out.println(allTransactionDetailOfUser.size());
		try {
		
			excelData = excel.appendInExcel(allTransactionDetailOfUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return excelData;
	}

	@Override
	public void createEntryOfTransaction(TransactionDetail[] transaction) {
		// TODO Auto-generated method stub
		for(TransactionDetail tempTransaction : transaction) {
			transactionRepo.save(tempTransaction);
		}
	}

}

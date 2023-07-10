package com.gopal.MoneyWorkflow.Services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopal.MoneyWorkflow.Exceptions.EntityValidationError;
import com.gopal.MoneyWorkflow.Exceptions.RequestBodyNotSupported;
import com.gopal.MoneyWorkflow.Exceptions.ResourceNotFoundException;
import com.gopal.MoneyWorkflow.Repositories.UserRepo;
import com.gopal.MoneyWorkflow.Services.TransactionDetailService;
import com.gopal.MoneyWorkflow.Services.UserService;
import com.gopal.MoneyWorkflow.entities.TransactionDetail;
import com.gopal.MoneyWorkflow.entities.User;

@Service
public class UserServiceImplementaion implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TransactionDetailService transactionService;


	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub

		user.setLastTransactionDate(new Date());
		if(user.getId()!=null) {
			throw new RequestBodyNotSupported("Request Body with Id not supported.");
		}
		User savedUser = this.userRepo.save(user);

		
		this.transactionService.createTransactionDetail(UserToTransactionDetail(savedUser));

		return savedUser;
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

	@Override
	public User deleteUser(Long userId) {
		// TODO Auto-generated method stub
		User deletedUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.deleteById(userId);
		return deletedUser;
	}

	@Override
	public User updateUser(User user) {
		User tempUser = getUserById(user.getId());
		System.out.println(tempUser.getTotalAmount());
		user.setTotalAmount(tempUser.getTotalAmount());
		if(user.getTypeOfTransaction().equalsIgnoreCase("Credit")) {
			user.setTotalAmount(user.getTotalAmount()+user.getLastTransactionAmount());
		}
		else if(user.getTypeOfTransaction().equalsIgnoreCase("Debit")) {
			user.setTotalAmount(user.getTotalAmount()- user.getLastTransactionAmount());
		}
		else {
			 throw new EntityValidationError("Type of Transaction is not Valid : "+user.getTypeOfTransaction());
		}
		user.setLastTransactionDate(new Date());
		

		this.transactionService.createTransactionDetail(UserToTransactionDetail(user));
		
		return this.userRepo.save(user);
	}

	public TransactionDetail UserToTransactionDetail(User user) {
		
		TransactionDetail transactionDetail = new TransactionDetail();

		transactionDetail.setTransactionAmount(user.getLastTransactionAmount());
		transactionDetail.setTransactionDate(user.getLastTransactionDate());
		transactionDetail.setTypeOfTransaction(user.getTypeOfTransaction());
		transactionDetail.setUsername(user.getUsername());
		transactionDetail.setTotalAmount(user.getTotalAmount());
		transactionDetail.setDescription(user.getDescription());
		transactionDetail.setUser(user);
	
//		TypeMap<User, TransactionDetail> mappedData = this.modelMapper.addMappings(new PropertyMap<User, TransactionDetail>(){
//		    @Override
//		    protected void configure() {
//		        skip(destination.getTransactionId());
//		    }
//		});
//		try {
//			transactionDetail =  mappedData.getDestinationType().newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		return transactionDetail;
	}
}

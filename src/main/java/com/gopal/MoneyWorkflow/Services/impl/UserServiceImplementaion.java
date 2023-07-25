package com.gopal.MoneyWorkflow.Services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub

		user.setLastTransactionDate(sdf.format(new Date()));
		if(user.getId()!=null) {
			throw new RequestBodyNotSupported("Request Body with Id not supported.");
		}
		
		if(user.getTypeOfTransaction().equalsIgnoreCase("Debit")) {
			user.setTotalAmount(0L - user.getLastTransactionAmount());
		}
		else if(user.getTypeOfTransaction().equalsIgnoreCase("Credit")) {
			user.setTotalAmount(0L + user.getLastTransactionAmount());
		}
		else {
			throw new EntityValidationError("Type of Transaction is not Valid : "+user.getTypeOfTransaction());
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
	public List<User> getAllUser(Integer pageNumber, Integer pageSize, String sortBy,String sortByDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortByDirection.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, 10, sort);
		return this.userRepo.findAll(p).getContent();
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
	public User updateUser(Long userId, User user) {
		User tempUser = getUserById(userId);
		tempUser.setLastTransactionAmount(user.getLastTransactionAmount());
		tempUser.setDescription(user.getDescription());
		
		if(user.getTypeOfTransaction().equalsIgnoreCase("Credit")) {
			tempUser.setTotalAmount(tempUser.getTotalAmount()+tempUser.getLastTransactionAmount());
			tempUser.setTypeOfTransaction("Credit");
		}
		else if(user.getTypeOfTransaction().equalsIgnoreCase("Debit")) {
			tempUser.setTotalAmount(tempUser.getTotalAmount()- tempUser.getLastTransactionAmount());
			tempUser.setTypeOfTransaction("Debit");
		}
		else {
			 throw new EntityValidationError("Type of Transaction is not Valid : "+user.getTypeOfTransaction());
		}
		tempUser.setLastTransactionDate(sdf.format(new Date()));
		

		this.transactionService.createTransactionDetail(UserToTransactionDetail(tempUser));
		
		return this.userRepo.save(tempUser);
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

	@Override
	public void createEntryOfUser(User[] user) {
		// TODO Auto-generated method stub
		
		for(User tempUser : user) {
			userRepo.save(tempUser);
		}
		
	}

	
}

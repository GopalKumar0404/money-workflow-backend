package com.gopal.MoneyWorkflow.Services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.gopal.MoneyWorkflow.entities.User;

public interface UserService {
	
	User createUser(User user);
	User getUserById(Long userId);
	List<User> getAllUser(Integer pageNumber, Integer pageSize, String sortBy,String sortByDirection);
	User deleteUser(Long userId);
	User updateUser(Long userId, User user);
	

}

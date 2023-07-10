package com.gopal.MoneyWorkflow.Services;

import java.util.List;

import com.gopal.MoneyWorkflow.entities.User;

public interface UserService {
	
	User createUser(User user);
	User getUserById(Long userId);
	List<User> getAllUser();
	User deleteUser(Long userId);
	User updateUser(User user);

}

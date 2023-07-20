package com.gopal.MoneyWorkflow.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.MoneyWorkflow.Services.UserService;
import com.gopal.MoneyWorkflow.entities.User;
import com.gopal.MoneyWorkflow.utility.AppConstants;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@GetMapping("/")
	public ResponseEntity<List<User>>getAllUsers(
			@RequestParam (value ="pageNumber", defaultValue =AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
			@RequestParam (value ="pageSize", defaultValue =AppConstants.PAGE_SIZE, required = false)Integer pageSize,
			@RequestParam (value ="sortBy", defaultValue=AppConstants.USER_SORT, required = false) String sortBy, 
			@RequestParam (value="sortByDirection", defaultValue =AppConstants.SORT_DIRECTION, required = false) String sortByDirection ){
		
		return ResponseEntity.ok(this.userService.getAllUser(pageNumber, pageSize, sortBy,sortByDirection));
		
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		
		return this.userService.getUserById(userId);
	}
	

	
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) {
		
		return this.userService.createUser(user);
		
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId ,@RequestBody User user) {
		
		return this.userService.updateUser(userId,user);
		
	}
	
	@DeleteMapping("/{userId}")
	public User deleteUser(@PathVariable Long userId) {
		
		return this.userService.deleteUser(userId);
	}

}

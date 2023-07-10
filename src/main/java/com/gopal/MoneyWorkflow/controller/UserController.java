package com.gopal.MoneyWorkflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.MoneyWorkflow.Services.UserService;
import com.gopal.MoneyWorkflow.entities.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@GetMapping("/")
	public ResponseEntity<List<User>>getAllUsers() {
		
		return ResponseEntity.ok(this.userService.getAllUser());
		
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		
		return this.userService.getUserById(userId);
	}
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) {
		
		return this.userService.createUser(user);
		
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {
		
		return this.userService.updateUser(user);
		
	}
	
	@DeleteMapping("/{userId}")
	public User deleteUser(@PathVariable Long userId) {
		
		return this.userService.deleteUser(userId);
	}

}

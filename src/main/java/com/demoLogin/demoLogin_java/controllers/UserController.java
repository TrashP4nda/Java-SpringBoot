package com.demoLogin.demoLogin_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demoLogin.demoLogin_java.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam String username , @RequestParam String password){
		userService.registerUser(username, password);
		return ResponseEntity.ok("User registered succesfully");
		
	}
	
	 @PostMapping("/resetPassword")
	    public ResponseEntity<String> resetPassword(@RequestParam String username , @RequestParam String password ,@RequestParam String password2) {
		 userService.changePassword(username,password,password2);
	        return ResponseEntity.status(404).body("Password Succesfully Changed");
	    }
	 
	 @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
	        if (userService.validateUser(username, password)) {
	            return ResponseEntity.ok("Login Successful");
	        }
	        return ResponseEntity.status(401).body("Invalid Credentials");
	    }
}

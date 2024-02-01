package com.demoLogin.demoLogin_java.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demoLogin.demoLogin_java.models.User;
import com.demoLogin.demoLogin_java.repositories.UserRepository;

@Service
public class UserService {
	private Map<String,String> users = new HashMap<>();
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


	
	public UserService() {
		
	}
	
	
	
	public boolean validateUser(String username , String password) {
		User user = userRepository.findByUsername(username);
		return user != null && passwordEncoder.matches(password, user.getPassword());
    }
	
	public boolean changePassword(String username, String oldPassword, String newPassword) {
	    User user = userRepository.findByUsername(username);
	    if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
	        user.setPassword(passwordEncoder.encode(newPassword));
	        userRepository.save(user);
	        return true;
	    }
	    return false;
	}

    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username) == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            userRepository.save(newUser);
            return true;
        } else {
            // User already exists
            return false;
        }
    }
}

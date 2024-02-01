package com.demoLogin.demoLogin_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoLogin.demoLogin_java.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByUsername(String username);
	
}

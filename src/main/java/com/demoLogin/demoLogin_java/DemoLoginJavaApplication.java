package com.demoLogin.demoLogin_java;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.demoLogin.demoLogin_java.models")
@SpringBootApplication
public class DemoLoginJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoLoginJavaApplication.class, args);
	}

}

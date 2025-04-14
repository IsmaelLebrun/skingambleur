package com.example.userM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserMApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMApplication.class, args);
	}

}

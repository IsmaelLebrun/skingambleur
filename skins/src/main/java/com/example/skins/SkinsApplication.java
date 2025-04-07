package com.example.skins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkinsApplication.class, args);
	}

}

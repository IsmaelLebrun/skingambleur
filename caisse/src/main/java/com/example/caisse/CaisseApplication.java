package com.example.caisse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CaisseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaisseApplication.class, args);
	}

}

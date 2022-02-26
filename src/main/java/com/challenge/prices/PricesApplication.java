package com.challenge.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.challenge.entity")
public class PricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}

}

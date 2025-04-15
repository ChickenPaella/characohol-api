package com.characohol.characohol_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class CharacoholApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharacoholApiApplication.class, args);
	}

}

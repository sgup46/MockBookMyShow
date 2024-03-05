package com.sapient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RemoteTheatreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteTheatreApplication.class, args);
	}


}

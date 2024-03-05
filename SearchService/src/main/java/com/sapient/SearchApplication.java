package com.sapient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaRepositories("com.api.*")
//@ComponentScan(basePackages = { "com.api.*" })
//@EntityScan("com.api.*")
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}


}

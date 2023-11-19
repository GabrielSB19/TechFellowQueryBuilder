package com.example.TechFellowQueryBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TechFellowQueryBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechFellowQueryBuilderApplication.class, args);
	}


}
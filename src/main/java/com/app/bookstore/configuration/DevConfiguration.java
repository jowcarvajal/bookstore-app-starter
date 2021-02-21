package com.app.bookstore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.app.bookstore.service.DatabaseService;

@Configuration
@Profile("dev")
public class DevConfiguration {
	
	@Autowired
	private DatabaseService databaseService ;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean isInitialize() {
		if (strategy.equals("create")) {
			this.databaseService.initialize();
		}
		return false;
	}
	
}

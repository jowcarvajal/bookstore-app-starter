package com.app.bookstore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.app.bookstore.service.DatabaseService;

@Configuration
@Profile("test")
public class TestConfiguration {

	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public void initialize() {
		databaseService.initialize();
	}
}

package com.julio.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.julio.helpdesk.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DbService dbService;
	
	@Bean
	public void instanciaDB () {
		this.dbService.instanciaDB();
	}

}

package com.caha.rest.swag.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caha.rest.swag.model.Person;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(PersonRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Person("User1", "LastName1", "Andover")));
			log.info("Preloading " + repository.save(new Person("User2", "LastName2", "Boston")));
		};
	}
}

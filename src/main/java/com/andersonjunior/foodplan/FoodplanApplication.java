package com.andersonjunior.foodplan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.andersonjunior.foodplan.service.services.DBService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FoodplanApplication implements CommandLineRunner {

	private final DBService dbService;

	public FoodplanApplication(DBService dbService) {
		this.dbService = dbService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodplanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbService.instantiateTestDatabase();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}

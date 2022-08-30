package com.andersonjunior.foodplan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andersonjunior.foodplan.service.services.DBService;

@SpringBootApplication
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

}

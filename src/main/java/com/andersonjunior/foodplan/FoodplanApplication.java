package com.andersonjunior.foodplan;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andersonjunior.foodplan.domain.models.Role;
import com.andersonjunior.foodplan.domain.models.User;
import com.andersonjunior.foodplan.service.services.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication //(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class FoodplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodplanApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {

			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Anderson Júnior", "andersonjunior.tech@gmail.com", "123", new ArrayList<>()));
			userService.saveUser(new User(null, "Administrador", "admin@admin", "123", new ArrayList<>()));

			userService.addRoleToUser("andersonjunior.tech@gmail.com", "ROLE_USER");
			userService.addRoleToUser("andersonjunior.tech@gmail.com", "ROLE_MANAGER");
			userService.addRoleToUser("admin@admin.com", "ROLE_ADMIN");

		};
	}

}

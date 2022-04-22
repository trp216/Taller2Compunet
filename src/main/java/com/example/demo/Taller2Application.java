package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServiceImpl;



@SpringBootApplication
public class Taller2Application {

	@Bean

	public CommandLineRunner dummy(UserRepository userRepository) {

		//para cerrar sesion:
		//http://localhost:8081/logout
		return (args) -> {
			UserServiceImpl registration = new UserServiceImpl(userRepository);
	    	User u = new User();
	    	u.setPassword("{noop}1234567");
	    	u.setType(UserType.admin);
	    	u.setUsername("peppa1234");
	    	//u.setOldPassword("{noop}123456789");
	    	registration.save(u);
	    	
	    	
	    	User u2 = new User();
	    	u2.setPassword("{noop}1234");
	    	u2.setType(UserType.operator);
	    	u2.setUsername("dora1234");
	 	    	
			
			registration.save(u2);
		};


	}


	// Alejandra Diaz Parra
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Taller2Application.class, args);
	}

}

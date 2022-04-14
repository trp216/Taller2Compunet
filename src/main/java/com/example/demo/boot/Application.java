package com.example.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repositories")
@EnableAutoConfiguration
@EntityScan(basePackages= {"co.edu.icesi.dev.uccareapp.transport.model.person","co.edu.icesi.dev.uccareapp.transport.model.sales", "co.edu.icesi.dev.uccareapp.transport.model.hr"})
@ComponentScan(basePackages = {"com.example.demo.repositories","com.example.demo.services"} )
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}

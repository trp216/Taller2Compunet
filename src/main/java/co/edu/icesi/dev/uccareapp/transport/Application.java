package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;
import co.edu.icesi.dev.uccareapp.transport.services.UserServiceImpl;

@SpringBootApplication
//@EnableJpaRepositories("com.example.demo.repositories")
//@EnableAutoConfiguration
//@EntityScan(basePackages= {"com.example.demo.model","co.edu.icesi.dev.uccareapp.transport.model.person","co.edu.icesi.dev.uccareapp.transport.model.sales", "co.edu.icesi.dev.uccareapp.transport.model.hr"})
//@ComponentScan(basePackages = {"com.example.demo.repositories","com.example.demo.services"} )
public class Application {

	@Bean

	public CommandLineRunner dummy(UserRepository userRepository) {

		//para cerrar sesion:
		//http://localhost:8081/logout
		return (args) -> {
			//UserServiceImpl registration = new UserServiceImpl(userRepository);
			
			//mientras no haya implementado lo del ultimo taller, para entrar usar las credenciales
			//username: user
			//password: la que salga generada por consola
			
	    	UserApp u = new UserApp();
	    	u.setPassword("{noop}1234567");
	    	u.setType(UserType.admin);
	    	u.setUsername("peppa1234");
	    	//u.setOldPassword("{noop}123456789");
	    	userRepository.save(u);
	    	
	    	UserApp u2 = new UserApp();
	    	u2.setPassword("{noop}1234");
	    	u2.setType(UserType.operator);
	    	u2.setUsername("dora1234");
	 	    	
	    	
			
	    	userRepository.save(u2);
	    	System.out.println(userRepository.findAll());
		};


	}
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	// Alejandra Diaz Parra
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}

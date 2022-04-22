package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;
import com.example.demo.model.UserType;


public interface UserRepository  extends CrudRepository<User, Long>{
	
List<User> findByName(String name);
	
	List<User> findByType(UserType type);
	
	User findByUsername(String username);

}

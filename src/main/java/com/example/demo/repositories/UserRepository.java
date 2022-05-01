package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserApp;
import com.example.demo.model.UserType;

@Repository
public interface UserRepository  extends CrudRepository<UserApp, Long>{
	
//List<User> findByUsername(String username);
	
	List<UserApp> findByType(UserType type);
	
	UserApp findByUsername(String username);

}

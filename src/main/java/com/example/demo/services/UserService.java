package com.example.demo.services;

import java.util.Optional;

import com.example.demo.model.UserApp;
import com.example.demo.model.UserType;

public interface UserService {
	
	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public void delete(UserApp user);


	public UserType[] getTypes();

}

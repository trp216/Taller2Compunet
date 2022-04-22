package com.example.demo.services;

import java.util.Optional;

import com.example.demo.model.User;
import com.example.demo.model.UserType;

public interface UserService {
	
	public void save(User user);

	public Optional<User> findById(long id);

	public Iterable<User> findAll();

	public Iterable<User> findAllAdmins();

	public Iterable<User> findAllOperators();

	public void delete(User user);


	public UserType[] getTypes();

}

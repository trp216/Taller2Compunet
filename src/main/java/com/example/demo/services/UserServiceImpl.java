package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repositories.UserRepository;

public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void save(User user) {
//		if(user.getNewPassword()== null) {
//			user.setPassword(findById(user.getId()).get().getPassword());
//			save(user);
//		}else {
//			if(findById(user.getId()).get().getPassword().equals(user.getOldPassword())) {
//				Optional<User> user1 = findById(user.getId());
//		        user1.get().setOldPassword(user.getOldPassword());
//				user1.get().setPassword(user.getNewPassword());
//				
//				System.out.println(user1.get().getPassword());
//				
//			save(user);
//				
//			}
		userRepository.save(user);
		//}
	}

	public Optional<User> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	public Iterable<User> findAllAdmins() {
		return userRepository.findByType(UserType.admin);
	}
	
	
	public Iterable<User> findAllOperators() {
		return userRepository.findByType(UserType.operator);
	}


	public void delete(User user) {
		userRepository.delete(user);

	}


	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}

}

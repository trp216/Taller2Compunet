package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.services.UserServiceImpl;

@Controller
public class UserControllerImpl {
	
	UserServiceImpl userService;
	
	public UserControllerImpl(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/login";
	}

	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("types", userService.getTypes());

		return "users/add-user";
	}

	@PostMapping("/users/add/")
	public String saveUser(@ModelAttribute User user, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {

			model.addAttribute("user", user);
			model.addAttribute("types", userService.getTypes());

			if (bindingResult.hasErrors()) {

				return "/users/add-user1";

			}

			// model.addAttribute("username",user.getUsername());

		}
		return "/users/add-user";
	}

	

}

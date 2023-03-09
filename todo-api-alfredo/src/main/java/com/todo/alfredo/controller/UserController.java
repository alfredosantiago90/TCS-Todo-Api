package com.todo.alfredo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.alfredo.business.UserService;
import com.todo.alfredo.model.User;



@RestController
@RequestMapping("/user")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	@GetMapping("/all")
	public List<User> getAll(){
		
		return userService.getAll();
	}
	
	
	@PostMapping()
	public User saveUser(@RequestBody User user) {
		log.info("User created");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userService.createUser(user);
	}
	
	@DeleteMapping("eliminar/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		boolean ok = userService.deleteUser(id);
		if(ok) {
			return "User deleted with id " + id;
		}else {
			return "User could'nt be deleted " + id;
		}
	}
	

}

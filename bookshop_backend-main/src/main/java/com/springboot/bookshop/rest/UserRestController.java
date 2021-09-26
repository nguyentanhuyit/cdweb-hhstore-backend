package com.springboot.bookshop.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.error.UserNotFoundException;
import com.springboot.bookshop.service.AuthorService;
import com.springboot.bookshop.service.UserService;
import com.springboot.bookshop.util.BCryptEncodeUtil;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAll() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = userService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found: " + userId);
		}
		return user;
	}
	
	@PostMapping("/users")
	public User addUser(@Valid @RequestBody User user) {
		user.setId(0);
		// encode password
		String encodedPassword = BCryptEncodeUtil.encode(user.getPassword());
		user.setPassword(encodedPassword);
		//
		userService.saveOrUpdate(user);
		return user;
	}
	
	@PutMapping("/users")
	public User updateUser(@Valid @RequestBody User user) {
		// encode password (front-end have to send normal password)
		String encodedPassword = BCryptEncodeUtil.encode(user.getPassword());
		user.setPassword(encodedPassword);
		//
		userService.saveOrUpdate(user);
		return user;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		User user = userService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found: " + userId);
		}
		try {			
			userService.deleteById(userId);
		} catch (Exception e) {
			throw new RuntimeException("Delete User error (delete parent have many child): " + e.getMessage());
		}
		return "Delete User: " + userId;
	}
}




package com.springboot.bookshop.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.error.UserNotFoundException;
import com.springboot.bookshop.service.UserService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public User getUserLogin(@RequestBody User userLogin) {
		User user = userService.checkUserLogin(userLogin);
		if (user == null) {
			throw new UserNotFoundException("Login fail, user not found");
		}
		return user;
	}
	
	@GetMapping("/checkusername/{userName}")
	public boolean checkUserName(@PathVariable String userName) {
		boolean result = userService.checkUserName(userName);
		return result;
	}
	
	@GetMapping("/usernames")
	public List<String> getAllUsername() {
		return userService.getAllUsername();
	}
	
	@PutMapping("/account/verify/{userId}")
	public String updateAccountVerify(@PathVariable int userId) {
		User user = userService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found: " + userId);
		}
		try {			
			userService.updateAccountVerify(userId);
		} catch (Exception e) {
			throw new RuntimeException("Update user verify error: " + e.getMessage());
		}
		return "Update user verify: " + userId;
	}
}

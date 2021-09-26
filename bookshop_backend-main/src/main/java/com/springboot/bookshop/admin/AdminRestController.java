package com.springboot.bookshop.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.service.UserService;

@RestController
@RequestMapping("/api")
public class AdminRestController {
	
	@Autowired
	UserService userService;
	
//	@PostAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String testAdmin() {
		return "here is admin";
	}
	
	@GetMapping("/finduser/{username}")
	public User testFindByName(@PathVariable String username) {
		return userService.findByUsername(username);
	}
}

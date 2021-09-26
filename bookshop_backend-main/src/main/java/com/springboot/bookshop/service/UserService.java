package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void saveOrUpdate(User user);
	
	public void deleteById(int id);
	
	public User checkUserLogin(User userLogin);
	
	public boolean checkUserName(String userName);
	
	public List<String> getAllUsername();
	
	public void updateAccountVerify(int userId);
	
	public User findByUsername(String username);
}

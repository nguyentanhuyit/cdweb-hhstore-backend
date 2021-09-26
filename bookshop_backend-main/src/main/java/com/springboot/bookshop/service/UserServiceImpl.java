package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.AuthorDAO;
import com.springboot.bookshop.dao.UserDAO;
import com.springboot.bookshop.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional
	public User findById(int id) {
		return userDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		userDAO.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		userDAO.deleteById(id);
	}

	@Override
	@Transactional
	public User checkUserLogin(User userLogin) {
		return userDAO.checkUserLogin(userLogin);
	}

	@Override
	@Transactional
	public boolean checkUserName(String userName) {
		return userDAO.checkUserName(userName);
	}

	@Override
	@Transactional
	public void updateAccountVerify(int userId) {
		userDAO.updateAccountVerify(userId);
	}

	@Override
	@Transactional
	public List<String> getAllUsername() {
		return userDAO.getAllUsername();
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

}

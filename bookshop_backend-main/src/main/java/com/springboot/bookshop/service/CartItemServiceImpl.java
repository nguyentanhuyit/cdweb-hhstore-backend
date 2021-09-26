package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.CartItemDAO;
import com.springboot.bookshop.entity.CartItem;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemDAO cartItemDAO;

	@Override
	@Transactional
	public List<CartItem> findAll() {
		return cartItemDAO.findAll();
	}
	
	@Transactional
	@Override
	public CartItem findById(int id) {
		return cartItemDAO.findById(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(CartItem cartItem) {
		cartItemDAO.saveOrUpdate(cartItem);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		cartItemDAO.deleteById(id);
	}

	@Override
	@Transactional
	public List<CartItem> findByUserId(int userId) {
		return cartItemDAO.findByUserId(userId);
	}

	@Override
	@Transactional
	public void deleteByUserId(int userId) {
		cartItemDAO.deleteByUserId(userId);
	}
	
}

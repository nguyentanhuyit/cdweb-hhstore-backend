package com.springboot.bookshop.dao;

import java.util.List;

import com.springboot.bookshop.entity.CartItem;

public interface CartItemDAO {
	public List<CartItem> findAll();
	
	public CartItem findById(int id);
	
	public void saveOrUpdate(CartItem cartItem);
	
	public void deleteById(int id);
	
	public List<CartItem> findByUserId(int userId);
	
	public void deleteByUserId(int userId);
}

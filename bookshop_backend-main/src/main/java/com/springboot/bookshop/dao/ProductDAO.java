package com.springboot.bookshop.dao;

import java.util.List;

import com.springboot.bookshop.entity.Product;

public interface ProductDAO {
	
	public List<Product> findAll();
	
	public Product findById(int id);
	
	public void saveOrUpdate(Product product);
	
	public void deleteById(int id);
	
	public List<Product> showHomeProductByDateRelease();
}

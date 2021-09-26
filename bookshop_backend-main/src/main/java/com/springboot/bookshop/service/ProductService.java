package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Product findById(int id);
	
	public void saveOrUpdate(Product product);
	
	public void deleteById(int id);
	
	public List<Product> showHomeProductByDateRelease();
}

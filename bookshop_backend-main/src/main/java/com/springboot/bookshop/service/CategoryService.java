package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.Category;

public interface CategoryService {
	public List<Category> findAll();
	
	public Category findById(int id);
	
	public void saveOrUpdate(Category category);
	
	public void deleteById(int id);
}

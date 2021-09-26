package com.springboot.bookshop.dao;

import java.util.List;

import com.springboot.bookshop.entity.Category;

public interface CategoryDAO {
	public List<Category> findAll();
	
	public Category findById(int id);
	
	public void saveOrUpdate(Category category);
	
	public void deleteById(int id);
}

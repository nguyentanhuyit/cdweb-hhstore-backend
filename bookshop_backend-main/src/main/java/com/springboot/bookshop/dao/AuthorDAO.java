package com.springboot.bookshop.dao;

import java.util.List;

import com.springboot.bookshop.entity.Author;

public interface AuthorDAO {
	
	public List<Author> findAll();
	
	public Author findById(int id);
	
	public void saveOrUpdate(Author author);
	
	public void deleteById(int id);
}

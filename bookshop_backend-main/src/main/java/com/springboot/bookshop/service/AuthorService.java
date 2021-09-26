package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.Author;

public interface AuthorService {
	
	public List<Author> findAll();
	
	public Author findById(int id);
	
	public void saveOrUpdate(Author author);
	
	public void deleteById(int id);
}

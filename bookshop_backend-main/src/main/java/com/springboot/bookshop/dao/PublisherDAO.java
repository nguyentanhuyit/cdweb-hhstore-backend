package com.springboot.bookshop.dao;

import java.util.List;

import com.springboot.bookshop.entity.Publisher;

public interface PublisherDAO {
	
	public List<Publisher> findAll();
	
	public Publisher findById(int id);
	
	public void saveOrUpdate(Publisher publisher);
	
	public void deleteById(int id);
}

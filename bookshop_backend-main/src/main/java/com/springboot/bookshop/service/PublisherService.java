package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.Publisher;

public interface PublisherService {
	
	public List<Publisher> findAll();
	
	public Publisher findById(int id);
	
	public void saveOrUpdate(Publisher publisher);
	
	public void deleteById(int id);
}

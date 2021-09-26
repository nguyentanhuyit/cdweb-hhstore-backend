package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.AuthorDAO;
import com.springboot.bookshop.dao.PublisherDAO;
import com.springboot.bookshop.entity.Author;
import com.springboot.bookshop.entity.Publisher;

@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	private PublisherDAO publisherDAO;

	@Override
	@Transactional
	public List<Publisher> findAll() {
		return publisherDAO.findAll();
	}

	@Transactional
	@Override
	public Publisher findById(int id) {
		return publisherDAO.findById(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(Publisher publisher) {
		publisherDAO.saveOrUpdate(publisher);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		publisherDAO.deleteById(id);
	}

}

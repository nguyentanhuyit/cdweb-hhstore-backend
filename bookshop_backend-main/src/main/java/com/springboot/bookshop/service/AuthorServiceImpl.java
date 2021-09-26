package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.AuthorDAO;
import com.springboot.bookshop.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorDAO authorDAO;

	@Override
	@Transactional
	public List<Author> findAll() {
		return authorDAO.findAll();
	}

	@Transactional
	@Override
	public Author findById(int id) {
		return authorDAO.findById(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(Author author) {
		authorDAO.saveOrUpdate(author);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		authorDAO.deleteById(id);
	}

}

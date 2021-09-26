package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.CategoryDAO;
import com.springboot.bookshop.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	@Transactional
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Transactional
	@Override
	public Category findById(int id) {
		return categoryDAO.findById(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(Category category) {
		categoryDAO.saveOrUpdate(category);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		categoryDAO.deleteById(id);
	}

}

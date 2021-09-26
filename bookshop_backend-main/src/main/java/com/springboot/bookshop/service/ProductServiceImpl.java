package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.ProductDAO;
import com.springboot.bookshop.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	@Transactional
	public Product findById(int id) {
		return productDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Product product) {
		productDAO.saveOrUpdate(product);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		productDAO.deleteById(id);
	}

	@Override
	@Transactional
	public List<Product> showHomeProductByDateRelease() {
		return productDAO.showHomeProductByDateRelease();
	}

}

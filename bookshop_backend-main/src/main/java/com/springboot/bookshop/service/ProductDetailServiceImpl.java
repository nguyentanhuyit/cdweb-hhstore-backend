package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.ProductDetailDAO;
import com.springboot.bookshop.entity.ProductDetail;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	
	@Autowired
	private ProductDetailDAO productDetailDAO;

	@Override
	@Transactional
	public List<ProductDetail> findAll() {
		return productDetailDAO.findAll();
	}

	@Override
	@Transactional
	public ProductDetail findById(int id) {
		return productDetailDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(ProductDetail productDetail) {
		productDetailDAO.saveOrUpdate(productDetail);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		productDetailDAO.deleteById(id);
	}

}

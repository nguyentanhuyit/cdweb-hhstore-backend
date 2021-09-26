package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.ProductDetail;

public interface ProductDetailService {
	
	public List<ProductDetail> findAll();
	
	public ProductDetail findById(int id);
	
	public void saveOrUpdate(ProductDetail productDetail);
	
	public void deleteById(int id);
}

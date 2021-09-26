package com.springboot.bookshop.dao;

import java.util.List;

import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.ProductDetail;

public interface ProductDetailDAO {
	
	public List<ProductDetail> findAll();
	
	public ProductDetail findById(int id);
	
	public void saveOrUpdate(ProductDetail productDetail);
	
	public void deleteById(int id);
}

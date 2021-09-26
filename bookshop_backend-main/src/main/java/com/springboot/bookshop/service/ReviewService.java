package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.Review;

public interface ReviewService {
	public List<Review> findAll();
	
	public Review findById(int reviewId);
	
	public void saveOrUpdate(Review review);
	
	public void deleteById(int reviewId);
	
	public List<Review> findByProductId(int productId);
}

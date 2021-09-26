package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.ReviewDAO;
import com.springboot.bookshop.entity.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDAO reviewDAO;

	@Override
	@Transactional
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Review findById(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Review review) {
		reviewDAO.saveOrUpdate(review);
	}

	@Override
	@Transactional
	public void deleteById(int reviewId) {
		reviewDAO.deleteById(reviewId);
	}

	@Override
	@Transactional
	public List<Review> findByProductId(int productId) {
		return reviewDAO.findByProductId(productId);
	}

}

package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.Review;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review findById(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Review review) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(review);
		
	}

	@Override
	public void deleteById(int reviewId) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from Review where id=:reviewId");
//		query.setParameter("reviewId", reviewId);
		Review review = findById(reviewId);
		currentSession.delete(review);
	}

	@Override
	public List<Review> findByProductId(int productId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Review> query = currentSession.createQuery("from Review where product.id=:productId");
		query.setParameter("productId", productId);
		List<Review> reviews = query.getResultList();
		return reviews;
	}
	
}

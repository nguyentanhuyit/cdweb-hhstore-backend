package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.CartItem;

@Repository
public class CartItemDAOImpl implements CartItemDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<CartItem> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CartItem> query = currentSession.createQuery("from CartItem");
		List<CartItem> cartItems = query.getResultList();
		return cartItems;
	}

	@Override
	public CartItem findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		CartItem cartItem = currentSession.get(CartItem.class, id);
		return cartItem;
	}

	@Override
	public void saveOrUpdate(CartItem cartItem) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(cartItem);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from CartItem where id=:cartItemId");
//		query.setParameter("cartItemId", id);
//		query.executeUpdate();
		CartItem cartItem = findById(id);
		currentSession.delete(cartItem);
	}

	@Override
	public List<CartItem> findByUserId(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CartItem> query = currentSession.createQuery("from CartItem where user.id=:userId");
		query.setParameter("userId", userId);
		List<CartItem> cartItems = query.getResultList();
		return cartItems;
	}

	@Override
	public void deleteByUserId(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from CartItem where user.id=:userId");
		query.setParameter("userId", userId);
		query.executeUpdate();
	}
	
}

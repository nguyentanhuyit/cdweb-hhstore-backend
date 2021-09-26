package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.OrderDetail;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<OrderDetail> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<OrderDetail> query = currentSession.createQuery("from OrderDetail");
		List<OrderDetail> orderDetails = query.getResultList();
		return orderDetails;
	}

	@Override
	public OrderDetail findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		OrderDetail orderDetail = currentSession.get(OrderDetail.class, id);
		return orderDetail;
	}

	@Override
	public void saveOrUpdate(OrderDetail orderDetail) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(orderDetail);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from OrderDetail where id=:orderDetailId");
//		query.setParameter("orderDetailId", id);
//		query.executeUpdate();
		OrderDetail orderDetail = findById(id);
		currentSession.delete(orderDetail);
	}

}

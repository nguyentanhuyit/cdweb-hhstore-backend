package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.OrderTable;

@Repository
public class OrderTableDAOImpl implements OrderTableDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<OrderTable> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<OrderTable> query = currentSession.createQuery("from OrderTable", OrderTable.class);
		List<OrderTable> orderTables = query.getResultList();
		return orderTables;
	}

	@Override
	public OrderTable findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		OrderTable orderTable = currentSession.get(OrderTable.class, id);
		return orderTable;
	}

	@Override
	public void saveOrUpdate(OrderTable orderTable) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(orderTable);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from OrderTable where id=:orderTableId");
//		query.setParameter("orderTableId", id);
//		query.executeUpdate();
		OrderTable orderTable = findById(id);
		currentSession.delete(orderTable);
	}

	@Override
	public List<OrderTable> findByUserId(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<OrderTable> query = currentSession.createQuery("from OrderTable where user.id=:userId order by id desc", OrderTable.class);
		query.setParameter("userId", userId);
		List<OrderTable> orderTables = query.getResultList();
		return orderTables;
	}

}

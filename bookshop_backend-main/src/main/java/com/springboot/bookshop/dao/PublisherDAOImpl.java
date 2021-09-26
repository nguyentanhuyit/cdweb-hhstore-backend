package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.Publisher;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Publisher> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Publisher> query = currentSession.createQuery("from Publisher");
		List<Publisher> publishers = query.getResultList();
		return publishers;
	}

	@Override
	public Publisher findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Publisher publisher = currentSession.get(Publisher.class, id);
		return publisher;
	}

	@Override
	public void saveOrUpdate(Publisher publisher) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(publisher);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from Publisher where id=:publisherId");
//		query.setParameter("publisherId", id);
//		query.executeUpdate();
		Publisher publisher = findById(id);
		currentSession.delete(publisher);
	}
	
	
}

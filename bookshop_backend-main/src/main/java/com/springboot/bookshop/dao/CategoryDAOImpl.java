package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.bookshop.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Category> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Category> query = currentSession.createQuery("from Category");
		List<Category> categories = query.getResultList();
		return categories;
	}

	@Override
	public Category findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Category category = currentSession.get(Category.class, id);
		return category;
	}

	@Override
	public void saveOrUpdate(Category category) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(category);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from Category where id=:categoryId");
//		query.setParameter("categoryId", id);
//		query.executeUpdate();
		Category category = findById(id);
		currentSession.delete(category);
	}
	
	
}

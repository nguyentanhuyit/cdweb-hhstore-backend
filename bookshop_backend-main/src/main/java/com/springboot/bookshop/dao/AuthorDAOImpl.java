package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Author> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Author> query = currentSession.createQuery("from Author");
		List<Author> authors = query.getResultList();
		return authors;
	}

	@Override
	public Author findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Author author = currentSession.get(Author.class, id);
		return author;
	}

	@Override
	public void saveOrUpdate(Author author) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(author);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from Author where id=:authorId");
//		query.setParameter("authorId", id);
//		query.executeUpdate();
		Author author = findById(id);
		currentSession.delete(author);
	}
	
	
}

package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Product> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Product> query = currentSession.createQuery("from Product", Product.class);
		List<Product> products = query.getResultList();
		return products;
	}

	@Override
	public Product findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Product product = currentSession.get(Product.class, id);
		return product;
	}

	@Override
	public void saveOrUpdate(Product product) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(product);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from Product where id=:productId");
//		query.setParameter("productId", id);
//		query.executeUpdate();
		Product product = findById(id);
		currentSession.delete(product);
	}

	@Override
	public List<Product> showHomeProductByDateRelease() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Product> query = 
				currentSession.createQuery("from Product order by productDetail.dateRelease desc", Product.class);
		List<Product> products = query.setFirstResult(0).setMaxResults(20).getResultList();
		return products;
	}
	
}

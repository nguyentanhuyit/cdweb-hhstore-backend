package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.ProductDetail;

@Repository
public class ProductDetailDAOImpl implements ProductDetailDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<ProductDetail> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ProductDetail> query = currentSession.createQuery("from ProductDetail", ProductDetail.class);
		List<ProductDetail> productDetails = query.getResultList();
		return productDetails;
	}

	@Override
	public ProductDetail findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		ProductDetail productDetail = currentSession.get(ProductDetail.class, id);
		return productDetail;
	}

	@Override
	public void saveOrUpdate(ProductDetail productDetail) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(productDetail);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from ProductDetail where id=:productDetailId");
//		query.setParameter("productDetailId", id);
//		query.executeUpdate();
		ProductDetail productDetail = findById(id);
		currentSession.delete(productDetail);
	}
	
}

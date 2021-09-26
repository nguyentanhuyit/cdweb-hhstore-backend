package com.springboot.bookshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Page<Product> findByCategoryId(int categoryId, Pageable pageable);
	Page<Product> findByAuthorId(int authorId, Pageable pageable);
	Page<Product> findByPriceBetween(int priceFrom, int priceTo, Pageable pageable);
	
	Page<Product> findByCategoryIdAndAuthorId(int categoryId, int authorId, Pageable pageable);
	Page<Product> findByCategoryIdAndPriceBetween(int categoryId, int priceFrom, int priceTo, Pageable pageable);
	
	Page<Product> findByAuthorIdAndPriceBetween(int authorId, int priceFrom, int priceTo, Pageable pageable);
	
	Page<Product> findByCategoryIdAndAuthorIdAndPriceBetween(int categoryId, int authorId, int priceFrom, int priceTo, Pageable pageable);
	
	// search
	Page<Product> findByTitleContainingIgnoreCase(String search, Pageable pageable);
}

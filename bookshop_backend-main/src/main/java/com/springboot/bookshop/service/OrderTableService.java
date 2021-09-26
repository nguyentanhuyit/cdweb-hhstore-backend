package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.OrderTable;

public interface OrderTableService {
	public List<OrderTable> findAll();
	
	public OrderTable findById(int id);
	
	public void saveOrUpdate(OrderTable orderTable);
	
	public void deleteById(int id);
	
	public List<OrderTable> findByUserId(int userId);
}

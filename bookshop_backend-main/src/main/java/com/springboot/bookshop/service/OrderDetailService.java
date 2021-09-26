package com.springboot.bookshop.service;

import java.util.List;

import com.springboot.bookshop.entity.OrderDetail;

public interface OrderDetailService {
	public List<OrderDetail> findAll();
	
	public OrderDetail findById(int id);
	
	public void saveOrUpdate(OrderDetail orderDetail);
	
	public void deleteById(int id);
}

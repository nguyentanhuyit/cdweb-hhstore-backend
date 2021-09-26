package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.OrderDetailDAO;
import com.springboot.bookshop.entity.OrderDetail;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	@Transactional
	public List<OrderDetail> findAll() {
		return orderDetailDAO.findAll();
	}

	@Override
	@Transactional
	public OrderDetail findById(int id) {
		return orderDetailDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(OrderDetail orderDetail) {
		orderDetailDAO.saveOrUpdate(orderDetail);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		orderDetailDAO.deleteById(id);
	}

}

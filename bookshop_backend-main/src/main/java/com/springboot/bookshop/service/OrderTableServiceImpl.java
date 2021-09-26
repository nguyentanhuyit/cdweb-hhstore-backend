package com.springboot.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bookshop.dao.OrderTableDAO;
import com.springboot.bookshop.entity.OrderTable;

@Service
public class OrderTableServiceImpl implements OrderTableService {

	@Autowired
	private OrderTableDAO orderTableDAO;
	
	@Override
	@Transactional
	public List<OrderTable> findAll() {
		return orderTableDAO.findAll();
	}

	@Override
	@Transactional
	public OrderTable findById(int id) {
		return orderTableDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(OrderTable orderTable) {
		orderTableDAO.saveOrUpdate(orderTable);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		orderTableDAO.deleteById(id);
	}

	@Override
	@Transactional
	public List<OrderTable> findByUserId(int userId) {
		return orderTableDAO.findByUserId(userId);
	}

}

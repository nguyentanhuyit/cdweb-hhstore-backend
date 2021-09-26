package com.springboot.bookshop.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookshop.entity.OrderDetail;
import com.springboot.bookshop.entity.OrderTable;
import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.service.OrderTableService;
import com.springboot.bookshop.service.ProductService;
import com.springboot.bookshop.service.UserService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class OrderTableRestController {
	
	@Autowired
	private OrderTableService orderTableService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/ordertables")
	public List<OrderTable> getAll() {
		return orderTableService.findAll();
	}
	
	@GetMapping("/ordertables/{orderTableId}")
	public OrderTable getOrderTable(@PathVariable int orderTableId) {
		OrderTable orderTable = orderTableService.findById(orderTableId);
		if (orderTable == null) {
			throw new RuntimeException("OrderTable not found: " + orderTableId);
		}
		return orderTable;
	}
	
	/* 
	 * {
	 * 		"id": 0,
	 * 		"user": {"id": 3}
	 * 		...
	 * 		...
	 * 
	 * */
	
	@PostMapping("/ordertables")
	public OrderTable addOrderTable(@RequestBody OrderTable orderTable) {
		orderTable.setId(0);
		LocalDate date = LocalDate.now();
		orderTable.setDateCreated(date);
		orderTableService.saveOrUpdate(orderTable);
		return orderTable;
	}
	
	@PutMapping("/ordertables")
	public OrderTable updateOrderTable(@RequestBody OrderTable orderTable) {
		LocalDate date = LocalDate.now();
		orderTable.setDateCreated(date);
		orderTableService.saveOrUpdate(orderTable);
		return orderTable;
	}
	
	@DeleteMapping("/ordertables/{orderTableId}")
	public String deleteOrderTable(@PathVariable int orderTableId) {
		OrderTable orderTable = orderTableService.findById(orderTableId);
		if (orderTable == null) {
			throw new RuntimeException("OrderTable not found: " + orderTableId);
		}
		try {			
			orderTableService.deleteById(orderTableId);
		} catch (Exception e) {
			throw new RuntimeException("Delete OrderTable error (delete parent have many child): " + e.getMessage());
		}
		return "Delete OrderTable: " + orderTableId;
	}
	
	
	@GetMapping("/ordertablelist/{userId}")
	public List<OrderTable> getOrderTablesByUserId(@PathVariable int userId) {
		return orderTableService.findByUserId(userId);
	}
	
	
}

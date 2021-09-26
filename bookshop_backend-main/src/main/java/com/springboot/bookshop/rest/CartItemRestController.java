package com.springboot.bookshop.rest;

import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.bookshop.entity.CartItem;
import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.error.CartItemNotFoundException;
import com.springboot.bookshop.repository.CartItemRepository;
import com.springboot.bookshop.service.CartItemService;
import com.springboot.bookshop.service.ProductService;
import com.springboot.bookshop.service.UserService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class CartItemRestController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CartItemRepository cartItemRepository;

	@GetMapping("/cartitems")
	public List<CartItem> getAll() {
		return cartItemService.findAll();
	}

	@GetMapping("/cartitems/{cartItemId}")
	public CartItem getCartItem(@PathVariable int cartItemId) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		if (cartItem == null) {
			throw new CartItemNotFoundException("CartItem not found: " + cartItemId);
		}
		return cartItem;
	}

	/*
	 * JSON
	 *
	 * { "id": 9, "user": { "id": 3 }, "product": { "id": 70 }, "quantity": 2 }
	 * 
	 *
	 */

	@PostMapping("/cartitems")
	public CartItem addCartItem(@RequestBody CartItem cartItem) {
		cartItem.setId(0);
		cartItemService.saveOrUpdate(cartItem);
		return cartItem;
	}

	@PutMapping("/cartitems")
	public CartItem updateCartItem(@RequestBody CartItem cartItem) {
		cartItemService.saveOrUpdate(cartItem);
		return cartItem;
	}

	@DeleteMapping("/cartitems/{cartItemId}")
	public String deleteCartItem(@PathVariable int cartItemId) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		if (cartItem == null) {
			throw new CartItemNotFoundException("CartItem not found: " + cartItemId);
		}
		try {
			cartItemService.deleteById(cartItemId);
		} catch (Exception e) {
			throw new RuntimeException("Delete CartItem error (delete parent have many child): " + e.getMessage());
		}
		return "Delete CartItem: " + cartItemId;
	}

	@GetMapping("/cart/{userId}")
	public List<CartItem> getCartItemsByUserId(@PathVariable int userId) {
		return cartItemService.findByUserId(userId);
	}

	@DeleteMapping("/cartitems/deletebyuserid/{userId}")
	public String deleteCartItemByUserId(@PathVariable int userId) {
		try {
			cartItemService.deleteByUserId(userId);
		} catch (Exception e) {
			throw new RuntimeException("Delete CartItem by userId failed: " + e.getMessage());
		}
		return "Delete CartItem by userId: " + userId;
	}
	
	@GetMapping("/cartitems/userid/{userId}")
	public List<CartItem> getCartItemsByUserIdRepo(@PathVariable int userId) {
		return cartItemRepository.findByUserId(userId);
	}
	
	@GetMapping("/cartitems/totalprice/userid/{userId}")
	public int getTotalPrice(@PathVariable int userId) {
		int result = 0;
		List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
		for (CartItem cartItem : cartItems) {
			result += (cartItem.getProduct().getPrice() * cartItem.getQuantity());
		}
		return result;
	}

}

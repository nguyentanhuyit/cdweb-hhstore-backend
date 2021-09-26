package com.springboot.bookshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "CartItem not found")
public class CartItemNotFoundException extends RuntimeException {
	public CartItemNotFoundException(String exception) {
		super(exception);
	}
}

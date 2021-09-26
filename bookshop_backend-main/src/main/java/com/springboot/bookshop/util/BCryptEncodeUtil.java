package com.springboot.bookshop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncodeUtil {
	public static String encode(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		return passwordEncoder.encode(password);
	}
	
	public static boolean matches(String password, String encodedPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		return passwordEncoder.matches(password, encodedPassword);
	}
	
	public static void main(String[] args) {
		String s = encode("1234");
		System.out.println(s);
	}
}

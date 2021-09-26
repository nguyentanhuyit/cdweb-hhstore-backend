package com.springboot.bookshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.Review;
import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.service.ProductService;
import com.springboot.bookshop.service.ReviewService;
import com.springboot.bookshop.service.UserService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class ReviewRestController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@PostMapping("/reviews")
	public Review addReview(@RequestBody Review review) {
		review.setId(0);
		reviewService.saveOrUpdate(review);
		return review;
	}

	@DeleteMapping("/reviews/{reviewId}")
	public String deleteReview(@PathVariable int reviewId) {
		try {
			reviewService.deleteById(reviewId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "Delete review: " + reviewId;
	}

	@GetMapping("/reviews/product/{productId}")
	public List<Review> getReviewsByProductId(@PathVariable int productId) {
		return reviewService.findByProductId(productId);
	}

}

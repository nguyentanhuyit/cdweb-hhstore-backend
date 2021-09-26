package com.springboot.bookshop.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.repository.ProductRepository;
import com.springboot.bookshop.service.ProductService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

//	@PostAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/products")
	public List<Product> getAll() {
		return productService.findAll();
	}

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable int productId) {
		Product product = productService.findById(productId);
		if (product == null) {
			throw new RuntimeException("Product not found: " + productId);
		}
		return product;
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		product.setId(0);
		productService.saveOrUpdate(product);
		return product;
	}

	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		productService.saveOrUpdate(product);
		return product;
	}

	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		Product product = productService.findById(productId);
		if (product == null) {
			throw new RuntimeException("Product not found: " + productId);
		}
		try {
			productService.deleteById(productId);
		} catch (Exception e) {
			return "Delete product error (delete parent have many child): " + e.getMessage();
		}
		return "Deleted product: " + productId;
	}

	@GetMapping("/products/daterelease")
	public List<Product> getProductsByDateRelease() {
		return productService.showHomeProductByDateRelease();
	}

	@GetMapping("/products/params")
	public Page<Product> getProductsByParams(@RequestParam(required = false, name = "categoryId") String categoryId,
			@RequestParam(required = false, name = "authorId") String authorId,
			@RequestParam(required = false, name = "priceFrom") String priceFrom,
			@RequestParam(required = false, name = "priceTo") String priceTo,
			@RequestParam(required = false, name = "page", defaultValue = "0") String page,
			@RequestParam(required = false, name = "sortBy", defaultValue = "id") String sortBy,
			// promotion, purchaseNumber, dateRelease, price
			@RequestParam(required = false, name = "desc", defaultValue = "false") String desc) {

		Pageable pageable = PageRequest.of(Integer.parseInt(page), 10, Sort.by(sortBy));
		if (desc.equals("true")) {
			pageable = PageRequest.of(Integer.parseInt(page), 10, Sort.by(sortBy).descending());
		}

		Page<Product> allProducts = productRepository.findAll(pageable);

		Page<Product> returnProducts = null;

		if (categoryId == null && authorId == null && priceFrom == null && priceTo == null) {
			returnProducts = allProducts;
		}
		if (categoryId != null && authorId == null && priceFrom == null && priceTo == null) {
			returnProducts = productRepository.findByCategoryId(Integer.parseInt(categoryId), pageable);
		}
		if (categoryId == null && authorId != null && priceFrom == null && priceTo == null) {
			returnProducts = productRepository.findByAuthorId(Integer.parseInt(authorId), pageable);
		}
		if (categoryId == null && authorId == null && priceFrom != null && priceTo != null) {
			returnProducts = productRepository.findByPriceBetween(Integer.parseInt(priceFrom),
					Integer.parseInt(priceTo), pageable);
		}
		if (categoryId != null && authorId != null && priceFrom == null && priceTo == null) {
			returnProducts = productRepository.findByCategoryIdAndAuthorId(Integer.parseInt(categoryId),
					Integer.parseInt(authorId), pageable);
		}
		if (categoryId != null && authorId == null && priceFrom != null && priceTo != null) {
			returnProducts = productRepository.findByCategoryIdAndPriceBetween(Integer.parseInt(categoryId),
					Integer.parseInt(priceFrom), Integer.parseInt(priceTo), pageable);
		}
		if (categoryId == null && authorId != null && priceFrom != null && priceTo != null) {
			returnProducts = productRepository.findByAuthorIdAndPriceBetween(Integer.parseInt(authorId),
					Integer.parseInt(priceFrom), Integer.parseInt(priceTo), pageable);
		}
		if (categoryId != null && authorId != null && priceFrom != null && priceTo != null) {
			returnProducts = productRepository.findByCategoryIdAndAuthorIdAndPriceBetween(Integer.parseInt(categoryId),
					Integer.parseInt(authorId), Integer.parseInt(priceFrom), Integer.parseInt(priceTo), pageable);
		}

		return returnProducts;
	}

	@GetMapping("/products/search")
	public Page<Product> getProductsBySearch(@RequestParam(required = false, name = "search") String search,
			@RequestParam(required = false, name = "page", defaultValue = "0") String page) {
		Pageable pageable = PageRequest.of(Integer.parseInt(page), 10);
		return productRepository.findByTitleContainingIgnoreCase(search, pageable);
	}
}

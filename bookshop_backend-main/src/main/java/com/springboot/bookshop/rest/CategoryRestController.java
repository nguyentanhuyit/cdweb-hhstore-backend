package com.springboot.bookshop.rest;

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

import com.springboot.bookshop.entity.Author;
import com.springboot.bookshop.entity.Category;
import com.springboot.bookshop.service.AuthorService;
import com.springboot.bookshop.service.CategoryService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/categories/{categoryId}")
	public Category getCategory(@PathVariable int categoryId) {
		Category category = categoryService.findById(categoryId);
		if (category == null) {
			throw new RuntimeException("Category not found: " + categoryId);
		}
		return category;
	}
	
	@PostMapping("/categories")
	public Category addCategory(@RequestBody Category category) {
		category.setId(0);
		categoryService.saveOrUpdate(category);
		return category;
	}
	
	@PutMapping("/categories")
	public Category updateCategory(@RequestBody Category category) {
		categoryService.saveOrUpdate(category);
		return category;
	}
	
	@DeleteMapping("/categories/{categoryId}")
	public String deleteCategory(@PathVariable int categoryId) {
		Category category = categoryService.findById(categoryId);
		if (category == null) {
			throw new RuntimeException("Category not found: " + categoryId);
		}
		try {			
			categoryService.deleteById(categoryId);
		} catch (Exception e) {
			return "Delete Category error (delete parent have many child): " + e.getMessage();
		}
		return "Delete Category: " + categoryId;
	}
}




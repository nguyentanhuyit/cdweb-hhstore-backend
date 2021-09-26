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
import com.springboot.bookshop.service.AuthorService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class AuthorRestController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/authors")
	public List<Author> getAll() {
		return authorService.findAll();
	}
	
	@GetMapping("/authors/{authorId}")
	public Author getAuthor(@PathVariable int authorId) {
		Author author = authorService.findById(authorId);
		if (author == null) {
			throw new RuntimeException("Author not found: " + authorId);
		}
		return author;
	}
	
	@PostMapping("/authors")
	public Author addAuthor(@RequestBody Author author) {
		author.setId(0);
		authorService.saveOrUpdate(author);
		return author;
	}
	
	@PutMapping("/authors")
	public Author updateAuthor(@RequestBody Author author) {
		authorService.saveOrUpdate(author);
		return author;
	}
	
	@DeleteMapping("/authors/{authorId}")
	public String deleteAuthor(@PathVariable int authorId) {
		Author author = authorService.findById(authorId);
		if (author == null) {
			throw new RuntimeException("Author not found: " + authorId);
		}
		try {			
			authorService.deleteById(authorId);
		} catch (Exception e) {
			return "Delete author error (delete parent have many child): " + e.getMessage();
		}
		return "Delete Author: " + authorId;
	}
}




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
import com.springboot.bookshop.entity.Publisher;
import com.springboot.bookshop.service.AuthorService;
import com.springboot.bookshop.service.PublisherService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class PublisherRestController {
	
	@Autowired
	private PublisherService publisherService;
	
	@GetMapping("/publishers")
	public List<Publisher> getAll() {
		return publisherService.findAll();
	}
	
	@GetMapping("/publishers/{publisherId}")
	public Publisher getPublisher(@PathVariable int publisherId) {
		Publisher publisher = publisherService.findById(publisherId);
		if (publisher == null) {
			throw new RuntimeException("Publisher not found: " + publisherId);
		}
		return publisher;
	}
	
	@PostMapping("/publishers")
	public Publisher addPublisher(@RequestBody Publisher publisher) {
		publisher.setId(0);
		publisherService.saveOrUpdate(publisher);
		return publisher;
	}
	
	@PutMapping("/publishers")
	public Publisher updatePublisher(@RequestBody Publisher publisher) {
		publisherService.saveOrUpdate(publisher);
		return publisher;
	}
	
	@DeleteMapping("/publishers/{publisherId}")
	public String deletePublisher(@PathVariable int publisherId) {
		Publisher publisher = publisherService.findById(publisherId);
		if (publisher == null) {
			throw new RuntimeException("Publisher not found: " + publisherId);
		}
		try {			
			publisherService.deleteById(publisherId);
		} catch (Exception e) {
			return "Delete Publisher error (delete parent have many child): " + e.getMessage();
		}
		return "Delete Publisher: " + publisherId;
	}
}




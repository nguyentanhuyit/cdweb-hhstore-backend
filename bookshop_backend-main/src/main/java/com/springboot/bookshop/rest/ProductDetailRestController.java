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

import com.springboot.bookshop.entity.Product;
import com.springboot.bookshop.entity.ProductDetail;
import com.springboot.bookshop.service.ProductDetailService;
import com.springboot.bookshop.service.ProductService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/api")
public class ProductDetailRestController {
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@GetMapping("/productdetails")
	public List<ProductDetail> getAll() {
		return productDetailService.findAll();
	}
	
	@GetMapping("/productdetails/{productDetailId}")
	public ProductDetail getProductDetail(@PathVariable int productDetailId) {
		ProductDetail productDetail = productDetailService.findById(productDetailId);
		if (productDetail == null) {
			throw new RuntimeException("ProductDetail not found: " + productDetailId);
		}
		return productDetail;
	}
	
	@PostMapping("/productdetails")
	public ProductDetail addProductDetail(@RequestBody ProductDetail productDetail) {
		productDetail.setId(0);
		productDetailService.saveOrUpdate(productDetail);
		return productDetail;
	}
	
	@PutMapping("/productdetails")
	public ProductDetail updateProductDetail(@RequestBody ProductDetail productDetail) {
		productDetailService.saveOrUpdate(productDetail);
		return productDetail;
	}
	
	@DeleteMapping("/productdetails/{productDetailId}")
	public String deleteProductDetail(@PathVariable int productDetailId) {
		ProductDetail productDetail = productDetailService.findById(productDetailId);
		if (productDetail == null) {
			throw new RuntimeException("Product not found: " + productDetailId);
		}
		try {
			productDetailService.deleteById(productDetailId);
		} catch (Exception e) {
			return "Delete productDetail error (delete parent have many child): " + e.getMessage();
		}
		return "Deleted productDetail: " + productDetailId;
	}
	
	@PutMapping("/productdetails/{productDetailId}/{type}/{number}")
	public ProductDetail consumeAndRestore(@PathVariable int productDetailId, @PathVariable String type, @PathVariable int number) {
		ProductDetail productDetail = productDetailService.findById(productDetailId);
		if (productDetail == null) {
			throw new RuntimeException("ProductDetail not found: " + productDetailId);
		}
		if (type.equals("consume")) {
			productDetail.setPurchaseNumber(productDetail.getPurchaseNumber() + number);
		}
		if (type.equals("restore")) {
			productDetail.setPurchaseNumber(productDetail.getPurchaseNumber() - number);
		}
		productDetailService.saveOrUpdate(productDetail);
		return productDetail;
	}
}

package com.kenleo.springboot_mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenleo.springboot_mall.constant.ProductCategory;
import com.kenleo.springboot_mall.dto.ProductRequest;
import com.kenleo.springboot_mall.model.Product;
import com.kenleo.springboot_mall.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(
			@RequestParam(required = false) ProductCategory category,
			@RequestParam(required = false) String search) {

		List<Product> prodList = productService.getProducts(category, search);

		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		Product product = productService.getProductById(productId);
		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
		Integer productId = productService.createProduct(productRequest);
		Product product = productService.getProductById(productId);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
			@RequestBody @Valid ProductRequest productRequest) {
		// 檢查是否存在
		Product product = productService.getProductById(productId);
		if (product == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		productService.updateProduct(productId, productRequest);
		Product updatedProduct = productService.getProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {

		productService.deleteProductById(productId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}

package com.kenleo.springboot_mall.service;

import java.util.List;

import com.kenleo.springboot_mall.dto.ProductQueryParams;
import com.kenleo.springboot_mall.dto.ProductRequest;
import com.kenleo.springboot_mall.model.Product;

public interface ProductService {

	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, ProductRequest productRequest);
	
	void deleteProductById(Integer productId);
	
	List<Product> getProducts(ProductQueryParams productQueryParams);
	
	Integer countProduct(ProductQueryParams productQueryParams);
}

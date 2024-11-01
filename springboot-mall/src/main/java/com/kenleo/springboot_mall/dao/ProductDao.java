package com.kenleo.springboot_mall.dao;

import com.kenleo.springboot_mall.dto.ProductRequest;
import com.kenleo.springboot_mall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
}

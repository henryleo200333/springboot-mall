package com.kenleo.springboot_mall.service;

import com.kenleo.springboot_mall.dto.ProductRequest;
import com.kenleo.springboot_mall.model.Product;

public interface ProductService {

	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);
}

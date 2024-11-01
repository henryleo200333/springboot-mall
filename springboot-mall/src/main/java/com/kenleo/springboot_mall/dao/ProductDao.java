package com.kenleo.springboot_mall.dao;

import java.util.List;

import com.kenleo.springboot_mall.constant.ProductCategory;
import com.kenleo.springboot_mall.dto.ProductRequest;
import com.kenleo.springboot_mall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, ProductRequest productRequest);

	void deleteProductById(Integer productId);

	List<Product> getProducts(ProductCategory category, String search);
}

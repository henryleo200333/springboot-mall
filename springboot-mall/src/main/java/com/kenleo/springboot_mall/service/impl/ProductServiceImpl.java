package com.kenleo.springboot_mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.dao.ProductDao;
import com.kenleo.springboot_mall.dto.ProductRequest;
import com.kenleo.springboot_mall.model.Product;
import com.kenleo.springboot_mall.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		return productDao.createProduct(productRequest);
	}

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		productDao.updateProduct(productId, productRequest);
		
	}

	@Override
	public void deleteProductById(Integer productId) {
		productDao.deleteProductById(productId);	
	}

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

}

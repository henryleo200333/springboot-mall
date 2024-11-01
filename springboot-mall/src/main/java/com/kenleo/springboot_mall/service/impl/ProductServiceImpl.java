package com.kenleo.springboot_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.dao.ProductDao;
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

}

package com.kenleo.springboot_mall.dao;

import com.kenleo.springboot_mall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);
}

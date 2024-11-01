package com.kenleo.springboot_mall.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.dao.ProductDao;
import com.kenleo.springboot_mall.model.Product;
import com.kenleo.springboot_mall.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Product getProductById(Integer productId) {
		String sql = "select * from product where product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		
		List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		if(productList.isEmpty()) {
			return null;
		}else {
			return productList.get(0);
		}
		
	}

}

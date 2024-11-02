package com.kenleo.springboot_mall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.constant.ProductCategory;
import com.kenleo.springboot_mall.dao.ProductDao;
import com.kenleo.springboot_mall.dto.ProductQueryParams;
import com.kenleo.springboot_mall.dto.ProductRequest;
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
		if (productList.isEmpty()) {
			return null;
		} else {
			return productList.get(0);
		}

	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {

		String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

		Map<String, Object> map = new HashMap<>();
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());

		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {

		String sql = "UPDATE product set "
				+ "product_name = :productName, category = category, image_url = :imageUrl, price = :price,"
				+ " stock = :stock, description = :description, last_modified_date = :lastModifiedDate"
				+ " where product_id=:productId";

		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);

		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());

		Date now = new Date();
		map.put("lastModifiedDate", now);

		namedParameterJdbcTemplate.update(sql, map);

	}

	@Override
	public void deleteProductById(Integer productId) {
		
		String sql = "DELETE FROM product where product_id=:productId";
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		namedParameterJdbcTemplate.update(sql, map);
				
	}

	@Override
	public List<Product> getProducts(ProductQueryParams productQueryParams) {
		String sql = "select * from product where 1=1";

		Map<String, Object> map = new HashMap<>();
		
		if(productQueryParams.getCategory()!= null) {
			map.put("category", productQueryParams.getCategory().name());
			sql += " and category = :category";
		}
		if(productQueryParams.getSearch() != null) {
			map.put("search", "%" + productQueryParams.getSearch() + "%");
			sql += " and product_name like :search";
		}
		
		sql += " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();
		sql += " LIMIT " + productQueryParams.getLimit() + " OFFSET " + productQueryParams.getOffset();
		List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		
		return productList;
	}

}

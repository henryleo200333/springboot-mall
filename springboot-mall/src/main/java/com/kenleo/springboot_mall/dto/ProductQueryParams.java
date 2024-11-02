package com.kenleo.springboot_mall.dto;

import com.kenleo.springboot_mall.constant.ProductCategory;

public class ProductQueryParams {

	private String search;

	private ProductCategory category;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

}

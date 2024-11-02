package com.kenleo.springboot_mall.dto;

import com.kenleo.springboot_mall.constant.ProductCategory;

public class ProductQueryParams {

	private String search;

	private ProductCategory category;

	private String sort;
	private String orderBy;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

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

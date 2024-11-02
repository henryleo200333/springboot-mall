package com.kenleo.springboot_mall.dao;

import java.util.List;

import com.kenleo.springboot_mall.model.OrderItem;

public interface MyOrderDao {

	Integer createOrder(Integer userId, Integer totalAmount);
	
	void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
	
}

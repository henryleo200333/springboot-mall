package com.kenleo.springboot_mall.service;

import java.util.List;

import com.kenleo.springboot_mall.dto.CreateOrderRequest;
import com.kenleo.springboot_mall.dto.OrderQueryParams;
import com.kenleo.springboot_mall.model.MyOrder;

public interface MyOrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

	MyOrder getOrderById(Integer orderId);

	List<MyOrder> getOrders(OrderQueryParams orderQueryParams);

	Integer countOrder(OrderQueryParams orderQueryParams);
}

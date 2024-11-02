package com.kenleo.springboot_mall.service;

import com.kenleo.springboot_mall.dto.CreateOrderRequest;
import com.kenleo.springboot_mall.model.MyOrder;

public interface MyOrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

	MyOrder getOrderById(Integer orderId);

}

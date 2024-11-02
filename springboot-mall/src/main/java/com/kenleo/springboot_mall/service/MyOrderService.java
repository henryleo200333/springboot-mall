package com.kenleo.springboot_mall.service;

import com.kenleo.springboot_mall.dto.CreateOrderRequest;

public interface MyOrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}

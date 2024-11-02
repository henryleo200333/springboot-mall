package com.kenleo.springboot_mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kenleo.springboot_mall.dto.CreateOrderRequest;
import com.kenleo.springboot_mall.model.MyOrder;
import com.kenleo.springboot_mall.service.MyOrderService;

import jakarta.validation.Valid;

@RestController
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;
	
	@PostMapping("/users/{userId}/orders")
	public ResponseEntity<?> createOrder(@PathVariable Integer userId,
										@RequestBody @Valid CreateOrderRequest createOrderRequest){
		
		Integer orderId = myOrderService.createOrder(userId, createOrderRequest);
		
		MyOrder order = myOrderService.getOrderById(orderId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	

}

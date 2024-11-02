package com.kenleo.springboot_mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenleo.springboot_mall.dto.CreateOrderRequest;
import com.kenleo.springboot_mall.dto.OrderQueryParams;
import com.kenleo.springboot_mall.model.MyOrder;
import com.kenleo.springboot_mall.service.MyOrderService;
import com.kenleo.springboot_mall.util.Page;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;

	@PostMapping("/users/{userId}/orders")
	public ResponseEntity<?> createOrder(@PathVariable Integer userId,
			@RequestBody @Valid CreateOrderRequest createOrderRequest) {

		Integer orderId = myOrderService.createOrder(userId, createOrderRequest);

		MyOrder order = myOrderService.getOrderById(orderId);

		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

	@GetMapping("/users/{userId}/orders")
	public ResponseEntity<Page<MyOrder>> getOrders(@PathVariable Integer userId,
			@RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
			@RequestParam(defaultValue = "0") @Min(0) Integer offset) {

		OrderQueryParams orderQueryParams = new OrderQueryParams();
		orderQueryParams.setLimit(limit);
		orderQueryParams.setOffset(offset);
		orderQueryParams.setUserId(userId);

		List<MyOrder> orderList = myOrderService.getOrders(orderQueryParams);
		Integer count = myOrderService.countOrder(orderQueryParams);

		Page<MyOrder> page = new Page<>();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setResults(orderList);
		page.setTotal(count);

		return ResponseEntity.status(HttpStatus.OK).body(page);

	}

}

package com.kenleo.springboot_mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kenleo.springboot_mall.dao.MyOrderDao;
import com.kenleo.springboot_mall.dao.ProductDao;
import com.kenleo.springboot_mall.dto.BuyItem;
import com.kenleo.springboot_mall.dto.CreateOrderRequest;
import com.kenleo.springboot_mall.model.OrderItem;
import com.kenleo.springboot_mall.model.Product;
import com.kenleo.springboot_mall.service.MyOrderService;

@Component
public class MyOrderServiceImpl implements MyOrderService {

	@Autowired
	MyOrderDao myOrderDao;

	@Autowired
	ProductDao productDao;

	@Override
	@Transactional
	public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

		int totalAmount = 0;
		List<OrderItem> orderItemList = new ArrayList<>();

		for (BuyItem item : createOrderRequest.getBuyItemList()) {

			Product product = productDao.getProductById(item.getProductId());

			int amount = product.getPrice() * item.getQuantity();
			totalAmount += amount;

			// 轉換BuyItem to OrderItem
			OrderItem orderItem = new OrderItem();
			orderItem.setAmount(amount);
			orderItem.setProductId(item.getProductId());
			orderItem.setQuantity(item.getQuantity());
			orderItemList.add(orderItem);
		}

		Integer orderId = myOrderDao.createOrder(userId, totalAmount);

		myOrderDao.createOrderItems(orderId, orderItemList);

		return orderId;
	}

}

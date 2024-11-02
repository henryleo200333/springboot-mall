package com.kenleo.springboot_mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.kenleo.springboot_mall.dao.MyOrderDao;
import com.kenleo.springboot_mall.dao.ProductDao;
import com.kenleo.springboot_mall.dao.UserDao;
import com.kenleo.springboot_mall.dto.BuyItem;
import com.kenleo.springboot_mall.dto.CreateOrderRequest;
import com.kenleo.springboot_mall.model.MyOrder;
import com.kenleo.springboot_mall.model.OrderItem;
import com.kenleo.springboot_mall.model.Product;
import com.kenleo.springboot_mall.model.User;
import com.kenleo.springboot_mall.service.MyOrderService;

@Component
public class MyOrderServiceImpl implements MyOrderService {

	private final static Logger log = LoggerFactory.getLogger(MyOrderServiceImpl.class);

	@Autowired
	MyOrderDao myOrderDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

		// 檢查user存在
		User user = userDao.getUserById(userId);
		if (user == null) {
			log.warn("該 userId {} 不存在", userId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		int totalAmount = 0;
		List<OrderItem> orderItemList = new ArrayList<>();

		for (BuyItem item : createOrderRequest.getBuyItemList()) {

			Product product = productDao.getProductById(item.getProductId());

			// 檢查是否存在
			if (product == null) {
				log.warn("商品 {} 不存在", item.getProductId());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			} else if (product.getStock() < item.getQuantity()) {
				log.warn("商品 {} 庫存數量不足，無法購買。 剩餘庫存 {}，欲購買數量 {}", item.getProductId(), product.getStock(),
						item.getQuantity());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}

			productDao.updateStock(product.getProductId(), product.getStock()-item.getQuantity());
			
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

	@Override
	public MyOrder getOrderById(Integer orderId) {

		MyOrder order = myOrderDao.getOrderById(orderId);

		List<OrderItem> orderItemList = myOrderDao.getOrderItemsByOrderId(orderId);

		order.setOrderItemList(orderItemList);

		return order;
	}

}

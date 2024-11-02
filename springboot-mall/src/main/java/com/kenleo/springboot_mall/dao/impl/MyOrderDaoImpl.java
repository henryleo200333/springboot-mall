package com.kenleo.springboot_mall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.dao.MyOrderDao;
import com.kenleo.springboot_mall.model.MyOrder;
import com.kenleo.springboot_mall.model.OrderItem;
import com.kenleo.springboot_mall.rowmapper.MyOrderRowMapper;
import com.kenleo.springboot_mall.rowmapper.OrderItemRowMapper;

@Component
public class MyOrderDaoImpl implements MyOrderDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer createOrder(Integer userId, Integer totalAmount) {

		String sql = "INSERT INTO myorder(user_id, total_amount, created_date, last_modified_date) "
				+ "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";

		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("totalAmount", totalAmount);

		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

		int orderId = keyHolder.getKey().intValue();
		return orderId;
	}

	@Override
	public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {

		String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount)"
				+ "VALUES (:orderId, :productId, :quantity, :amount)";

		MapSqlParameterSource[] maps = new MapSqlParameterSource[orderItemList.size()];

		for (int i = 0; i < orderItemList.size(); i++) {

			OrderItem orderItem = orderItemList.get(i);

			maps[i] = new MapSqlParameterSource();
			maps[i].addValue("orderId", orderId);
			maps[i].addValue("productId", orderItem.getProductId());
			maps[i].addValue("quantity", orderItem.getQuantity());
			maps[i].addValue("amount", orderItem.getAmount());
		}

		namedParameterJdbcTemplate.batchUpdate(sql, maps);
	}

	@Override
	public MyOrder getOrderById(Integer orderId) {

		String sql = "select * from myorder where order_id = :orderId";

		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);

		List<MyOrder> orderList = namedParameterJdbcTemplate.query(sql, map, new MyOrderRowMapper());

		if (orderList.isEmpty()) {
			return null;
		}
		return orderList.get(0);
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {

		String sql = "select * from order_item as oi " + "LEFT JOIN product as p on oi.product_id = p.product_id"
				+ " where oi.order_id = :orderId";

		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);

		
		List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
		
		return orderItemList;
		
	}

}

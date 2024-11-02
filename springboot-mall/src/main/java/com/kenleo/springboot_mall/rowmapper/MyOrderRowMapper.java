package com.kenleo.springboot_mall.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kenleo.springboot_mall.model.MyOrder;

public class MyOrderRowMapper implements RowMapper<MyOrder> {

	@Override
	public MyOrder mapRow(ResultSet rs, int rowNum) throws SQLException {

		MyOrder order = new MyOrder();

		order.setOrderId(rs.getInt("order_id"));
		order.setUserId(rs.getInt("user_id"));
		order.setTotalAmount(rs.getInt("total_amount"));
		order.setCreatedDate(rs.getTimestamp("created_date"));
		order.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

		return order;

	}

}

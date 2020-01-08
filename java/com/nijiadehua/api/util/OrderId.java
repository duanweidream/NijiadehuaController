package com.nijiadehua.api.util;

import java.util.Date;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;

public class OrderId {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	private static OrderId instance = null;

	public static OrderId getInstance() {
		if (instance == null) {
			instance = new OrderId();
		}
		return instance;
	}

	public String getOrderId() {
		String order_id = "";
		try {
			Sql sql = new Sql(" insert into art_order_ids (create_time) values (?) ");
			sql.addParam(new Date());
			Long id = jdbcTemplate.saveObject(sql);
			if (id != null) {
				order_id = UtilFunction.getOrderNo(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return order_id;
	}

	public static void main(String[] args) {
		System.out.println(OrderId.getInstance().getOrderId());
	}
	
}

package com.nijiadehua.api.service;

import java.util.List;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.Sales;

/**
 * ClassName:FlowerInfoService</br> Function: 文章基本信息处理 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-04-16 上午10:09:34</br>
 * 
 */
public class HomeTest {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public List<Sales> queryHomeSalesList() throws ApiError{
		Sql sql = new Sql(" select a.sales_id,c.sort_code,c.sort_short_name,c.sort_long_name,a.sales_name,a.subtitle sales_title,a.sales_price,a.mkt_price,a.sales_img ");
		sql.append(" from goods_sales_info a,goods_sales_sort b,goods_sort_sales c ");
		sql.append(" where a.sales_id = b.sales_id and b.sort_id = c.id ");
		sql.append(" order by a.modify_time desc ");
		List<Sales> salesList = jdbcTemplate.queryForList(sql, Sales.class);
		return salesList;
	}
	
}

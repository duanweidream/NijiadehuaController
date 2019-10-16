package com.hualouhui.weixin.service;

import com.hualouhui.weixin.base.db.JdbcTemplate;
import com.hualouhui.weixin.base.db.Sql;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.model.Sales;

/**
 * ClassName:SalesService</br> Function: 销售品处理 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-04-16 上午10:09:34</br>
 * 
 */
public class SalesService {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public void searchSalesForPage(Page page,String sort_id) throws ApiError{
		Sql sql = new Sql(" select a.sales_id,c.sort_code,c.sort_short_name,c.sort_long_name,a.sales_name,a.subtitle sales_title,a.sales_price,a.mkt_price,a.sales_img ");
		sql.append(" from goods_sales_info a,goods_sales_sort b,goods_sort_sales c ");
		sql.append(" where a.sales_id = b.sales_id and b.sort_id = c.id ");
		sql.append(" order by a.modify_time desc ");
		jdbcTemplate.search(page, sql,Sales.class);
	}
	
	public Sales findSalesBySalesId(String sales_id) throws ApiError{
		Sql sql = new Sql(" select a.sales_id,a.sales_name,a.subtitle sales_title,a.sales_price,a.mkt_price,a.sales_img ");
		sql.append(" from goods_sales_info a ");
		sql.append(" where a.sales_id = ? ");
		sql.append(" order by a.modify_time desc ");
		sql.addParam(sales_id);
		return jdbcTemplate.findObject(sql,Sales.class);
	}
}

package com.nijiadehua.api.service;

import java.util.List;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.controller.v1.home.response.HomeFocusResponse;
import com.nijiadehua.api.controller.v1.home.response.HomeListResponse;
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
public class HomeService {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public List<HomeFocusResponse> queryLocationListByLocationType(Long location_type) throws ApiError{
		Sql sql = new Sql(" select location_id,location_name,img_url,land_url,location_desc from art_location where location_type = ? ");
		sql.addParam(location_type);
		sql.append(" order by location_sort asc ");
		List<HomeFocusResponse> salesList = jdbcTemplate.queryForList(sql, HomeFocusResponse.class);
		return salesList;
	}
	
	public List<HomeListResponse> queryHomeList() throws ApiError{
		Sql sql = new Sql(" select a.sales_id,c.sort_code,c.sort_short_name,c.sort_long_name,a.sales_name,a.sales_title,e.artist_name art_name,a.sales_price,a.mkt_price,d.img_url sales_img ");
		sql.append(" from art_sales_info a,art_sales_sort b,art_sort c,art_sales_img d,artist_info e ");
		sql.append(" where a.sales_id = b.sales_id and b.sort_id = c.id and a.sales_id = d.sales_id and a.artist_id = e.id and d.img_sort = 1 and a.sales_status = 1 and a.sales_home = 1 ");
		sql.append(" order by a.modify_time desc ");
		return jdbcTemplate.queryForList(sql,HomeListResponse.class);
	}
	

	
}

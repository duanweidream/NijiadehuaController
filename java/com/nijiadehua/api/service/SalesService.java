package com.nijiadehua.api.service;

import java.util.ArrayList;
import java.util.List;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.controller.v1.sales.response.DetailResponse;
import com.nijiadehua.api.controller.v1.sales.response.SalesAttrResponse;
import com.nijiadehua.api.controller.v1.sales.response.SalesAttrResponse;
import com.nijiadehua.api.controller.v1.sales.response.SalesValueResponse;
import com.nijiadehua.api.controller.v1.sales.response.SearchResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.util.StringUtil;

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
		Sql sql = new Sql(" select a.sales_id,c.sort_code,c.sort_short_name,c.sort_long_name,a.sales_name,e.artist_name art_name,a.sales_price,a.mkt_price,d.img_url sales_img ");
		sql.append(" from art_sales_info a,art_sales_sort b,art_sort c,art_sales_img d,artist_info e ");
		sql.append(" where a.sales_id = b.sales_id and b.sort_id = c.id and a.sales_id = d.sales_id and a.artist_id = e.id and d.img_sort = 1 and a.sales_status = 1 and a.sales_home = 1 ");
		sql.append(" order by a.modify_time desc ");
		jdbcTemplate.search(page, sql,SearchResponse.class);
	}
	
	public DetailResponse findSalesBySalesId(String sales_id) throws ApiError{
		Sql sql = new Sql(" select a.sales_id,c.sort_code,c.sort_short_name,c.sort_long_name,a.sales_name,a.title sales_title,d.artist_name art_name,a.sales_price,a.mkt_price  ");
		sql.append(" from art_sales_info a,art_sales_sort b,art_sort c ,artist_info d ");
		sql.append(" where a.sales_id = b.sales_id and b.sort_id = c.id and a.sales_id = b.sales_id and a.artist_id = d.id and a.sales_status = 1 and a.sales_id = ? ");
		sql.append(" order by a.modify_time desc ");
		sql.addParam(sales_id);
		return jdbcTemplate.findObject(sql,DetailResponse.class);
	}
	
	public List<String> findSalesImgBySalesId(String sales_id) throws ApiError{
		
		List<String> result = new ArrayList<String>();
		
		Sql sql = new Sql(" select img_url from art_sales_img where sales_id = ? ");
		sql.append(" order by img_sort desc ");
		sql.addParam(sales_id);
		Object[] array = jdbcTemplate.findForArray(sql);
		if(array != null) {
			for(Object o : array) {
				if(!StringUtil.isEmpty(o)) {
					result.add(o.toString());
				}
			}
		}
		
		return result;
		
	}
	
	
	public List<SalesAttrResponse> findSalesAttrBySalesId(String sales_id) throws ServiceException{
		try {
			Sql sql = new Sql(" SELECT c.attr_id,c.attr_name ");
			sql.append(" FROM art_sales_info a,art_prod_info b,art_attribute c  ");
			sql.append(" where a.product_id = b.product_id and b.sort_id = c.attr_sort and a.sales_id = ? ");
			sql.addParam(sales_id);
			List<SalesAttrResponse> attrList = jdbcTemplate.queryForList(sql,SalesAttrResponse.class);
			if(attrList == null || attrList.size() == 0) {
				throw new ServiceException("查询销售品SKU属性错误");
			}
			
			for(SalesAttrResponse attr : attrList) {
				
				Sql valueSql = new Sql(" SELECT id value_id,attr_value value_name from art_attribute_value where attr_id = ? ");
				valueSql.addParam(attr.getAttr_id());
				
				List<SalesValueResponse> valueList = jdbcTemplate.queryForList(valueSql,SalesValueResponse.class);
				if(valueList == null || valueList.size() == 0) {
					throw new ServiceException("查询销售品SKU属性值错误");
				}
				attr.setValues(valueList);
				
			}
			
			return attrList;
		}catch (Exception e) {
			throw new ServiceException("查询销售品SKU属性错误："+e.getMessage());
		}
		
	}
	
	public int findSalesStockBySalesId(String sales_id,String value_id) throws ServiceException{
		try {
			
			Sql sql = new Sql(" select ifnull(sum(c.sku_stock),0) as stock from art_sales_info a,art_prod_info b,art_prod_sku c,art_prod_sku_item d ");
			sql.append(" where a.product_id = b.product_id and b.product_id = c.product_id and c.sku_id = d.sku_id and a.sales_id = ? and d.attr_value_id = ?  ");
			sql.addParam(sales_id,value_id);
			int result = jdbcTemplate.findInteger(sql);
			
			return result;
		}catch (Exception e) {
			throw new ServiceException("查询销售品库存错误："+e.getMessage());
		}
		
	}
	
}

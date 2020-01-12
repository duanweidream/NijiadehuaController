package com.nijiadehua.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.system.util.statement.Statement;
import com.nijiadehua.api.dao.base.BaseDao;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.ArtOrderGoods;

@Repository
public class OrderDao extends BaseDao {

	private static JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * 
	 * @param salesId
	 * @return
	 * @throws ApiError
	 */
	public Object[] querySalesProdInfoBySalesId(Long salesId) throws ApiError {

		Sql sql = new Sql(
				" select a.sales_id,a.product_id,a.sales_name,a.sales_title,a.sales_price,a.mkt_price,d.img_url sales_img ");
		sql.append(" from art_sales_info a,art_sales_sort b,art_sort c,art_sales_img d ");
		sql.append(
				" where a.sales_id = b.sales_id and b.sort_id = c.id and a.sales_id = d.sales_id and d.img_sort = 1 and a.sales_status = 1 ");
		sql.append(" and a.sales_id = ? ");
		sql.addParam(salesId);
		return jdbcTemplate.findForArray(sql);

	}

	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * 
	 * @param salesId
	 * @return
	 */
	public Object[] querySkuSpecInfoBySkuCode(String skuCode) {
		Statement stms = stmsFactory
				.createNativeStatement(" select sku_id,sku_name from art_prod_sku where sku_code = ? ");
		stms.addParam(skuCode);
		return (Object[]) hibernateDao.find(stms);
	}

	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * 
	 * @param order_id
	 * @return
	 * @throws ApiError
	 */
	public List<ArtOrderGoods> queryOrderGoodsByOrderId(String order_id) throws ApiError {

		Sql sql = new Sql(" select * from art_order_goods where order_id = ? ");
		sql.addParam(order_id);
		return jdbcTemplate.queryForList(sql, ArtOrderGoods.class);

	}
	
	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * 
	 * @param order_id
	 * @return
	 * @throws ApiError
	 */
	public int queryProdStockBySkuId(Long sku_id) throws Exception {
		try {
			Sql sku = new Sql(" select ifnull(sum(sku_stock),0) as stock from art_prod_sku where sku_id = ? ");
			sku.addParam(sku_id);
			return jdbcTemplate.findInteger(sku);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	
}

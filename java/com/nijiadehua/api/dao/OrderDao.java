package com.nijiadehua.api.dao;

import org.springframework.stereotype.Repository;

import com.nijiadehua.api.base.system.util.statement.Statement;
import com.nijiadehua.api.dao.base.BaseDao;

@Repository
public class OrderDao extends BaseDao{
	
	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * @param salesId
	 * @return
	 */
	public Object[] querySalesProdInfoBySalesId(Long salesId){
		Statement stms = stmsFactory.createNativeStatement(" select a.sales_id,a.product_id,a.sales_name,b.product_name,a.sales_price,a.mkt_price,a.sales_img,b.ava_stock,b.product_spec from goods_sales_info a,goods_product_info b where a.product_id = b.product_id ");
		stms.append("and", "a.sales_id", "=", salesId);
		
		return (Object[])hibernateDao.find(stms);
	}
	
	
	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * @param salesId
	 * @return
	 */
	public Object[] querySkuSpecInfoBySkuCode(String skuCode){
		Statement stms = stmsFactory.createNativeStatement(" select a.sales_id,a.product_id,a.sales_name,b.product_name,a.sales_price,a.mkt_price,a.sales_img,b.ava_stock,b.product_spec from goods_sales_info a,goods_product_info b where a.product_id = b.product_id ");
		stms.append("and", "a.sales_id", "=", skuCode);
		
		return (Object[])hibernateDao.find(stms);
	}
	
	
}

package com.nijiadehua.api.dao;

import org.springframework.stereotype.Repository;

import com.nijiadehua.api.base.system.util.statement.Statement;
import com.nijiadehua.api.dao.base.BaseDao;

@Repository
public class OrderDao extends BaseDao {

	/**
	 * 根据销售品id，查询产品、销售品、库存信息
	 * 
	 * @param salesId
	 * @return
	 */
	public Object[] querySalesProdInfoBySalesId(Long salesId) {
		Statement stms = stmsFactory.createNativeStatement(
				" select a.sales_id,a.product_id,a.sales_name,a.title,a.sales_price,a.mkt_price,d.img_url sales_img ");
		stms.append(" from art_sales_info a,art_sales_sort b,art_sort c,art_sales_img d ");
		stms.append(
				" where a.sales_id = b.sales_id and b.sort_id = c.id and a.sales_id = d.sales_id and d.img_sort = 1 ");
		stms.append(" and a.sales_id = ? ");
		stms.addParam(salesId);

		return (Object[]) hibernateDao.find(stms);
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

}

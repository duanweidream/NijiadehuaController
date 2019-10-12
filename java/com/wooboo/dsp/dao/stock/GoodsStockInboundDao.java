package com.wooboo.dsp.dao.stock;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.GoodsStockInbound;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class GoodsStockInboundDao extends BaseDao{

	public void searchGoodsStockInbound(GoodsStockInbound goodsStockInbound,Page page){
		Statement stms = stmsFactory.createNativeStatement(" select a.id,a.product_id,b.product_name,a.total_stock,a.pre_stock,a.rel_stock,a.ava_stock,a.in_stock,a.valid,a.modifyor,a.modify_time "); 
		          stms.append(" from goods_stock_inbound a left join goods_product_info b on a.product_id = b.product_id where 1 = 1 "); 
		
		if(goodsStockInbound != null){
			if(goodsStockInbound.getProductInfo() != null){
				stms.append("and", "a.product_id", "=",goodsStockInbound.getProductInfo().getProduct_id());
				stms.appendLike(null, "and", "b.product_name", goodsStockInbound.getProductInfo().getProduct_name());
			}
		}
		stms.addOrderBy(" a.modify_time desc ");
        hibernateDao.search(stms, page);         
	}
	
}

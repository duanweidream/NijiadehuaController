package com.wooboo.dsp.dao.stock;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.GoodsStockOutbound;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class GoodsStockOutboundDao extends BaseDao{

	public void searchGoodsStockOutbound(GoodsStockOutbound goodsStockOutbound,Page page){
		Statement stms = stmsFactory.createNativeStatement(" select a.id,a.product_id,b.product_name,a.total_stock,a.pre_stock,a.rel_stock,a.ava_stock,a.out_stock,a.valid,a.modifyor,a.modify_time "); 
		          stms.append(" from goods_stock_outbound a left join goods_product_info b on a.product_id = b.product_id where 1 = 1 "); 
		
		if(goodsStockOutbound != null){
			if(goodsStockOutbound.getProductInfo() != null){
				stms.append("and", "a.product_id", "=",goodsStockOutbound.getProductInfo().getProduct_id());
				stms.appendLike(null, "and", "b.product_name", goodsStockOutbound.getProductInfo().getProduct_name());
			}
		}
		stms.addOrderBy(" a.modify_time desc ");
        hibernateDao.search(stms, page);         
	}
	
}

package com.wooboo.dsp.dao.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.GoodsProductInfo;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class GoodsProductDao extends BaseDao{

	public void searchGoodsProductInfo(GoodsProductInfo goodsProductInfo,Page page){
		Statement stms = stmsFactory.createStatement(" from GoodsProductInfo  where 1 = 1 ");
		
		if(goodsProductInfo != null){
			stms.append("and", "product_id", "=",goodsProductInfo.getProduct_id());
			stms.appendLike(null, "and", "product_name", goodsProductInfo.getProduct_name());
		}
		stms.addOrderBy(" id asc ");
        hibernateDao.search(stms, page);         
	}
	
	public List<GoodsProductInfo> queryGoodsProductInfo(){
		Statement stms = stmsFactory.createStatement(" from GoodsProductInfo ");
		stms.addOrderBy(" id asc ");
        return hibernateDao.query(stms);         
	}
	
}

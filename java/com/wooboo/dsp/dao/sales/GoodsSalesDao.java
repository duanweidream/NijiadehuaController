package com.wooboo.dsp.dao.sales;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.GoodsSalesInfo;
import com.wooboo.dsp.model.GoodsSalesSort;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class GoodsSalesDao extends BaseDao{
	
	public void searchGoodsSalesInfo(GoodsSalesInfo goodsSalesInfo,Page page){
		Statement stms = stmsFactory.createStatement(" from GoodsSalesInfo  where 1 = 1 ");
		
		if(goodsSalesInfo != null){
			stms.append("and", "sales_id", "=",goodsSalesInfo.getSales_id());
			stms.appendLike(null, "and", "sales_name", goodsSalesInfo.getSales_name());
		}
		
		stms.addOrderBy(" modify_time desc ");
        hibernateDao.search(stms, page);         
	}
	
	public void deleteGoodsSalesSort(Long sales_id){
		Statement stms = stmsFactory.createStatement(" delete from GoodsSalesSort where sales_id = ? ");
		stms.addParam(sales_id);
        hibernateDao.update(stms);         
	}
	
	public List<Long> queryGoodsSalesSort(Long sales_id){
		Statement stms = stmsFactory.createNativeStatement(" select sort_id from goods_sales_sort where sales_id = ? ");
		stms.addParam(sales_id);
		
        return hibernateDao.query(stms);         
	}
	
}

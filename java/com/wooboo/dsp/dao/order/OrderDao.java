package com.wooboo.dsp.dao.order;

import org.springframework.stereotype.Repository;
import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.OrderInfo;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class OrderDao extends BaseDao{
	
	public void searchOrderInfo(OrderInfo orderInfo,Page page){
		Statement stms = stmsFactory.createStatement(" from OrderInfo  where 1 = 1 ");
		
		if(orderInfo != null){
			stms.append("and", "order_no", "=",orderInfo.getOrder_no());
		}
		
		stms.addOrderBy(" order_create_time desc ");
        hibernateDao.search(stms, page);         
	}

	
}

package com.wooboo.dsp.dao.V1;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.Sales;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class HomeDao extends BaseDao{

	public List<Sales> home(){
		Statement stms = stmsFactory.createNativeStatement(" select sales_id,sales_name from goods_sales_info ");
		stms.addOrderBy(" modify_time asc ");
        return hibernateDao.query(stms);         
	}
	
}

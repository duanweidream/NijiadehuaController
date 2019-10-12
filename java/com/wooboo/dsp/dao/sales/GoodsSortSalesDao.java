package com.wooboo.dsp.dao.sales;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.GoodsSortSales;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class GoodsSortSalesDao extends BaseDao{

	public List<GoodsSortSales> queryRootSort(){
        Statement stms = stmsFactory.createStatement("from GoodsSortSales m  where m.pId is null");
                  stms.addOrderBy(" m.sortSeq asc");
        return hibernateDao.query(stms);
	}
	
	public List<GoodsSortSales> querySortByPid(Long pid){
        Statement stms = stmsFactory.createStatement("from GoodsSortSales m  where m.pId  = ? ");
        stms.addParam(pid);
                  stms.addOrderBy(" m.sortSeq asc");
        return hibernateDao.query(stms);
	}
	
	
}

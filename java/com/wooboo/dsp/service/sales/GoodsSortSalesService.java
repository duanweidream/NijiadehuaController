package com.wooboo.dsp.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.dao.sales.GoodsSortSalesDao;
import com.wooboo.dsp.model.GoodsSortSales;
import com.wooboo.dsp.service.base.BaseService;

@Service
public class GoodsSortSalesService extends BaseService{
	
	@Autowired
	private GoodsSortSalesDao goodsSortDao;
	
	public List<GoodsSortSales> queryRootSort(){
		return goodsSortDao.queryRootSort();
	}
	
	public void saveGoodsSort(GoodsSortSales m){
		m.setVisitility(ATTSystem.LOGIC_TRUE.equals(m.getVisitility())?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
		goodsSortDao.saveObject(m);
	}
	
	public void updateGoodsSort(GoodsSortSales menu,GoodsSortSales newM){
		menu.setSortName(newM.getSortName());
		menu.setSortDesc(newM.getSortDesc());
		menu.setSortSeq(newM.getSortSeq());
		menu.setVisitility(ATTSystem.LOGIC_TRUE.equals(newM.getVisitility())?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
		goodsSortDao.updateObject(menu);
	}

	public List<GoodsSortSales> querySortByPid(Long pid){
		return goodsSortDao.querySortByPid(pid);
	}
	
}

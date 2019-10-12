package com.wooboo.dsp.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.dao.sales.GoodsSalesDao;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.GoodsSalesInfo;
import com.wooboo.dsp.model.GoodsSalesSort;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.Page.Page;

@Service
public class GoodsSalesService extends BaseService{
	
	private static Logger log = Logger.getLogger(GoodsSalesService.class);
	
	@Autowired
	private GoodsSalesDao goodsSalesDao;
	
	public void searchGoodsSalesInfo(GoodsSalesInfo goodsSalesInfo,Page page){
		goodsSalesDao.searchGoodsSalesInfo(goodsSalesInfo, page);
	}
	
	public void saveGoodsSalesInfo(GoodsSalesInfo goodsSalesInfo,Long[] sales_sort) throws ServiceException{
		try{
			goodsSalesDao.saveObject(goodsSalesInfo);
			
			Long sales_id = goodsSalesInfo.getSales_id();
			
			for(Long sort : sales_sort){
				
				GoodsSalesSort goodsSalesSort = new GoodsSalesSort();
				goodsSalesSort.setSales_id(sales_id);
				goodsSalesSort.setSort_id(sort);
				
				goodsSalesDao.saveObject(goodsSalesSort);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			log.logError("新增商品失败",e);
			throw new ServiceException("新增商品失败");
		}
		
	}
	
	public GoodsSalesInfo getGoodsSalesInfo(Long id){
		
		return goodsSalesDao.getObject(id, GoodsSalesInfo.class);
		
	}
	
	public void modifyGoodsSalesInfo(GoodsSalesInfo goodsSalesInfo,Long[] sales_sort){
		try{
			goodsSalesDao.updateObject(goodsSalesInfo);
			
			Long sales_id = goodsSalesInfo.getSales_id();
			
			goodsSalesDao.deleteGoodsSalesSort(sales_id);
			for(Long sort : sales_sort){
				
				GoodsSalesSort goodsSalesSort = new GoodsSalesSort();
				goodsSalesSort.setSales_id(sales_id);
				goodsSalesSort.setSort_id(sort);
				
				goodsSalesDao.saveObject(goodsSalesSort);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			log.logError("修改商品失败",e);
			throw new ServiceException("修改商品失败");
		}
		
	}

	public void removeGoodsSalesInfo(Long id){
		
		try{
			GoodsSalesInfo goodsSalesInfo = goodsSalesDao.getObject(id, GoodsSalesInfo.class);
			goodsSalesDao.deleteObject(goodsSalesInfo);
			goodsSalesDao.deleteGoodsSalesSort(goodsSalesInfo.getSales_id());
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("删除商品失败",e);
			throw new ServiceException("删除商品失败");
		}
		
	}
	
	public void onOffGoodsSalesInfo(Long id,Long sales_status){
		
		try{
			GoodsSalesInfo goodsSalesInfo = goodsSalesDao.getObject(id, GoodsSalesInfo.class);
			
			goodsSalesInfo.setSales_status(sales_status);
			goodsSalesDao.updateObject(goodsSalesInfo);
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("开启关闭商品失败",e);
			throw new ServiceException("开启关闭商品失败");
		}
		
	}
	
	public List<Long> queryGoodsSalesSort(Long sales_id){
		return goodsSalesDao.queryGoodsSalesSort(sales_id);
	}
	
	
}

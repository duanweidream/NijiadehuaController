package com.wooboo.dsp.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.dao.order.OrderDao;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.ArtistInfo;
import com.wooboo.dsp.model.OrderInfo;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.Page.Page;

@Service
public class OrderService extends BaseService{
	
	private static Logger log = Logger.getLogger(OrderService.class);
	
	@Autowired
	private OrderDao artistDao;
	
	public void searchOrderInfo(OrderInfo orderInfo,Page page){
		artistDao.searchOrderInfo(orderInfo, page);
	}
	
	
	public void saveArtistInfo(ArtistInfo artistInfo) throws ServiceException{
		try{
			
			artistDao.saveObject(artistInfo);
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("新增艺术家失败",e);
			throw new ServiceException("新增艺术家失败");
		}
		
	}
	
	public ArtistInfo getArtistInfo(Long id){
		
		return artistDao.getObject(id, ArtistInfo.class);
		
	}
	
	public void modifyArtistInfo(ArtistInfo artistInfo){
		try{
			
			artistDao.updateObject(artistInfo);
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("修改艺术家失败",e);
			throw new ServiceException("修改艺术家失败");
		}
		
	}

	public void removeArtistInfo(Long id){
		
		try{
			
			ArtistInfo artistInfo = artistDao.getObject(id, ArtistInfo.class);
			artistDao.deleteObject(artistInfo);
			
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("删除艺术家失败",e);
			throw new ServiceException("删除艺术家失败");
		}
		
		
		
	}
	
	
}

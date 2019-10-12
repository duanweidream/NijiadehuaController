package com.wooboo.dsp.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.dao.stock.GoodsProductDao;
import com.wooboo.dsp.dao.stock.GoodsStockInboundDao;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.GoodsProductInfo;
import com.wooboo.dsp.model.GoodsStockInbound;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.util.NumberUtil;

@Service
public class GoodsStockInboundService extends BaseService {
	
	private static Logger log = Logger.getLogger(GoodsStockInboundService.class);
	
	@Autowired
	private GoodsStockInboundDao goodsStockInboundDao;
	
	@Autowired
	private GoodsProductDao goodsProductDao;
	
	public void searchGoodsProductInfo(GoodsStockInbound goodsStockInbound, Page page) throws ServiceException{
		goodsStockInboundDao.searchGoodsStockInbound(goodsStockInbound, page);
	}

	public void saveGoodsStockInbound(GoodsStockInbound goodsStockInbound) throws ServiceException{
		
		try{
			
			GoodsProductInfo goodsProductInfo = goodsProductDao.getObject(goodsStockInbound.getProductInfo().getProduct_id(),GoodsProductInfo.class);
			goodsProductInfo.setTotal_stock(NumberUtil.add(goodsProductInfo.getTotal_stock(),goodsStockInbound.getIn_stock()));
			goodsProductInfo.setPre_stock(goodsProductInfo.getPre_stock());
			goodsProductInfo.setRel_stock(goodsProductInfo.getRel_stock());
			goodsProductInfo.setAva_stock(NumberUtil.add(goodsProductInfo.getAva_stock(),goodsStockInbound.getIn_stock()));
			goodsProductInfo.setModifyor(goodsStockInbound.getModifyor());
			goodsProductInfo.setModify_time(goodsStockInbound.getModify_time());
			goodsProductDao.updateObject(goodsProductInfo);
			
			goodsStockInbound.setTotal_stock(goodsProductInfo.getTotal_stock());
			goodsStockInbound.setPre_stock(goodsProductInfo.getPre_stock());
			goodsStockInbound.setRel_stock(goodsProductInfo.getRel_stock());
			goodsStockInbound.setAva_stock(goodsProductInfo.getAva_stock());
			goodsStockInboundDao.saveObject(goodsStockInbound);
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("新增库存失败",e);
			throw new ServiceException("新增库存失败");
		}
		
	}

	public void invalidGoodsStockInbound(GoodsStockInbound goodsStockInbound) throws ServiceException{
		
		try{
			
			GoodsProductInfo goodsProductInfo = goodsProductDao.getObject(goodsStockInbound.getProductInfo().getProduct_id(),GoodsProductInfo.class);
			goodsProductInfo.setTotal_stock(goodsProductInfo.getTotal_stock() - goodsStockInbound.getIn_stock());
			goodsProductInfo.setPre_stock(goodsProductInfo.getPre_stock());
			goodsProductInfo.setRel_stock(goodsProductInfo.getRel_stock());
			goodsProductInfo.setAva_stock(goodsProductInfo.getAva_stock() - goodsStockInbound.getIn_stock());
			goodsProductInfo.setModifyor(goodsStockInbound.getModifyor());
			goodsProductInfo.setModify_time(goodsStockInbound.getModify_time());
			goodsProductDao.updateObject(goodsProductInfo);
			
			goodsStockInbound.setTotal_stock(goodsProductInfo.getTotal_stock());
			goodsStockInbound.setPre_stock(goodsProductInfo.getPre_stock());
			goodsStockInbound.setRel_stock(goodsProductInfo.getRel_stock());
			goodsStockInbound.setAva_stock(goodsProductInfo.getAva_stock());
			goodsStockInbound.setValid(0L);
			goodsStockInboundDao.updateObject(goodsStockInbound);
			
		}catch(Exception e){
			e.printStackTrace();
			log.logError("置为无效失败",e);
			throw new ServiceException("置为无效失败");
		}
		

	}
	
}

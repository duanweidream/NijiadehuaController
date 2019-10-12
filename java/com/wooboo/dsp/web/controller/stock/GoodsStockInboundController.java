package com.wooboo.dsp.web.controller.stock;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.GoodsProductInfo;
import com.wooboo.dsp.model.GoodsStockInbound;
import com.wooboo.dsp.service.stock.GoodsStockInboundService;
import com.wooboo.dsp.system.util.ApiError;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;

@Controller
@RequestMapping(value="/stock")
public class GoodsStockInboundController{

	@Autowired
	private GoodsStockInboundService goodsStockInboundService;
	
	/**
	 * 币种资料->币种列表
	 *
	 * @return
	 */
	@RequestMapping(value="/inbound/search")
    public ModelAndView search(GoodsStockInbound goodsStockInbound){
		ModelAndView model = new ModelAndView("/stock/search_inbound");
		Page page = PagerFactory.createPage();
		goodsStockInboundService.searchGoodsProductInfo(goodsStockInbound, page);
		model.addObject("goodsStockInbound",goodsStockInbound);
		model.addObject(ATTPage.PAGE_LIST,page);
    	return model;
    }
	
	/**
	 * 币种资料->新建币种
	 *
	 * @return
	 */
	@RequestMapping(value="/inbound/add/to")
    public ModelAndView toAdd(){
		ModelAndView model = new ModelAndView("/stock/add_inbound");
    	return model;
    }
	
	/**
	 * 币种资料->保存币种
	 *
	 * @return
	 */
	@RequestMapping(value="/inbound/add/su")
	@ResponseBody
    public Result addSu(GoodsStockInbound goodsStockInbound,Long product_id){
		
		try{
			Date currentTime = new Date();
			Long uid = SessionHelper.getUserId();
			
			GoodsProductInfo goodsProductInfo = new GoodsProductInfo();
			goodsProductInfo.setProduct_id(product_id);
			goodsStockInbound.setProductInfo(goodsProductInfo);
			
			goodsStockInbound.setValid(1L);
			goodsStockInbound.setCreate_time(currentTime);
			goodsStockInbound.setCreator(uid);
			goodsStockInbound.setModifyor(uid);
			goodsStockInbound.setModify_time(currentTime);
			
			goodsStockInboundService.saveGoodsStockInbound(goodsStockInbound);
		}catch(ServiceException e){
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e.getMessage()));
		}
		return new Result();
		
    }
	
	/**
	 * 币种资料->删除币种
	 *
	 * @return
	 */
	@RequestMapping(value="/inbound/invalid")
	@ResponseBody
    public Result invalid(Long id){
		
		try{
			Date currentTime = new Date();
			Long uid = SessionHelper.getUserId();
			
			GoodsStockInbound goodsStockInbound = goodsStockInboundService.getObject(id,GoodsStockInbound.class);
			goodsStockInbound.setModifyor(uid);
			goodsStockInbound.setModify_time(currentTime);
			
			goodsStockInboundService.invalidGoodsStockInbound(goodsStockInbound);
		}catch(Exception e){
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e.getMessage()));
		}
		return new Result();
		
    }
	
	
	
	/**
	 * 币种资料->删除币种
	 *
	 * @return
	 */
	@RequestMapping(value="/inbound/detail/to/{id}")
    public ModelAndView detail(@PathVariable(value = "id") Long id){
		ModelAndView model = new ModelAndView("/stock/detail_inbound");
		
		GoodsStockInbound goodsStockInbound = goodsStockInboundService.getObject(id, GoodsStockInbound.class);
		
		model.addObject("goodsStockInbound", goodsStockInbound);
		return model;
		
    }
	
	
	
}

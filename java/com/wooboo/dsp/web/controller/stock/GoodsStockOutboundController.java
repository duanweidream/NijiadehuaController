package com.wooboo.dsp.web.controller.stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.model.GoodsStockInbound;
import com.wooboo.dsp.model.GoodsStockOutbound;
import com.wooboo.dsp.service.stock.GoodsStockOutboundService;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;

@Controller
@RequestMapping(value="/stock")
public class GoodsStockOutboundController{
		
	@Autowired
	private GoodsStockOutboundService goodsStockOutboundService;
	
	/**
	 * 币种资料->币种列表
	 *
	 * @return
	 */
	@RequestMapping(value="/outbound/search")
    public ModelAndView search(GoodsStockOutbound goodsStockOutbound){
		ModelAndView model = new ModelAndView("/stock/search_outbound");
		Page page = PagerFactory.createPage();
		goodsStockOutboundService.searchGoodsStockOutbound(goodsStockOutbound, page);
		model.addObject("goodsStockOutbound",goodsStockOutbound);
		model.addObject(ATTPage.PAGE_LIST,page);
    	return model;
    }
	
	/**
	 * 币种资料->删除币种
	 *
	 * @return
	 */
	@RequestMapping(value="/outbound/detail/to/{id}")
    public ModelAndView detail(@PathVariable(value = "id") Long id){
		ModelAndView model = new ModelAndView("/stock/detail_outbound");
		
		GoodsStockOutbound goodsStockOutbound = goodsStockOutboundService.getObject(id, GoodsStockOutbound.class);
		
		model.addObject("goodsStockOutbound", goodsStockOutbound);
		return model;
		
    }
	
	
}

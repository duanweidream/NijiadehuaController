package com.wooboo.dsp.web.controller.stock;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.model.GoodsProductInfo;
import com.wooboo.dsp.service.stock.GoodsProductService;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;

@Controller
@RequestMapping(value="/stock")
public class GoodsProductController   {
		
	@Autowired
	private GoodsProductService goodsProductService;
	
	/**
	 * 币种资料->币种列表
	 *
	 * @return
	 */
	@RequestMapping(value="/product/search")
    public ModelAndView search(GoodsProductInfo goodsProductInfo){
		ModelAndView model = new ModelAndView("/stock/search_product");
		Page page = PagerFactory.createPage();
		goodsProductService.searchGoodsProductInfo(goodsProductInfo, page);
		model.addObject("goodsProductInfo",goodsProductInfo);
		model.addObject(ATTPage.PAGE_LIST,page);
    	return model;
    }
	
	/**
	 * 币种资料->新建币种
	 *
	 * @return
	 */
	@RequestMapping(value="/product/add/to")
    public ModelAndView toAdd(){
		ModelAndView model = new ModelAndView("/stock/add_product");
    	return model;
    }
	
	/**
	 * 币种资料->保存币种
	 *
	 * @return
	 */
	@RequestMapping(value="/product/add/su")
	@ResponseBody
    public Result addSu(GoodsProductInfo goodsProductInfo){
		Date currentTime = new Date();
		Long uid = SessionHelper.getUserId();
		
		goodsProductInfo.setCreate_time(currentTime);
		goodsProductInfo.setCreator(uid);
		goodsProductInfo.setModifyor(uid);
		goodsProductInfo.setModify_time(currentTime);
		
		goodsProductService.saveGoodsProductInfo(goodsProductInfo);
		
		return new Result();
		
    }
	
	
	/**
	 * 币种资料->修改币种
	 *
	 * @return
	 */
	@RequestMapping(value="/product/modify/to")
    public ModelAndView toModify(Long id){
		ModelAndView model = new ModelAndView("/stock/modify_product");
		
		GoodsProductInfo goodsProductInfo = goodsProductService.getGoodsProductInfo(id);
		
		model.addObject("goodsProductInfo",goodsProductInfo);
    	return model;
    }
	
	/**
	 * 币种资料->修改保存
	 *
	 * @return
	 */
	@RequestMapping(value="/product/modify/su")
	@ResponseBody
    public Result modifySu(GoodsProductInfo goodsProductInfo){
		Date currentTime = new Date();
		Long uid = SessionHelper.getUserId();
		
		
		goodsProductInfo.setModifyor(uid);
		goodsProductInfo.setModify_time(currentTime);
		
		goodsProductService.modifyGoodsProductInfo(goodsProductInfo);
		
		return new Result();
		
    }
	
	/**
	 * 币种资料->删除币种
	 *
	 * @return
	 */
	@RequestMapping(value="/product/remove/su")
	@ResponseBody
    public Result remove(Long id){
		
		goodsProductService.removeGoodsProductInfo(id);
		
		return new Result();
		
    }
	
}

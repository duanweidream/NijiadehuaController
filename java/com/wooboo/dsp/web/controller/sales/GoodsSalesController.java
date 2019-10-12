package com.wooboo.dsp.web.controller.sales;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.GoodsSalesInfo;
import com.wooboo.dsp.service.sales.GoodsSalesService;
import com.wooboo.dsp.service.sales.GoodsSortSalesService;
import com.wooboo.dsp.system.util.ApiError;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;

@Controller
@RequestMapping(value = "/goods")
public class GoodsSalesController {

	@Autowired
	private GoodsSalesService goodsSalesService;

	@Autowired
	private GoodsSortSalesService goodsSortSalesService;

	/**
	 * 币种资料->币种列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/search")
	public ModelAndView search(GoodsSalesInfo goodsSalesInfo) {
		ModelAndView model = new ModelAndView("/sales/search_sales");
		Page page = PagerFactory.createPage();
		goodsSalesService.searchGoodsSalesInfo(goodsSalesInfo, page);
		model.addObject("goodsSalesInfo", goodsSalesInfo);
		model.addObject(ATTPage.PAGE_LIST, page);
		return model;
	}

	/**
	 * 币种资料->新建币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/add/to")
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/sales/add_sales");

		model.addObject("salesSort", goodsSortSalesService.querySortByPid(2L));
		return model;
	}

	/**
	 * 币种资料->保存币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/add/su")
	@ResponseBody
	public Result addSu(GoodsSalesInfo goodsSalesInfo, Long[] sales_sort) {

		try {

			Date currentTime = new Date();
			Long uid = SessionHelper.getUserId();
			
			goodsSalesInfo.setSales_status(0L);
			goodsSalesInfo.setCreate_time(currentTime);
			goodsSalesInfo.setCreator(uid);
			goodsSalesInfo.setModifyor(uid);
			goodsSalesInfo.setModify_time(currentTime);

			goodsSalesService.saveGoodsSalesInfo(goodsSalesInfo, sales_sort);

		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}

	/**
	 * 币种资料->修改币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/modify/to")
	public ModelAndView toModify(Long sales_id) {
		ModelAndView model = new ModelAndView("/sales/modify_sales");
		
		GoodsSalesInfo goodsSalesInfo = goodsSalesService.getGoodsSalesInfo(sales_id);
		String sales_img = goodsSalesInfo.getSales_img();
		
		model.addObject("sales_img",sales_img.split(","));
		model.addObject("goodsSalesInfo", goodsSalesInfo);
		model.addObject("salesSort", goodsSortSalesService.querySortByPid(2L));
		model.addObject("goodsSalesSort", goodsSalesService.queryGoodsSalesSort(sales_id));
		return model;
	}

	/**
	 * 币种资料->修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/modify/su")
	@ResponseBody
	public Result modifySu(GoodsSalesInfo goodsSalesInfo,Long[] sales_sort) {
		try {
			Date currentTime = new Date();
			Long uid = SessionHelper.getUserId();

			goodsSalesInfo.setModifyor(uid);
			goodsSalesInfo.setModify_time(currentTime);

			goodsSalesService.modifyGoodsSalesInfo(goodsSalesInfo,sales_sort);

		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}

	/**
	 * 币种资料->删除币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/remove/su")
	@ResponseBody
	public Result remove(Long sales_id) {
		try {
			goodsSalesService.removeGoodsSalesInfo(sales_id);
		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}
	
	/**
	 * 币种资料->删除币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales/onOff")
	@ResponseBody
	public Result onOff(Long sales_id,Long sales_status) {
		try {
			goodsSalesService.onOffGoodsSalesInfo(sales_id,sales_status);
		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}
	
}

package com.wooboo.dsp.web.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.model.ArtistInfo;
import com.wooboo.dsp.model.OrderInfo;
import com.wooboo.dsp.service.order.OrderService;
import com.wooboo.dsp.service.report.ReportService;
import com.wooboo.dsp.system.util.ChartsUtil;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;
import com.wooboo.dsp.util.NumberUtil;
import com.wooboo.dsp.util.StringUtil;
import com.wooboo.dsp.web.controller.report.ReportController;

@Controller
@RequestMapping(value="/order")
public class OrderController {

	
	private static Logger logger = Logger.getLogger(OrderController.class);
	
	
	@Autowired
	private OrderService orderService;

	/**
	 * 币种资料->币种列表
	 *
	 * @return
	 */
	@RequestMapping(value="/search")
    public ModelAndView search(OrderInfo orderInfo){
		ModelAndView model = new ModelAndView("/order/search_order");
		Page page = PagerFactory.createPage();
		orderService.searchOrderInfo(orderInfo, page);
		model.addObject("orderInfo",orderInfo);
		model.addObject(ATTPage.PAGE_LIST,page);
    	return model;
    }


}

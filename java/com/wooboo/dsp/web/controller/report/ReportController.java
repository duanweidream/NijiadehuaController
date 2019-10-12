package com.wooboo.dsp.web.controller.report;


import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.enums.WoobooEnums;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.service.report.ReportService;
import com.wooboo.dsp.system.util.ChartsUtil;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;
import com.wooboo.dsp.util.NumberUtil;
import com.wooboo.dsp.util.StringUtil;
import com.wooboo.dsp.util.UtilFunction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/report")
public class ReportController{
	
	private static Logger logger = Logger.getLogger(ReportController.class);
	
	
	@Autowired
	private ReportService reportService;

	@ResponseBody
	@RequestMapping(value="/dashboard/data")
	public Result dashboard(HttpServletResponse response){
		Result result = new Result();
		JSONObject data = new JSONObject();
		Date beginDate = DateUtil.getFistDayOfMonth();
		//List<Object[]>  list = reportService.queryReportUserDate(beginDate, new Date());
		List<Object[]>  list = new ArrayList<Object[]>();
		Object[] d1 = new Object[4];
		d1[0] = "2019-09-01";
		d1[1] = "5";
		d1[2] = "3";
		d1[3] = "0.3";
		list.add(d1);
		
		Object[] d2 = new Object[4];
		d2[0] = "2019-09-02";
		d2[1] = "2";
		d2[2] = "1";
		d2[3] = "0.5";
		list.add(d2);
		
		Object[] d3 = new Object[4];
		d3[0] = "2019-09-03";
		d3[1] = "7";
		d3[2] = "5";
		d3[3] = "0.8";
		list.add(d3);
		
	
		
		
		JSONObject legend = ChartsUtil.legend(new String[]{"本月新增订单","本月支付订单","本月成交率"});
		
		List<String> dateList = new ArrayList<>();
		List<Integer> showList = new ArrayList<>();
		List<Integer> clickList = new ArrayList<>();
		List<Double> moneyList = new ArrayList<>();
		for(Object[] objs:list){
			//Date date = (Date)objs[0];
			//dateList.add(DateUtil.getFormatDate(date, "yyyy-MM-dd"));
			dateList.add(objs[0].toString());
			showList.add(NumberUtil.parseInteger(StringUtil.dealNull(objs[1]),0));
			clickList.add(NumberUtil.parseInteger(StringUtil.dealNull(objs[2]),0));
			moneyList.add(NumberUtil.getDouble(StringUtil.dealNull(objs[3]), 0d));
		}
		
		JSONArray series = new JSONArray();
		series.add(ChartsUtil.series("本月新增订单", "line", "总量", showList));
		series.add(ChartsUtil.series("本月支付订单", "line", "总量", clickList));
		series.add(ChartsUtil.series("本月成交率", "line", "总量", moneyList));
		
		
		JSONObject xAxis = ChartsUtil.xAxis(dateList);
		
		
		data.put("legend", legend);
		data.put("xAxis", xAxis);
		data.put("series", series);
		
		result.setData(data);
		return result;
	}

}

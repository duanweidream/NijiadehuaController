package com.wooboo.dsp.web.controller.sales;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.model.GoodsSortSales;
import com.wooboo.dsp.service.sales.GoodsSortSalesService;
import com.wooboo.dsp.util.StringUtil;

@Controller
@RequestMapping(value="/goods/sort/sales")
public class GoodsSortSalesController {
	
	@Autowired
	private GoodsSortSalesService goodsSortService;
	
	/**
     * 系统设置->菜单管理
     * @param request
     * @return
     */
    @RequestMapping("/list")  
    public ModelAndView menuList(HttpServletRequest request){  
    	ModelAndView model = new ModelAndView("/sales/sort_list");
    	List<GoodsSortSales> list = goodsSortService.queryRootSort();
    	model.addObject("clist", list);
    	return model;
    }  
    
    /**
     * 系统设置->菜单管理->添加菜单
     * @param menuId 菜单id 如传则是修改菜单.
     * @return
     */
    @RequestMapping("/to")  
    public ModelAndView menuto(Long sortId,Long pid){  
    	ModelAndView model = new ModelAndView("/sales/sort_to");
    	if(!StringUtil.isEmpty(sortId)){
    		GoodsSortSales menu = goodsSortService.getObject(sortId, GoodsSortSales.class);
    		model.addObject("menu", menu);
    	}else{
    		model.addObject("pid", pid);
    	}
    	return model;
    }
    /**
     * 系统设置->菜单管理->菜单添加|更新
     * @param menuId
     * @return
     */
    @ResponseBody
    @RequestMapping("/su")  
    public Result menusu(GoodsSortSales menu,Long sortId){  
    	Result result = new Result();
    	GoodsSortSales m = null;
    	if(!StringUtil.isEmpty(menu.getId())){
    		m = goodsSortService.getObject(menu.getId(), GoodsSortSales.class);
    	}
    	if(null==m){
    		goodsSortService.saveGoodsSort(menu);
    	}else{
    		goodsSortService.updateGoodsSort(m, menu);
    	}
    	return result;
    }
    
}

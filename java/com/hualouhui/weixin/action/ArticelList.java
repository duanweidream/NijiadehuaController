package com.hualouhui.weixin.action;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.FlowerImg;
import com.hualouhui.weixin.model.FlowerInfo;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.service.FlowerInfoService;
import com.hualouhui.weixin.util.Parameters;
/**
 * ClassName:Publish</br> Function: 热文阅读 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-6-10 上午10:09:34</br>
 * 
 */
public class ArticelList extends ActionBase {
	
	private static Logger log = Logger.getLogger(ArticelList.class);
	
	public String sort_id;//分类
	public Page page;
	
	public ArticelList() {
		needCheckAuth = false;
	}

	@Override
	public void initParameters(Parameters params) throws ApiError {
		sort_id = params.getFieldValue("sort_id");
        page = newPage(params);
	}

	@Override
	public Result invokeService() throws ApiError {

		Result result = new Result();
		FlowerInfoService flowerInfoService = new FlowerInfoService();
		
		flowerInfoService.searchFlowerInfoForPage(page, sort_id);
		List<FlowerImg> imgList = flowerInfoService.searchFlowerImgList();
		
		List<FlowerInfo> listTrip = (List<FlowerInfo>)page.getList();
		
		for(FlowerInfo flowerInfo : listTrip){
			
			List<FlowerImg> child = new ArrayList<FlowerImg>();
			for(FlowerImg img : imgList){
				
				if(img.getFlowerId().longValue() == flowerInfo.getId().longValue()){
					child.add(img);
				}
			}
			flowerInfo.setInfo_imgs(child);
		}
		
		
		JSONObject data = new JSONObject();
		data.put("list", JSONArray.fromObject(listTrip).toString());
		setPageInfo(page, data);
		result.put("data", data);
		return result;
	
	}
	


	
}

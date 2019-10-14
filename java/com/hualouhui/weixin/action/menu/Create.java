package com.hualouhui.weixin.action.menu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.base.wechat.WeChatApi;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.WxMenu;
import com.hualouhui.weixin.util.Config;
import com.hualouhui.weixin.util.Parameters;

/**
 * @author  luoyouhua 2016-04-13
 * 微信菜单创建
 * */
public class Create extends ActionBase{
	public static String token=Config.getValue("weixin.api.token");
	public String signature,timestamp,nonce,echostr;
	public String encrypt_type,msg_signature;
	public HttpServletRequest request;
	
	public Create(){
		 needCheckAuth =false;
	}
	
	@Override
	public void initParameters(Parameters params) throws ApiError {	
		
	}
	@Override
	public Result invokeService() throws ApiError {
		Result result = new Result();
		JSONObject menuJson=new JSONObject();
	    List<WxMenu> list = getWxMenu();
	    JSONArray array = new JSONArray();
	    for(WxMenu menu:list){
	    	array.add(menuToJson(menu));
	    }
	    menuJson.put("button", array);
	    JSONObject o = WeChatApi.menuCreate(menuJson);
	    result.setRet(o);
		return result;
	}
	public  JSONObject menuToJson(WxMenu menu){
		JSONObject o = new JSONObject();
		o.put("type", menu.type);
		o.put("name", menu.name);
		if("click".equals(menu.type)){
			o.put("key",menu.key);
		}else{
			o.put("url", menu.url);
		}
		if(null!=menu.sub_button&&menu.sub_button.size()>0){
			JSONArray array = new JSONArray();
			for(WxMenu sub_menu:menu.sub_button){
				array.add(menuToJson(sub_menu));
			}
			o.put("sub_button", array);
		}
		return o;
	}
	
	public  List<WxMenu> getWxMenu(){
		List<WxMenu> list = new ArrayList<>();
		WxMenu menu0 = new WxMenu("view", "信息发布", Config.getValue("server.name")+"/toPublish.do", "xinxifabu");
		
		WxMenu menu1 = new WxMenu("view", "信息查看", Config.getValue("server.name")+"/publishList.do", "xinxichakan");
		
		
		WxMenu menu2 = new WxMenu("click", "热文阅读", null, "guanyuwom");
		
		List<WxMenu> sub_list2 = new ArrayList<>();
		sub_list2.add(new WxMenu("view", "热文阅读",Config.getValue("server.name")+"/pages/article.list.html","articleread"));
		sub_list2.add(new WxMenu("view", "关于我们", Config.getValue("server.name")+"/pages/about.us.html", "aboutus"));
		sub_list2.add(new WxMenu("view", "广告发布",Config.getValue("server.name")+"/pages/ad.publish.html","guanggaofabu"));
		

		menu2.setSub_button(sub_list2);

		list.add(menu0);
		list.add(menu1);
		list.add(menu2);
		return list;
	}


	
	
	
	
	
}

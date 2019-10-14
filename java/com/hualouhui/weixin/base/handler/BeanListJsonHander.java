package com.hualouhui.weixin.base.handler;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class BeanListJsonHander<T> {
	private final Class<T> type;
	private final JsonRowProcessor convents;
	public BeanListJsonHander(Class<T> type){
		this.type=type;
		this.convents=new BeanJsonProcessor();
	}
	public List<T> handle(JSONArray array){
	    return this.convents.toBeanList(array, type);
	}
	public <T> T handle(JSONObject obj,Class<T> type){
	    return convents.toBean(obj, type);
	}


}

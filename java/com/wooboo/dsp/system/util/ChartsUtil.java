package com.wooboo.dsp.system.util;

import java.util.List;

import com.wooboo.dsp.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChartsUtil {

	
	public static JSONObject legend(String[] lengends){
		JSONObject o = new JSONObject();
		JSONArray array = new JSONArray();
		for(String s:lengends){
			array.add(s);
		}
		o.put("data",array);
		return o;
	}
	
	public static JSONObject xAxis(List<String> list){
		JSONObject o = new JSONObject();
		JSONArray array = new JSONArray();
		for(String s:list){
			array.add(s);
		}
		o.put("data",array);
		return o;
	}
	
	public static JSONObject series(String name,String type,String stack,List<?> list){
		JSONObject o = new JSONObject();
		o.put("name", name);
		o.put("type", type);
		o.put("stack", stack);
		JSONArray array = new JSONArray();
		for(Object d:list){
			array.add(StringUtil.dealNull(d));
		}
		o.put("data",array);
		return o;
	}
	
	
}

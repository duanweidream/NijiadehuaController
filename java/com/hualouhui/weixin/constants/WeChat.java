package com.hualouhui.weixin.constants;

import java.util.HashMap;
import java.util.Map;


/**
 * 微信接口常量设置
 * */
public class WeChat {
	public static enum Source{meituan,jd,taobao}
	/**
	 * 1:关注/取消关注  subscribe|unsubscribe
	 * 2:扫描带参数二维码 subscribe|SCAN (未关注发送subscribe,已关注发送SCAN)
	 * 3:上报地理位置 LOCATION
	 * 4:自定义菜单事件 CLICK
	 * 5:点击菜单拉去消息事件
	 * 6:点击菜单跳转链接事件
	 * */
	public static enum Event {
		subscribe("subscribe","订阅"),
		unsubscribe("unsubscribe","取消订阅"),
		click("CLICK","菜单点击"),
		text("text","消息");
        private String status,name;
        public static final Map<String, Event> statusMap = new HashMap<String, Event>();
		static {
			Event[] statu = Event.class.getEnumConstants();
		    for(Event s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
        private Event(String status,String name) {
            this.status = status;
            this.name=name;
        }
        public String toString(){
        	return this.status;
        }
        public String toName(){
        	return this.name;
        }
        public static Event fromValue(String value) {
		    return statusMap.get(value);
	    }
        public static String valueToName(String value){
        	Event s = Event.fromValue(value);
  		  return null==s?value:s.toName();
  	    }
	}
		
	
	public static void main(String[] args){
		
		
	}
}

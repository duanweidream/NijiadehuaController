package com.nijiadehua.api.constants;

import java.util.HashMap;
import java.util.Map;


public class Status {
	public static enum AdState{publish,limit}
	public static enum Campaign {
        news("news","新建"),
        publish("publish","发布"),
        delete("delete","删除"),
        done("done","完成");
        private String status,name;
        public static final Map<String, Campaign> statusMap = new HashMap<String, Campaign>();
		static {
			Campaign[] statu = Campaign.class.getEnumConstants();
		    for(Campaign s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
        private Campaign(String status,String name) {
            this.status = status;
            this.name=name;
        }
        public String toString(){
        	return this.status;
        }
        public String toName(){
        	return this.name;
        }
        public static Campaign fromValue(String value) {
		    return statusMap.get(value);
	    }
        public static String valueToName(String value){
        	Campaign s = Campaign.fromValue(value);
  		  return null==s?value:s.toName();
  	    }
    }
	
	//高德 poi code
	public static enum PoiCode {
        canying("05","餐饮服务"),
        lvyou("11","旅游景点"),//110000
		kefang("10","客房预订"),
		gouwu("06","购物"),
        repair("03","汽车维修");
		
        
        private String status,name;
        public static final Map<String, PoiCode> statusMap = new HashMap<String, PoiCode>();
		static {
			PoiCode[] statu = PoiCode.class.getEnumConstants();
		    for(PoiCode s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
        private PoiCode(String status,String name) {
            this.status = status;
            this.name=name;
        }
        public String toString(){
        	return this.status;
        }
        public String toName(){
        	return this.name;
        }
        public static PoiCode fromValue(String value) {
		    return statusMap.get(value);
	    }
        public static String valueToName(String value){
        	PoiCode s = PoiCode.fromValue(value);
  		  return null==s?value:s.toName();
  	    }
    }
	
	
	public static void main(String[] args){
	}
}

package com.nijiadehua.api.constants;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {
	 textHtml("text/html",""),
     applicationJson("application/json",""),
     imageJpeg("image/jpge","");
     private String status,name;
     public static final Map<String, ContentType> statusMap = new HashMap<String, ContentType>();
		static {
			ContentType[] statu = ContentType.class.getEnumConstants();
		    for(ContentType s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
     private ContentType(String status,String name) {
         this.status = status;
         this.name=name;
     }
     public String toString(){
     	return this.status;
     }
     public String toName(){
     	return this.name;
     }
     public static ContentType fromValue(String value) {
		    return statusMap.get(value);
	    }
     public static String valueToName(String value){
    	 ContentType s = ContentType.fromValue(value);
		  return null==s?value:s.toName();
	    }
}

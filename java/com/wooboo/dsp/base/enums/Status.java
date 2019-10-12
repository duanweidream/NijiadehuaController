package com.wooboo.dsp.base.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wooboo.dsp.util.StringUtil;



public class Status {

	public static String options(String value,String type){
		StringBuffer sbuf=new StringBuffer();
		 if("process".equals(type)){
			System.out.println(ProcessType.valueToName("ado"));
	        for(Entry<String, ProcessType> en:ProcessType.statusMap.entrySet()){
		         sbuf.append("<option value=\""+en.getValue().status+"\" "+(StringUtil.equals(value, en.getValue().status)?"selected=\"selected\"":"")+">"+en.getValue().name+"</option>");	
		    }		    
		 }
		 return sbuf.toString();
	}
	public static String toName(String value,String type){
		if("process".equals(type)){
			return ProcessType.valueToName(value);
		}else if("adplan".equals(type)){
			return BillingType.valueToName(value);
		}
		return "";
	}

	
	  public enum ProcessType {
		    traffic("traffic","流量资源审核"),
		    company("company","广告主资质审核"),
		    wechat("wechat","微信推文数据确认"),
		    advertiser("advertiser","广告计划审核");
		    //finance("finance","财务资源审核");
		    private String status,name;
		    public static final Map<String, ProcessType> statusMap = new HashMap<String, ProcessType>();
			static {
				ProcessType[] statu = ProcessType.class.getEnumConstants();
			    for(ProcessType s:statu ) {
			    	statusMap.put(s.status.toString(), s);
			    }
		    }
		    private ProcessType(String status,String name) {
		        this.status = status;
		        this.name=name;
		    }
		    public String toString(){
		    	return this.status;
		    }
		    public String toName(){
		    	return this.name;
		    }
		    public static ProcessType fromValue(String value) {
			    return statusMap.get(value);
		    }
		    public static String valueToName(String value){
		    	ProcessType s = ProcessType.fromValue(value);
				  return null==s?value:s.toName();
			}
		}
	
	public static enum Currency {
        YUAN(1,"人民币"),
        OYUAN(2,"欧元"),
        MYUAN(3,"美元");
        private Integer status;
        private String name;
        public static final Map<String, Currency> statusMap = new HashMap<String, Currency>();
		static {
			Currency[] statu = Currency.class.getEnumConstants();
		    for(Currency s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
        private Currency(Integer status,String name) {
            this.status = status;
            this.name=name;
        }
        public Integer value(){
        	return this.status;
        }
        public String toString(){
        	return this.status.toString();
        }
        public String toName(){
        	return this.name;
        }
        public static Currency fromValue(String value) {
		    return statusMap.get(value);
	    }
        public static String valueToName(String value){
        	Currency s = Currency.fromValue(value);
  		  return null==s?value:s.toName();
  	    }
    }
	
	public static enum Order{
		unaudited(0,"未审核"),
        success(1,"审核成功"),
        fail(4,"审核失败");
        private Integer status;
        private String name;
        
        private Order(Integer status,String name) {
            this.status = status;
            this.name=name;
        }
        public Integer value(){
        	return this.status;
        }
        public String toString(){
        	return this.status.toString();
        }
        public String toName(){
        	return this.name;
        }
       
    }
	
	public static enum BillingType{
		cpc(2,"cpc"),
        cpm(1,"cpm");
        private Integer status;
        private String name;
        public static final Map<String, BillingType> statusMap = new HashMap<String, BillingType>();
		static {
			BillingType[] statu = BillingType.class.getEnumConstants();
		    for(BillingType s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
        private BillingType(Integer status,String name) {
            this.status = status;
            this.name=name;
        }
        public Integer value(){
        	return this.status;
        }
        public String toString(){
        	return this.status.toString();
        }
        public String toName(){
        	return this.name;
        }
        public static BillingType fromValue(String value) {
		    return statusMap.get(value);
	    }
        public static String valueToName(String value){
        	BillingType s = BillingType.fromValue(value);
  		  return null==s?value:s.toName();
  	    }
       
    }
	
	public static enum BusinessCode {
        charge("usercharge","用户充值"),
        adplan("adplan","广告计划"),
        adgroup("adgroup","广告组"),
        adidea("adidea","广告创意"),
		sysuser("sys_user","系统用户"),
		ideaOpenOff("ideaOpenOff","素材上下线");
        private String status;
        private String name;
        public static final Map<String, BusinessCode> statusMap = new HashMap<String, BusinessCode>();
		static {
			BusinessCode[] statu = BusinessCode.class.getEnumConstants();
		    for(BusinessCode s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		    }
	    }
        private BusinessCode(String status,String name) {
            this.status = status;
            this.name=name;
        }
        
        public String toString(){
        	return this.status.toString();
        }
        public String toName(){
        	return this.name;
        }
        public static BusinessCode fromValue(String value) {
		    return statusMap.get(value);
	    }
        public static String valueToName(String value){
        	BusinessCode s = BusinessCode.fromValue(value);
  		  return null==s?value:s.toName();
  	    }
    }
	
}

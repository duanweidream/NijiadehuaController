package com.wooboo.dsp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class NumberUtil {

	public static int getInt(Object value, int defVal) {
		if (value != null) {
			if (value instanceof Long) {
				return ((Long) value).intValue();
			}
			if (value instanceof Integer) {
				return (Integer) value;
			}
			if (value instanceof BigDecimal) {
				return ((BigDecimal) value).intValue();
			}
			if (value instanceof String) {
				return Integer.valueOf((String)value);
			}
			return Integer.valueOf(value.toString());
		}
		return defVal;
	}
	
	public static Integer getInteger(String val,Integer def){
		if(val!=null && !val.isEmpty()){
			 return Integer.valueOf(val);
		}
		return def;
	}
	public static Integer getInteger(String val){
		if(!StringUtil.isEmpty(val)){
			 return Integer.valueOf(val);
		}
		return null;
	}
	public static Double getDouble(Object val){
		return getDouble(StringUtil.dealNull(val));
	}
	public static Double getDouble(String val){
		return getDouble(val,null);
	}
	public static Double getDouble(String val,Double def){
		try{
			return Double.parseDouble(val);
		}catch(Exception e){
			return def;
		}
		
	}
	public static Long getLong(String val,Long def){
		try{
			return Long.parseLong(val);
		}catch(Exception e){
			return def;
		}
		
	}
	public static Integer parseInteger(String val,Integer def){
		try{
			return Integer.parseInt(val);
		}catch(Exception e){
			return def;
		}
	}
	
	public static String formatYuan(Double d){
		if(null==d){
			return "0.00";
		}
		DecimalFormat df=new DecimalFormat("#0.00"); 
		return df.format(d);
	}
	public static String formatNumber(Double d,String format){
		DecimalFormat df=new DecimalFormat( StringUtil.dealNull(format, "#0.00")); 
		return df.format(d);
	}
	
	public static String calculateRate(Integer val1,Integer val2){
		if(val1<=0){
			return formatNumber(0d, null);
		}
		Double rate = val1*100d/val2;
		
		//System.out.println(rate);
		
		
		return formatNumber(rate,null);
	}
	public static String calculateEcpm(Integer show,Double money){
		if(money<=0){
			return formatNumber(0d, null);
		}
		Double ecpm = money*1000d/show;
		return formatNumber(ecpm,null);
	}
	
	public static Long add(Long p1,Long p2){
		if(p1 == null){
			p1 = 0L;
		}
		if(p2 == null){
			p2 = 0L;
		}
		return p1 + p2;
	}
	
}

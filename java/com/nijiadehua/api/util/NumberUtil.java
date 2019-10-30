package com.nijiadehua.api.util;

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
	
	public static Long getLong(String val){
		if(!StringUtil.isEmpty(val)){
			 return Long.valueOf(val);
		}
		return null;
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
		DecimalFormat df=new DecimalFormat("#0.00"); 
		return df.format(d);
	}
	public static String formatNumber(Double d,String format){
		DecimalFormat df=new DecimalFormat( StringUtil.dealNull(format, "#0.00")); 
		return df.format(d);
	}
	
	
}

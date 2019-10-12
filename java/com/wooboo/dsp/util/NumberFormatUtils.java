package com.wooboo.dsp.util;

import java.text.DecimalFormat;

public class NumberFormatUtils {
	private static String TWODECIMALS = "0.00";
	private static String THREEDECIMALS = "0.000";
	private static String FOURDECIMALS = "0.0000";
	
	/**
	 * 保留两位小数，无值返回0.00
	 * @param obj
	 * @return
	 */
	public static String formatTwoDecimals(Object num){
		return formatByDefined(TWODECIMALS,num);
		
	}
	
	/**
	 * 保留三位小数，无值返回0.000
	 * @param num
	 * @return
	 */
	public static String formatThreeDecimals(Object num){
		return formatByDefined(THREEDECIMALS, num);
	}
	
	/**
	 * 保留四位小数，无值返回0.0000
	 * @param num
	 * @return
	 */
	public static String formatFourDecimals(Object num){
		return formatByDefined(FOURDECIMALS, num);
	}
	/**
	 * 根据 pattern，返回自定义格式化数据
	 * @param pattern
	 * @param num
	 * @return
	 */
	public static String formatByDefined(String pattern , Object num){
		return formatByDefined(pattern,num,null);
	}
	
	/**
	 * 根据 pattern，返回自定义格式化数据，definedResult定义无值时返回结果
	 * @param pattern
	 * @param num
	 * @param definedResult
	 * @return
	 */
	public static String formatByDefined(String pattern , Object num ,String definedResult){
		DecimalFormat df = new DecimalFormat(pattern);
		if(num != null && !"".equals(num.toString())){
			String result = "";
			double doubleNum = Double.parseDouble(num.toString());
			result = df.format(doubleNum);
			return result;
		}else{
			if(!"".equals(definedResult) && null != definedResult){
				return definedResult;
			}else{
				if(TWODECIMALS.equals(pattern)){
					return "0.00";
				}else if(THREEDECIMALS.equals(pattern)){
					return "0.000";
				}else if(FOURDECIMALS.equals(pattern)){
					return "0.0000";
				}else{
					return "0";
				}
			}
		}
	}
}

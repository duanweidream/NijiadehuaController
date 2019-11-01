package com.nijiadehua.api.util;

import java.util.Date;

/**
 * ClassName:UtilFunction</br> Function: 公用函数 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2016-9-14 上午10:09:34</br>
 * 
 */
public class UtilFunction {
	
	/**
	 * 返回图片的后缀格式
	 * @param suffix
	 * @return
	 */
	public static String getImgSuffix(String suffix){
		//List<String> setType = Arrays.asList("image/jpg","image/jpeg","image/png","image/gif");
		if(suffix.equalsIgnoreCase("image/jpg")){
			return "jpg";
		}else if(suffix.equalsIgnoreCase("image/jpeg")){
			return "jpg";
		}else if(suffix.equalsIgnoreCase("image/png")){
			return "png";
		}else if(suffix.equalsIgnoreCase("image/gif")){
			return "gif";
		}else{
			return null;
		}
	}
	
	
	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    private static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0     
        // num 代表长度为4     
        // d 代表参数为正数型 
        result = String.format("%0" + num + "d", Integer.parseInt(code));

        return result;
    }
	
    /**
     * 根据规则生成订单号
     * @param id
     * @return
     */
	public static String getOrderNo(Long id){
		String time = DateUtil.getFormatDate(new Date(),"yyyyMMddHHmm");
		String seq = autoGenericCode(id.toString(), 8);
		return time+seq;
	}
	
	public static void main(String [] args){
		String ss = getOrderNo(1L);
		System.out.println(ss);
	}
	
	
	
}

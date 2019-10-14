package com.hualouhui.weixin.util;
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
	
}

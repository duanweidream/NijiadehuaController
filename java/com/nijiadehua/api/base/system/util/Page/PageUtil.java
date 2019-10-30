package com.nijiadehua.api.base.system.util.Page;
import java.net.URLEncoder;

/**
 * 
 * @author luoyouhua
 *
 */
public class PageUtil {

	private static final String BLANK = "";
	public static boolean isEmpty(String s){
		return s == null || BLANK.compareTo(s) == 0;
    }
	public static boolean isEmpty(Object str) {
		return str == null || BLANK.equals(str);
	}

	public static int getInt(String str, int defaultVal){
		if(str!=null){
			try{
				return Integer.valueOf(str);
			}catch(Exception e){
				return defaultVal;
			}
		}
		return defaultVal;
	}
	public static String dealNull(String str) {
		return str != null ? str.trim() : BLANK;
	}
	public static String dealNull(Object obj) {
		String str = BLANK;
		if(obj!=null){
			if(obj instanceof String){
				str = (String)obj;
			}else{
				str = obj.toString();
			}
		}
		return str;
	}

	/**
	 * 
	 * <p>description:</p>
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String dealNull(Object str,String defaultValue){
		return str != null ? str.toString().trim() : defaultValue;
	}
	
	public static String dealNull(String str, String defaultVal) {
		return str != null ? str.trim() : defaultVal;
	}
	public static String encode(String str, final String encoding) {
		try {
			if (!PageUtil.isEmpty(str)) {
				str = URLEncoder.encode(str, encoding);
			}
		} catch (Exception e) {
		}
		return str;
	}
	public static boolean equals(Object value1, Object value2) {
		boolean is = false;
		if (value1 == value2) { // is null or self
			return is = true;
		}
		if (value1 != null && value2 != null) { // is not null;
			return value1.equals(value2);
		}
		return is;
	}
	

	public static void main(String[] args){
		//String s = "D:"+File.separator+"Tomcat 6.0"+File.separator+"market"+File.separator+"ROOT"+File.separator+"userfiles"+File.separator+"app"+File.separator+"382";
		//String m = "D:\\Tomcat 6.0\\market\\ROOT\\userfiles\\app";
		//File file = new File(s);
		String str = "?pgSize=20&pgFrom=10&titSize=117&cateCode=002003";
		System.out.println(str.replaceAll("pgFrom=\\d*", "pgFrom=30"));
	}
}

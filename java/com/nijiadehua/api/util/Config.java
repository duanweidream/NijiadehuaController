package com.nijiadehua.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nijiadehua.api.base.log.Logger;


public class Config{
    public static Logger logger = Logger.getLogger(Config.class);
    private static final String BLANK = "";
	public static Map<String, String> map = new HashMap<String, String>();
	public static Map<String, String> cmdMap = new HashMap<String, String>();
	public static boolean tag=false;
	public static List<String> source = null;
	public static Config config = new Config();
	static {
		source=new ArrayList<String>();
		source.add("/config");
	}
	public Config(){
       
	}
	public  void putInMap() throws FileNotFoundException, IOException{
		logger.logInfo("begin load properties===========================");
		for(String s:source){
			File fileProperties = new File(getClass().getResource(s).getPath());
			logger.logInfo("load dir:"+fileProperties.getName());
			if(fileProperties!=null && fileProperties.isDirectory()){
					File[] fileArray = fileProperties.listFiles();
					if(fileArray!=null){
						for(File f:fileArray){
							if(f.getPath().contains(".properties")){
								logger.logInfo("load properties file:"+f.getName());
								Properties properties = new Properties();
								properties.load(new FileInputStream(f));
								for(Entry e:properties.entrySet()){
									map.put(StringUtil.dealNull(e.getKey(),"null"), StringUtil.dealNull(e.getValue()));
								}
							}
					}
				}
			}
		}
        tag=true;
		logger.logInfo("load properties  done!===========================");
	}
	
	
	public static String getValue(String key){
		if(!config.tag){
			try {
				//config.putCmdInMap();
				config.putInMap();
			} catch (Exception e) {
				e.printStackTrace();
				logger.logError("load properties error:"+e.getMessage());
			} 
		}
		return map.get(key);
	}
	public static Integer getInteger(String key){
		return NumberUtil.getInteger(getValue(key));
	}
	public static Integer getInteger(String key,Integer baseScore){
		return NumberUtil.getInteger(getValue(key),baseScore);
	}
	public static Long getLong(String key,String defValue){
		return Long.parseLong(StringUtil.dealNull(getValue(key),defValue));
	}
	public static String getCmd(String action){
		if(!config.tag){
			try {
				config.putInMap();
			} catch (Exception e) {
				//e.printStackTrace();
				logger.logError("load properties error:"+e.getMessage());
			} 
		}
		return cmdMap.get(action);
	}
	public static boolean regex(String regex, String str) {
		boolean trueOrFalse;
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		trueOrFalse = m.matches();
		return trueOrFalse;
	}
	
	public static String dealNull(Object str,String defaultValue){
		return str != null ? str.toString().trim() : defaultValue;
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
}

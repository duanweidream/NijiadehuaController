package com.nijiadehua.api.base.ftp;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nijiadehua.api.util.Config;
import com.nijiadehua.api.util.StringUtil;

public class FolderManager {
    private static String baseFolder="/flower/";
	
    public static String getSysUserFolder(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	String timeFloder = dateFormat.format(new Date());
		return baseFolder+timeFloder+"/";
	}

    public synchronized static String getCurrentName(Integer userId,String fileName){
    	return userId+"_"+encodeNumStr(userId+""+new Date().getTime())+"."+StringUtil.getExtensionName(fileName);
    }
    public static int random(int n){
		if(n==0) return 0;
		Random random = new Random();
		return Math.abs(random.nextInt())%n;
	}
    public static String encodeNumStr(String value){
		if(value==null||value.length()==0) return "null";
		
		StringBuffer sBuf = new StringBuffer();
		char[] values = value.toCharArray();
		for (int i = 0; i < values.length; i++) {
			if (values[i] >= '0' && values[i] <= '9'&&i%3!=0) {
				int id = Integer.parseInt(String.valueOf(values[i]));
				char c = (char)(65+id+random(20));
				sBuf.append(c);
			}else{
				sBuf.append(values[i]);
			}
		}
		//替换非法字符
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(sBuf.toString());
		return matcher.replaceAll("");
	}
    public static String uploadFile(File file,String fileName){
    	if(file!=null && fileName!=null){
    		String path = FolderManager.getSysUserFolder();
    		String name=FolderManager.getCurrentName(1000, fileName);
    		String resPath = FileOperate.uploadFile(file, path, name);
        	return Config.getValue("ftp.url")+resPath;
    	}
    	return null;
    }

    public static String uploadFile(InputStream input,String filename){
    	if(input!=null && filename!=null){
    		String path = FolderManager.getSysUserFolder();
    		String name=FolderManager.getCurrentName(1000, filename);
    		String resPath = FileOperate.uploadFile(input, path, name);
        	return Config.getValue("ftp.url")+resPath;
    	}
    	return null;
    }

    
}

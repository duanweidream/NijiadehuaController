package com.hualouhui.weixin.action.base;

import java.util.HashMap;
import java.util.Map;

public class UriMeta {

	private static final String pack="com.hualouhui.weixin.action.";
	public String uri,className;
	public boolean rest=false;
	public boolean diando=false;
	public Map<String, String> map = new HashMap<String, String>();
	public UriMeta(String uri){
		this.uri=uri;
	}
	public UriMeta(){
		
	}
	public UriMeta parseUri(){
		String parseUri=null;
		if(uri.matches(".*/rest/1.0.*")){
			rest=true;
			parseUri = uri.replaceAll(".*/rest/1.0/", "");
		}else if(uri.endsWith(".do")){
			diando=true;
			parseUri = uri.replaceAll("\\.do", "").replaceFirst("^/", "");
		}
		parse(parseUri);
		return this;
	}

	public void parseRest(){
		StringBuffer sbuf = new StringBuffer(pack);
    	uri = uri.replaceAll(".*/rest/1.0/", "");
		String[] parts = uri.split("\\/");
		for(int i=0;i<parts.length;i++){
		   String part=parts[i];
		   if(i==parts.length-1){
			    String[] last=part.split(",");
				sbuf.append(Character.toUpperCase(last[0].charAt(0)));
				sbuf.append(last[0].substring(1));
				for(int n=1;n<last.length;n++){
					map.put("param_"+n, last[n]);
				}
		   }else{
			   sbuf.append(part+".");
		   }
		}
		this.className=sbuf.toString();
	}

	
	private void parse(String uri){
		StringBuffer sbuf = new StringBuffer(pack);
		
		String[] parts = uri.split("\\/");
		for(int i=0;i<parts.length;i++){
		   String part=parts[i];
		   if(i==parts.length-1){
			    String[] last=part.split(",");
				sbuf.append(Character.toUpperCase(last[0].charAt(0)));
				sbuf.append(last[0].substring(1));
				for(int n=1;n<last.length;n++){
					map.put("param_"+n, last[n]);
				}
		   }else{
			   sbuf.append(part+".");
		   }
		}
		this.className=sbuf.toString();
	}
	
	
	public String getParam(String key){
		return map.get(key);
	}
	public static UriMeta newUriMeta(String uri){
		return new UriMeta(uri).parseUri();
	}
	public static void main(String[] args){
		UriMeta um = UriMeta.newUriMeta("/Api1.0/rest/1.0/phone/code");
	    System.out.println(um.className);
		System.out.println(um.getParam("param_1")+"  =-  "+um.className);
	}
}

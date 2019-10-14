package com.hualouhui.weixin.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import com.hualouhui.weixin.model.UserInfo;


public class CookieHelper {

    public static final String FIELD_NAME = "userpass";
    
    //商超购物车
    public static final String COOKIE_SHOPPING_CART = "COOKIE_SHOPPING_CART";
    //餐厅购物车
    public static final String CUISINE_SHOPPING_CART = "CUISINE_SHOPPING_CART";
    //境外商超购物车
    public static final String OUTSIDE_SHOPPING_CART = "OUTSIDE_SHOPPING_CART";
    
    public static final String KEY = "dfyfhqs9";
    
    public static void removeCookie(){
    	
    }
    
    
    
    public static UserInfo getUserInfo(String cookie){
    	UserInfo m = new UserInfo();
    	if (cookie != null && !cookie.equals("")) {
    	    try{
        	    byte[] cookieBytes = ByteHelper.hex2byte(cookie);
                byte[] decodedBytes = decryptData(cookieBytes, KEY.getBytes());
            	String decodedString = new String(decodedBytes);
            	String[] parts = decodedString.split(",");
            	m.id=NumberUtil.getLong(parts[0]);
            	m.nickname=parts[1];
    	    }catch(Exception e){    		
    	    	e.printStackTrace();
    	    }
    	}
        return m;
    }
    
    public static String createCookie(UserInfo m){
    	String cookie = null;
    	try{
    		String content = m.id + ","+StringUtil.dealNull(m.nickname)+ "," + (System.currentTimeMillis() / 1000);
        	byte[] contentBytes = content.getBytes();
        	byte[] keyBytes = KEY.getBytes();
            byte[] cookieBytes = encryptData(contentBytes, keyBytes);
            cookie = ByteHelper.byte2hex(cookieBytes);
    	}catch(Exception e){    		
    	    e.printStackTrace();
    	}
        return cookie;
    } 
    
   

    public static byte[] encryptData(byte[] input, byte[] key) throws Exception { 
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, ALGORITHM); 
        Cipher c1 = Cipher.getInstance(ALGORITHM); 
        c1.init(Cipher.ENCRYPT_MODE, deskey); 
        byte[] cipherByte = c1.doFinal(input);   
        return cipherByte;  
    } 
    
    public static byte[] decryptData(byte[] input, byte[] key) throws Exception { 
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, ALGORITHM); 
        Cipher c1 = Cipher.getInstance(ALGORITHM); 
        c1.init(Cipher.DECRYPT_MODE, deskey); 
        byte[] clearByte = c1.doFinal(input);   
        return clearByte; 
    } 
    
    public static final String ALGORITHM = "DES";
    
    public static void main(String[] args) {
    	
    	//getUserInfo("0336D274951CE88ADE4896D4C313690AE2BC58FF7144DAFF53AA20C0F1CD82E5");
    	
    	/*
        Account account = new Account();
        account.accountId = 8;
        account.shopId = 5;
        String cookie = createCookie(account);
        System.out.println(cookie);
        account = getAccount("DE14C3B2C5281A40DD8CDBC3EFEFDA771A1382EF46E6280A");
        System.out.println(account.accountId);*/
        
    }
}

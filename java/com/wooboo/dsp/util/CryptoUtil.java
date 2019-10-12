package com.wooboo.dsp.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class CryptoUtil {

    public static final String KEY = "b2e3emdf";
   
    public static String decryptData(String src){
    	if (src != null) {
    	    try{
    	    	byte[] cookieBytes = ByteHelper.hex2byte(src);
                byte[] decodedBytes = decryptData(cookieBytes, KEY.getBytes());
            	String decodedString = new String(decodedBytes);
                return decodedString;
    	    }catch(Exception e){    		
    	    	e.printStackTrace();
    	    }
    	}
    	return null;
    }
    
    public static String encryptData(String strs){
    	String token = null;
    	try{
        	String content = strs;
        	byte[] contentBytes = content.getBytes();
        	byte[] keyBytes = KEY.getBytes();
            byte[] cookieBytes = encryptData(contentBytes, keyBytes);
            token = ByteHelper.byte2hex(cookieBytes);
    	}catch(Exception e){    		
    	    e.printStackTrace();
    	}
        return token;
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
        String src =  encryptData("40");
    	System.out.println(src);
        System.out.println(decryptData(src));
    }
}

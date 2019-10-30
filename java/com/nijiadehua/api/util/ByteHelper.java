package com.nijiadehua.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class ByteHelper {
	
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }  
    
    public static byte[] hex2byte(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }
    
    public static String calculateMd5(byte[] contentData) {
        byte[] digesta = new byte[0];  
        try {  
            MessageDigest alga = MessageDigest.getInstance("MD5");  
            alga.update(contentData);  
            digesta = alga.digest();  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  
        }  
        return byte2hex(digesta);
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
}

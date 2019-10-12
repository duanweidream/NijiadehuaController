package com.wooboo.dsp.util;

import com.wooboo.dsp.base.log.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
import org.apache.commons.codec.binary.Base64;

public class AESKit {
	  /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AESKit.class);
     
    public static final String IV = "0807060504030201";
    public static final String KEY = "abcdefgabcdefg12";
    
    
    public static String Encrypt(String sSrc) { 
    	try {
			return Encrypt(sSrc,KEY);
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
    }
    
    
    /*******************************************************************
     * AES加密算法
     * @author moyun
     * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定    此处使用AES-128-CBC加密模式，key需要为16位。
     * */
 
    //加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
 
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes();
         
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");   
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"   
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度   
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);   
        byte[] encrypted = cipher.doFinal(sSrc.getBytes()); 
         
        return Base64.encodeBase64String(encrypted);//此处使用BAES64做转码功能，同时能起到2次
    }
 
    public static String Decrypt(String sSrc) { 
    	try {
			return Decrypt(sSrc,KEY);
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
    }
     
    //解密
    public static String Decrypt(String sSrc, String sKey) throws Exception { 
         
        // 判断Key是否正确   
        if (sKey == null) {   
            System.out.print("Key为空null");   
            return null;   
        }   
        // 判断Key是否为16位   
        if (sKey.length() != 16) {   
            System.out.print("Key长度不是16位");   
            return null;   
        }   
        byte[] raw = sKey.getBytes("ASCII");
         
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");   
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");   
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());   
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);   
        byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用bAES64解密  
        try {   
            byte[] original = cipher.doFinal(encrypted1);   
            String originalString = new String(original);   
            return originalString;   
        } catch (Exception e) {   
            logger.info(e.toString());
            return null;   
        }   
    }
     
    public static void main(String[] args) throws Exception {
         
//        String pwd = "世界你好";
//        String epwd = Encrypt(pwd, "abcdefgabcdefg12");
//        System.out.println(epwd);
//        System.out.println(Decrypt("Ui7oTV2H2An+42PWtOHmww==", "abcdefgabcdefg12"));
    	
    	System.out.println(Decrypt("Ngtsmqugn1N+LaJTs6RVJBo03ZhAuJ2P/+aZjqcdClc="));
    	
    }
}

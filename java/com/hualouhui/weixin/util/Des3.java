package com.hualouhui.weixin.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import net.sf.json.JSONObject;



/**
 * 3DES加密工具
 */
public class Des3 {
	private final static String secretKey = "12kdieorlfor@lx100$#365#";
	// 向量
	private final static String iv = "76540123";

	public final static String iv_android = "kufgerk3";
	
	private final static String encoding = "utf-8";

	public static void main(String[] args) throws Exception {

		JSONObject o = new JSONObject();
		o.put("name", "章暴打");
	    o.put("haha", "asdfsdfd");
	    String s=o.toString();
	    String endes3 = Des3.encode(s);
	    System.out.println(endes3);
	    System.out.println(Des3.decode(endes3));
	    
	}

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普�?文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普�?文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:解密
	 * </p>
	 * 
	 * @param encryptText
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-4
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher
				.doFinal(Base64.decode(encryptText));

		return new String(decryptData, encoding);
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:解密文本
	 * </p>
	 * 
	 * @param encryptText
	 * @param b
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-4
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String encryptText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] decryptData = cipher
				.doFinal(Base64.decode(encryptText));
		return new String(decryptData, encoding);
	}
}

package com.nijiadehua.api.util;

public class IdBlur {
	
	public static String mapSting[] = { "ae", "w2", "n3", "1z", "5e", "81", "6R", "52", "38", "19", "ba", "b2", "bn", "bp", "BE", "BF", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B0", "Q1", "R1", "SK", "3T", "YU", "CV", "PW", "QX", "HY", "CZ", "TY", "TU", "SA", "SB", "SS", "UI", "VE", "ZR", "HR",
			"WO", "LA", "E8", "3K", "0R", "4L", "3N", "56", "98", "V9", "6M", "6U", "3J", "Y4", "67", "ML", "ZZ" };

	public static char mapChar[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z' };

	// 这个是用来加密的
	public static String getEncryption(String str) {
		char[] ch = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < mapChar.length; j++) {
				if (ch[i] == mapChar[j]) {
					sb.append(mapSting[j]);
				}
			}
		}
		return sb.toString();
	}

	// 这个是解密的
	public static String getDecryption(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i += 2) {
			for (int j = 0; j < mapSting.length; j++) {
				if (str.substring(i, i + 2).equals(mapSting[j])) {
					sb.append(mapChar[j]);
				}
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		String id = IdBlur.getEncryption("282");
		System.out.println(id+" = "+IdBlur.getDecryption(id));
	}
}

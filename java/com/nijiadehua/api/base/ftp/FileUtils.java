package com.nijiadehua.api.base.ftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 
 * 项目名称：qladmingit
 * @author hshuai
 * 创建时间：2016-10-13 下午6:56:49
 */
public class FileUtils {
	public static byte[] readFile(String fileFullPath) throws Exception {
		if (fileFullPath == null || (fileFullPath.trim()).length() == 0 || fileFullPath.equals("null")) {
			throw new NullPointerException("无效的文件路径");
		}
		File file = new File(fileFullPath);
		if (!file.exists()) {
			throw new NullPointerException("文件不存在");
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("读取文件不正确");
		bufferedInputStream.close();
		return bytes;
	}
}

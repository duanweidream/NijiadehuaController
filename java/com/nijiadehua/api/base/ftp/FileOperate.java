package com.nijiadehua.api.base.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.util.Config;
import com.nijiadehua.api.util.StringUtil;



/**
 * 功能描述:文件相关操作!
 * @author luo
 * @date Jul 20, 2010
 */
public class FileOperate {
	private static Logger logger = Logger.getLogger(FileOperate.class);
	private static Boolean mode = Boolean.valueOf(Config.getValue("ftp.mode")); 
	private static String LOCAL_PATH="";
	public FileOperate() {} 
	
	/** 
	* 新建目录 
	* 
	* @param folderPath 
	* String �?c:/fqf 
	* @return boolean 
	*/ 
	public static void newFolder(String folderPath) { 
		try { 
			
			String filePath = folderPath; 
			filePath = filePath.toString(); 
			java.io.File myFilePath = new java.io.File(filePath); 
			if (!myFilePath.exists()) { 
			//myFilePath.mkdir(); 
			logger.logInfo("create folder path:"+folderPath);
			myFilePath.mkdirs();
		   } 
		} catch (Exception e) { 
			logger.logInfo("新建目录操作出错"); 
			e.printStackTrace(); 
		} 
	} 
	
	/** 
	* 新建文件 
	* @param filePathAndName 
	* String 文件路径及名�?如c:/fqf.txt 
	* @param fileContent 
	* String 文件内容 
	* @return boolean 
	*/ 
	public void newFile(String filePathAndName, String fileContent) { 
		try { 
			String filePath = filePathAndName; 
			filePath = filePath.toString(); 
			File myFilePath = new File(filePath); 
			if (!myFilePath.exists()) { 
			   myFilePath.createNewFile(); 
			} 
			FileWriter resultFile = new FileWriter(myFilePath); 
			PrintWriter myFile = new PrintWriter(resultFile); 
			String strContent = fileContent; 
			myFile.println(strContent); 
			resultFile.close(); 
		
		} catch (Exception e) { 
			logger.logInfo("新建目录操作出错"); 
			e.printStackTrace(); 
		} 
	} 
	
	/** 
	* 删除文件 
	* 
	*/ 
	public static void delFile(String filePathAndName) { 
		logger.logInfo("delete file path:"+filePathAndName);
		if(mode){
			boolean b = deleteFileToFtp(filePathAndName);
			if(!b){
				delFileLocal(filePathAndName);
			}
		}else{
			delFileLocal(filePathAndName);
		}
	} 
	
	/** 
	* 删除文件 
	* 
	* @param filePathAndName 
	* String 文件路径及名�?如c:/fqf.txt 
	* @param fileContent 
	* String 
	* @return boolean 
	*/ 
	public static void delFileLocal(String filePathAndName){
		try {
			logger.logInfo("delete file path:"+filePathAndName);
			String filePath = filePathAndName; 
			filePath = filePath.toString(); 
			java.io.File myDelFile = new java.io.File(filePath); 
			if(myDelFile.exists()){
				myDelFile.delete(); 
			}
			} catch (Exception e) { 
				logger.logInfo("删除文件操作出错"); 
			e.printStackTrace(); 
			
			} 
	}
	
	
	/** 
	* 删除文件�?
	* 
	* @param filePathAndName 
	* String 文件夹路径及名称 如c:/fqf 
	* @param fileContent 
	* String 
	* @return boolean 
	*/ 
	public static void delFolder(String folderPath) {
	try { 
	delAllFile(folderPath); // 删除完里面所有内�?
	String filePath = folderPath; 
	filePath = filePath.toString(); 
	java.io.File myFilePath = new java.io.File(filePath); 
	logger.logInfo("delete dir file"+myFilePath.getAbsolutePath());
	myFilePath.delete(); // 删除空文件夹 
	
	} catch (Exception e) { 
	logger.logInfo("删除文件夹操作出"); 
	e.printStackTrace(); 
	
	} 
	
	} 
	
	/** 
	* 删除文件夹里面的�?��文件 
	* 
	* @param path 
	* String 文件夹路�?�?c:/fqf 
	*/ 
	public static void delAllFile(String path) { 
	    File file = new File(path); 
		if (!file.exists()) { 
		return; 
		} 
		if (!file.isDirectory()) { 
		return; 
		} 
		File[] list = file.listFiles();
	    for(File f:list){
	    	if(f.isDirectory()){
	    		delFolder(f.getAbsolutePath());
	    	}else{
	    		//logger.logInfo("delete file:"+f.getAbsolutePath());
	    		f.delete();
	    	}
	    }
	} 
	
	/** 
	* 复制单个文件 
	* 
	* @param oldPath 
	* String 原文件路�?如：c:/fqf.txt 
	* @param newPath 
	* String 复制后路�?如：f:/fqf.txt 
	* @return boolean 
	*/ 
	public static void copyFile(String oldPath, String newPath) { 
	try { 
	int bytesum = 0; 
	int byteread = 0; 
	File oldfile = new File(oldPath); 
	if (oldfile.exists()) { // 文件存在�?
	InputStream inStream = new FileInputStream(oldPath); // 读入原文�?
	FileOutputStream fs = new FileOutputStream(newPath); 
	byte[] buffer = new byte[1444]; 
	int length; 
	while ((byteread = inStream.read(buffer)) != -1) { 
	bytesum += byteread; // 字节�?文件大小 
	fs.write(buffer, 0, byteread); 
	} 
	inStream.close(); 
	} 
	} catch (Exception e) { 
    logger.logInfo("复制单个文件操作出错"); 
	e.printStackTrace(); 
	
	} 
	
	} 
	
	
	
	/** 
	* 移动文件到指定目�?
	* 
	* @param oldPath 
	* String 如：c:/fqf.txt 
	* @param newPath 
	* String 如：d:/fqf.txt 
	*/ 
	public static void moveFile(String oldPath, String newPath) { 
		copyFile(oldPath, newPath); 
		delFile(oldPath); 
	} 
	
	
	/**<b>功能说明：一上传文件</b>
	 * 日期：Sep 14, 2010
	 * @author luo
	 * @param upload
	 * @param absolutePath
	 * @throws Exception
	 */
	public static void upload(File upload, String absolutePath) {
		try{
			String dir = getFilePath(absolutePath);
			FileOperate.newFolder(dir);
			FileOutputStream fos = new FileOutputStream(absolutePath);
			FileInputStream fis = new FileInputStream(upload);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fis.close();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public static void upload(InputStream input, String absolutePath) {
		try{
			String dir = getFilePath(absolutePath);
			FileOperate.newFolder(dir);
			FileOutputStream fos = new FileOutputStream(absolutePath);
			FileInputStream fis = (FileInputStream)input;
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fis.close();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String getSavePath(){
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		return sdf.format(new Date());
	}
	
	//获取文件的文件夹路径
	private static String getFilePath(String filename){
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf("/");
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(0,i + 1);
			}
		}
		return filename;
	}
	private static InputStream getInputStreamFromUrl(String path){
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();//url.openConnection();
		    conn.setDoInput(true);
		    conn.connect();
		    return conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	
    private static String getUploadedpath(String fileName) {
        String filename = StringUtil.createId(32);
        int surfixStart = fileName.lastIndexOf('.');
        String surfix = (surfixStart <= 0) ? "" : fileName.substring(surfixStart);
//        if (isDuapp) {
//            return partTimestamp+md5+surfix;
//        }
        String filedir=getFileDir();
        return filedir+filename+surfix;
    }
    
    private static String getFileDir(){
    	SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/");
		return sdf.format(new Date());
    }
	
	
	public static String uploadFile(String url){
		InputStream input = getInputStreamFromUrl(url);
		if(null!=input){
			return Config.getValue("image.url")+uploadFile(input,getUploadedpath(url));
		}
		return null;
	}
	
	public static String uploadFile(String url,String path){
		InputStream input = getInputStreamFromUrl(url);
		if(null!=input){
			return Config.getValue("image.url")+uploadFile(input,getUploadedpath(path));
		}
		return null;
	}
	
	
	public static String uploadFile(InputStream input,String fullpath){
		String savePath=StringUtil.getFilePath(fullpath);
		String fileName=StringUtil.getFileName(fullpath);
		return uploadFile(input, savePath, fileName);
	}
	
	
	public static String uploadFile(InputStream input,String savePath,String uploadFileName){
		logger.logInfo("uploadFile upload= savePath="+savePath+" uploadFileName="+uploadFileName);
		String result = null;
		if(input != null){
			if(mode){
				boolean b = uploadFileToFtp(input, savePath, uploadFileName);  //uploadFileToFtp(upload, savePath, uploadFileName);
				if(!b){
					uploadFileToLocal(input, savePath, uploadFileName);
				}
			}else{
				uploadFileToLocal(input, savePath, uploadFileName);
			}
		}
		result = savePath +uploadFileName;
		return result;
	}
	
	public static String uploadFile(File upload,String savePath,String uploadFileName){
		String result = null;
		if(upload != null){
			logger.logDebug("uploadFile upload= savePath="+savePath+" uploadFileName="+uploadFileName);
			if(mode){
				boolean b = uploadFileToFtp(upload, savePath, uploadFileName);
				if(!b){
					uploadFileToLocal(upload, savePath, uploadFileName);
				}
			}else{
				uploadFileToLocal(upload, savePath, uploadFileName);
			}
			
		}
		result = savePath + uploadFileName;
		return result;
	}

	public static void uploadFileToLocal(File upload,String savePath,String uploadFileName){
		String foldPath = LOCAL_PATH + savePath;
		try {
			upload(upload, foldPath + uploadFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void uploadFileToLocal(InputStream input,String savePath,String uploadFileName){
		String foldPath = LOCAL_PATH + savePath;
		try {
			upload(input, foldPath + uploadFileName);
			logger.logInfo("upload file path:"+foldPath + uploadFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**<b>功能说明：上传文件到ftp</b>
	 * 日期：Sep 16, 2010
	 * @author luo
	 * @param upload  上传文件
	 * @param savePath 上传文件路径
	 * @param uploadFileName 上传文件
	 */
	private static boolean uploadFileToFtp(File upload,String savePath,String uploadFileName){
		FtpUploader uploader=FtpUploader.connectServer();
		uploader.mkdCatalog(savePath);
		boolean uf=uploader.uploadFile(upload, savePath, uploadFileName, "");
		uploader.distory();
		return uf;
		//		boolean cs = FTPUtil.connectServer();
        //		FTPUtil.mkdCatalog(savePath);
        //		boolean uf =FTPUtil.uploadFile(upload, savePath, uploadFileName, "");
        //		FTPUtil.distory();
        //		return cs && uf;
	}
	private static boolean uploadFileToFtp(InputStream input,String savePath,String uploadFileName){
		FtpUploader uploader=FtpUploader.connectServer();
		uploader.mkdCatalog(savePath);
		boolean uf=uploader.uploadFile(savePath, uploadFileName, input);
		uploader.distory();
		return uf;
//		boolean cs = FTPUtil.connectServer();
//		FTPUtil.mkdCatalog(savePath);
//		boolean uf=FTPUtil.uploadFile(savePath, uploadFileName, input);
//		FTPUtil.distory();
//		return cs && uf;
	}
	/**
	 * @功能描述:删除文件
	 * author：Administrator
	 * date：Sep 15, 2011 11:35:53 AM 
	 * @param filePathAndName
	 * @return
	 * boolean 
	 */
	private static boolean deleteFileToFtp(String filePathAndName){
		boolean cs = FTPUtil.connectServer();
		boolean uf =FTPUtil.deleteFile(filePathAndName);
		FTPUtil.distory();
		return cs && uf;
	}
	
	/**
	 * @功能描述:取得文件大小
	 * author：liuhao
	 * date：Sep 15, 2011 11:35:53 AM 
	 * @param filePathAndName
	 * @return
	 * boolean 
	 */
	public static double getFileSize(String filePathAndName){
		boolean cs = FTPUtil.connectServer();
		double fileSize = FTPUtil.getFileSize(filePathAndName);
		FTPUtil.distory();
		return fileSize;
	}
	
	
	/**
	 * @功能描述:下载文件
	 * author：liuhao
	 * date：Sep 15, 2011 11:35:53 AM 
	 * @param filePathAndName
	 * @return
	 * boolean 
	 */
	public static boolean downLoadFiles(String remoteFile,String localFile){
		boolean cs = FTPUtil.connectServer();
		boolean downLoadState = FTPUtil.loadFile(remoteFile,localFile);
		FTPUtil.distory();
		return cs||downLoadState;
	}
	
	public static String getCurrentName(String fileName){
    	return encodeNumStr(""+new Date().getTime())+"."+StringUtil.getExtensionName(fileName);
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
	 public static int random(int n){
			if(n==0) return 0;
			Random random = new Random();
			return Math.abs(random.nextInt())%n;
		}
	public static void main(String[] args){
		
		
		
		//System.out.println(getSavePath());
		String str = FileOperate.uploadFile("http://avatar.csdn.net/3/C/1/1_feng4656.jpg");
        System.out.println(str);
		/**
		try {
			FileInputStream in = new FileInputStream("d://1287890695203.jpg");
			FileOperate.uploadFileToFtp(in, "/image/aaaa/", "bbcc.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    */
		//FileOperate filedemo=new FileOperate(); 
		//filedemo.delFolder("d:\\userfiles"); 
	    //FileOperate.moveFile("D:\\Tomcat6\\market\\ROOT\\/userfiles/app/393/temp/icon.png "," D:\\Tomcat6\\market\\ROOT\\userfiles/images/393/393_71281079397531.png");
	} 
}

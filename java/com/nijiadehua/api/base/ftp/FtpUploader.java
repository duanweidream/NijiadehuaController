package com.nijiadehua.api.base.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.util.Config;




public class FtpUploader {
	private static final Logger logger = Logger.getLogger(FtpUploader.class); 
    private static String userName= Config.getValue("ftp.username");
    private static String password=  Config.getValue("ftp.password");
    private static String ip=Config.getValue("ftp.ip"); 
    private static int port=Integer.parseInt(Config.getValue("ftp.port"));  
    private  FTPClient ftpClient = null; 
    private boolean connetcion=false;
    public static int i = 1;
    
   
    public FtpUploader(FTPClient ftpClient,boolean connetcion){
    	this.ftpClient=ftpClient;
    	this.connetcion=connetcion;
    }
    
    
    /**
     * 设置FTP客服端的配置--可以不设?
     * @return ftpConfig
     */
    private static FTPClientConfig getFtpConfig() {
            FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
            return ftpConfig;
    }
    
    
    
    
    /**
     * 连接到ftp服务
     * @return true 连接服务器成功，false 连接服务器失
     */
    public static FtpUploader connectServer() {
    	logger.logInfo("FtpUploader.connectServer(ip="+ip+",userName="+userName+",password=********)");
    	FTPClient ftpClient = new FTPClient();
    	boolean connetcion = true;
        int reply;
        try {
                logger.logDebug("ip:"+ip);
                logger.logDebug("userName:"+userName+"  "+password);
                ftpClient.setControlEncoding("GBK");
                ftpClient.configure(getFtpConfig());
                ftpClient.connect(ip);
                ftpClient.login(userName, password);
                ftpClient.setDefaultPort(port);
              
                
                reply = ftpClient.getReplyCode();
                ftpClient.setDataTimeout(120000);
                if (!FTPReply.isPositiveCompletion(reply)) {
                        ftpClient.disconnect();
                        logger.logInfo("FTP server refused connection.");
                        connetcion = false;
                }
                i++;
                
        } catch (SocketException e) {
        	connetcion = false;
                e.printStackTrace();
                logger.logDebug("connect server fail:"+e.getMessage());
        } catch (IOException e) {
        	connetcion = false;
                e.printStackTrace();
                logger.logDebug("connect server fail:"+e.getMessage());
        }
            
        logger.logInfo("FtpUploader connectServer success:"+connetcion);
        return new FtpUploader(ftpClient,connetcion);
    }
    
    public  String getWorkSpace(){
    	try {
			return ftpClient.printWorkingDirectory();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
			
		}
    }
    
    /**
     * 列出服务器上文件和目
     * @param regStr --匹配的正则表达式
     * @throws IOException 
     */
    public  void listRemoteFiles(String regStr) throws IOException {
            try {  
            	   ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                   ftpClient.enterLocalPassiveMode();
                   ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            	    FTPFile files[] = ftpClient.listFiles(regStr);
            	    
            	    //String files[] = ftpClient.listNames(regStr);
                    if (files == null || files.length == 0)
                            System.out.println("没有任何文件!");
                    else {
                            for (int i = 0; i < files.length; i++) {
//                                    System.out.println(files[i].getName()+"    "+files[i].getTimestamp().getTime()+"   "+files[i].isDirectory()+" "+files[i].getSize()/1024+"k");
                            }
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
  
    
    /**
     * 列出服务器上文件和目
     * @param regStr --匹配的正则表达式
     * @throws IOException 
     */
    public List  listRemoteFiles() throws IOException {
    	List result = new ArrayList();
            try {  
            	   ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                   ftpClient.enterLocalPassiveMode();
                   ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            	    FTPFile files[] = ftpClient.listFiles();
            	    
                    if (files == null || files.length == 0)
                            System.out.println("没有任何文件!");
                    else {
                            for (int i = 0; i < files.length; i++) {
                                    //System.out.println(files[i].getName()+"    "+files[i].getTimestamp().getTime()+"   "+files[i].isDirectory()+" "+files[i].getSize()/1024+"k");
                            	result.add(files[i].getName());
                                    
                            }
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return result;
    }
    
 
    
    /**<b>功能说明：上传文件或者文件夹</b>
     * 日期：Sep 13, 2010
     * @author luo
     * @param localFile
     * @param localRootFile
     * @param distFolder
     * @return
     */
    public  String uploadManyFile_bk(File localFile,  File localRootFile,  String distFolder) {
        boolean flag = true;
        StringBuffer strBuf = new StringBuffer();
        int n = 0;
        try {
                if(localFile.isDirectory()){//如果是文件夹
                	ftpClient.makeDirectory("/"+localFile.getName());
//                	System.out.println(localFile.getPath());
                	ftpClient.changeWorkingDirectory("/"+localFile.getName());
//                	System.out.println("work directory"+ftpClient.printWorkingDirectory()+"===========");
                	
                	File fileList[] = localFile.listFiles();
                	for (File upfile : fileList) {
                		if(upfile.isDirectory()){
                            //ftpClient.changeWorkingDirectory(ftpClient.printWorkingDirectory())                			
                			uploadManyFile(upfile, localRootFile, distFolder);
                		}
                	}
                }
                
        } catch (NullPointerException e) {
                e.printStackTrace();
                logger.logDebug("本地文件上传失败！找不到上传文件");
        } catch (Exception e) {
                e.printStackTrace();
                logger.logDebug("本地文件上传失败");
        }finally{
        	distory();
        }
        return strBuf.toString();
}
    /**<b>功能说明：上传多个文</b>
     * 日期：Sep 13, 2010
     * @author luo
     * @param localFile 本地的文件夹路径
     * @param localRootFile
     * @param distFolder 目标路径
     * @return
     */
    public  String uploadManyFile(File localFile,  File localRootFile,  String distFolder) {
        StringBuffer strBuf = new StringBuffer();
        try {
        	    if(localFile.isDirectory()){
        	    	logger.logDebug("----------------------------upload folder "+localFile.getName()+" begin---------------------------");
        	    	String workingDir = FtpUploader.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                    ftpClient.changeWorkingDirectory("/");
        	    	ftpClient.changeWorkingDirectory(distFolder+workingDir);
                    ftpClient.makeDirectory(localFile.getName());
                    File fileList[] = localFile.listFiles();
                    for (File upfile : fileList) {
                            if (upfile.isDirectory()) {// 文件夹中还有文件�?
                                    uploadManyFile(upfile, localRootFile, distFolder);
                            } else {
                                     uploadFile(upfile, localRootFile, distFolder);
                            }
                    }
                    logger.logDebug("----------------------------upload folder "+localFile.getName()+" end---------------------------");
        	    }
        	    //logger.info(strBuf.toString());
        } catch (NullPointerException e) {
                e.printStackTrace();
                //logger.loglogDebug("本地文件上传失败！找不到上传文件");
        } catch (Exception e) {
                e.printStackTrace();
                //logger.loglogDebug("本地文件上传失败");
        }
        return strBuf.toString();
}

    /**
     * 上传单个文件，并重命�?
     * @param localFile--本地文件路径
     * @param distFolder--新的文件�?可以命名为空""
     * @return true 上传成功，false 上传失败
     */
    public  boolean uploadFile(File localFile,  File localRootFile, final String distFolder) {
    	    logger.logDebug("------------------------- upload file:"+localFile.getName());
            boolean flag = true;
            try {   
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                    InputStream input = new FileInputStream(localFile);
                    String objFolder = FtpUploader.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                    objFolder = distFolder+objFolder;
                    ftpClient.changeWorkingDirectory("/");
                    ftpClient.changeWorkingDirectory(objFolder);
                    flag = ftpClient.storeFile(localFile.getName(), input);
                    if (flag) {
                    	logger.logDebug("upload file success:"+objFolder+localFile.getName());
                    } else {
                    	logger.logDebug("upload file filed:"+objFolder+localFile.getName());
                    }
                    input.close();
            } catch (IOException e) {
                    e.printStackTrace();
                    logger.logDebug("upload file filed");
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return flag;
    }
    
    
    
    public boolean uploadFile(String dir,String fileName,InputStream input){
	    logger.logInfo("------------------------- upload file:"+fileName);
        boolean flag = true;
        try {   
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                ftpClient.changeWorkingDirectory("/");
                ftpClient.changeWorkingDirectory(dir);
                flag = ftpClient.storeFile(fileName, input);
                if (flag) {
                	logger.logInfo("upload file success:"+dir);
                } else {
                	logger.logInfo("upload file filed:"+dir);
                }
                input.close();
        } catch (IOException e) {
                e.printStackTrace();
                logger.logInfo("upload file filed");
        } catch (Exception e) {
                e.printStackTrace();
        }
        return flag;

    }
    
    
    /**
     * 上传单个文件，并重命
     * @param localFile--本地文件路径
     * @param distFolder--新的文件 可以命名为空"
     * @return true 上传成功，false 上传失败
     */
    public  boolean uploadFile(File localFile,  File localRootFile, final String distFolder,String remotDirectory) {
    	    logger.logDebug("------------------------- upload file:"+localFile.getName());
            boolean flag = true;
            try {   
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                    InputStream input = new FileInputStream(localFile);
                    String objFolder = FtpUploader.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                    objFolder = distFolder+objFolder;
                    ftpClient.changeWorkingDirectory("/");
                    ftpClient.changeWorkingDirectory(remotDirectory);
                    flag = ftpClient.storeFile(localFile.getName(), input);
                    if (flag) {
                    	logger.logDebug("upload file success:"+objFolder+localFile.getName());
                    } else {
                    	logger.logDebug("upload file filed:"+objFolder+localFile.getName());
                    }
                    input.close();
            } catch (IOException e) {
                    e.printStackTrace();
                    logger.logDebug("upload file filed�");
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return flag;
    }
    
    /**
     * 上传单个文件，并重命
     * @param localFile--要上传的文件
     * @param target--上传的路
     * @param file--文件
     * @return true 上传成功，false 上传失败
     */
    public boolean uploadFile(File localFile,  String savePath,String fileName, final String distFolder) {
	    logger.logDebug("------------------------- upload file:"+localFile.getName());
        boolean flag = true;
        try {   
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                InputStream input = new FileInputStream(localFile);
                //String objFolder = FTPUtil.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                String objFolder = distFolder+savePath;
                logger.logDebug("upload file path:"+distFolder+savePath+fileName);
                ftpClient.changeWorkingDirectory("/");
                ftpClient.changeWorkingDirectory(objFolder);
                flag = ftpClient.storeFile(fileName, input);
                if (flag) {
                	logger.logDebug("upload file success:"+distFolder+savePath+fileName);
                } else {
                	logger.logDebug("upload file filed:"+distFolder+savePath+fileName);
                }
                input.close();
        } catch (IOException e) {
                e.printStackTrace();
                logger.logDebug("upload file filed�");
        } catch (Exception e) {
                e.printStackTrace();
        }
        return flag;
    }

    public  void  sendSiteCommand(String commod) throws IOException{
    	ftpClient.sendSiteCommand(commod);
    }
    /**<b>功能说明：获取工作区</b>
     * 日期：Sep 25, 2010
     * @author luo
     * @param localFile
     * @param rootFile
     * @return
     */
    public static String getWorkingDirectory(File localFile,File rootFile){
    	String localDir = localFile.getAbsoluteFile().toURI().toString();
    	String root = rootFile.getAbsoluteFile().toURI().toString();
    	if(localDir.length()<=root.length()){
    		return "/";
    	}else{
    		return "/"+localDir.substring(root.length());
    	}
    }
    /**<b>功能说明：创建目</b>
     * 日期：Aug 20, 2010
     * @author luo
     * @param dir
     */
    public  void mkdCatalog(String dir){
    	try {
    	logger.logDebug("----------------mkdir folder:"+dir+" begin-----------------------");
    	String[] folder = dir.split("//|/");
    	String contact = "/";
    	for(int i = 0,j=folder.length;i<j;i++){
    		if(null!=folder[i]&&!"".equals(folder[i])){
    			contact=contact+folder[i]+"/";
    			boolean bool = ftpClient.changeWorkingDirectory(folder[i]);
    			if(!bool){
	    			logger.logDebug(contact);
	    			ftpClient.makeDirectory(contact);
	    			logger.logDebug("make dir:"+contact);
    			}
    		}
    	}
    	} catch (Exception e) {
    	   e.printStackTrace();
    	}
    	logger.logDebug("----------------mkdir folder:"+dir+" done!!-----------------------");
     }
    
    /**<b>功能说明：从ftp服务器上获取文件</b>
     * 日期：Aug 20, 2010
     * @author luo
     * @param path 服务器文件路�?
     * @return
     * 
     */
    public InputStream retrieveFileStream(String path){
    	try {
			return ftpClient.retrieveFileStream(path);
		} catch (IOException e) {
			logger.logDebug("get inputStream error! file path "+path);
			e.printStackTrace();
		}
		return null;
    }
    /**<b>功能说明：从ftp上下载文件到本地</b>
     * 日期：Aug 20, 2010
     * @author luo
     * @param remoteFileName 服务器上文件
     * @param localFileName 本地文件
     */
    public  boolean loadFile(String remoteFileName, String localFileName) {
        boolean flag = true;
        connectServer();
        // 下载文件
        BufferedOutputStream buffOut = null;
        try {
                buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
                flag = ftpClient.retrieveFile(remoteFileName, buffOut);
        } catch (Exception e) {
                e.printStackTrace();
                logger.logDebug("本地文件下载失败");
        } finally {
                try {
                        if (buffOut != null)
                                buffOut.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return flag;
    }
    /**
     * 删除ftp中的文件
     * @author luo
     * @param pathname
     * @return
     * @throws IOException
     */
    public  boolean deleteFile(String pathname){
    	logger.logDebug("delete file in ftp : path "+pathname);
    	try {
    		
			return ftpClient.deleteFile(pathname);
		} catch (IOException e) {
			logger.logDebug("delete file pathname fail:"+e.getMessage());
			return false;
		}
    }
    
    
    
     /**<b>功能说明：</b>
     * 日期：Aug 20, 2010
     * @author luo
     */
    public  void distory(){
    	try{
	    	ftpClient.disconnect();
	    	ftpClient = null;
	    	logger.logDebug("--------------------ftpclient desconnect-----------------");
    	}catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
    	
   public static void main(String[] args) throws IOException{
	   //FTPUtil.connectServer();
	   
	   
	   File file = new File("/Users/luoyouhua/Documents/source/wxerp/WebRoot/images/logo.png");
	   File local = new File("D:/");
	   //FTPUtil.deleteFile("/userfile/ftp/a/");
	   FtpUploader.connectServer();
	   //FtpUploader.mkdCatalog("/aaaaa");

	   //listRemoteFiles("/");
	   //FtpUploader.uploadFile(file, local, "");
	   
	   //FTPUtil.listRemoteFiles("/");
//	   System.out.println(getWorkSpace());
	   //FTPUtil.distory();
	  // File file = new File("D:/userfile/ftp/ftp.jpg");
	  // File local = new File("D:/userfile");
	  // FTPUtil.uploadFile(file, local, "");
   }
   /**
	 * 获取文件夹大�?
	 */
   public static long getDirSize(String path){
	   
	   return 1;
   }
   /**
	 * 获取文件大小
	 */
  public  double getFileSize(String path){
	  double fileSize = 0.0;
	  try {
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
		FTPFile files[] = ftpClient.listFiles(path);
		for(int i=0;i<files.length;i++){
			fileSize = files[i].getSize()/1024;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return fileSize;
  }
   /**
	 * 正则表达式判断数�?
	 */
	public static boolean isLetter(String str){ 
		   Pattern pattern = Pattern.compile("[a-zA-Z]*");
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
}

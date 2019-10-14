package com.hualouhui.weixin.base.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.util.Config;



public class FTPUtil {
	private static final Logger logger = Logger.getLogger(FTPUtil.class); 
    private static String userName;         //FTP 登录用户名
    private static String password;         //FTP 登录密码
    private static String ip;                     //FTP 服务器地址IP地址
    private static int port;                        //FTP 端口
    private static Properties property = null;    //属性集
    private static FTPClient ftpClient = null; //FTP 客户端代理化
    public static int i = 1;//FTP状态码
    
    /**
     * 设置参数
     */
    private static void setArg() {
        userName = Config.getValue("ftp.username");
        password = Config.getValue("ftp.password");
        ip = Config.getValue("ftp.ip");
        port = Integer.parseInt(Config.getValue("ftp.port"));
        /**  
        property = new Properties();
            try {
            	    InputStream  in =Thread.currentThread().getContextClassLoader().getResource(CONFIG_FILE).openStream();
                    property.load(in);
                    userName = property.getProperty("ftp.username");
                    password = property.getProperty("ftp.password");
                    ip = property.getProperty("ftp.ip");
                    port = Integer.parseInt(property.getProperty("ftp.port"));
            } catch (FileNotFoundException e1) {
            	    logger.logError("配置文件 " + CONFIG_FILE + " 不存在！");
            } catch (IOException e) {
                    logger.logError("配置文件 " + CONFIG_FILE + " 无法读取！");
            }
            */
    }
    
    /**
     * 设置FTP客服端的配置--一般可以不设置
     * @return ftpConfig
     */
    private static FTPClientConfig getFtpConfig() {
            FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
            return ftpConfig;
    }
    /**
     * 连接到ftp服务器
     * @return true 连接服务器成功，false 连接服务器失败
     */
    public static boolean connectServer() {
    	//
        logger.logInfo("-----------------connect server begin...--------------------");
    	boolean flag = true;
            if (ftpClient == null) {
                    int reply;
                    try {
                            setArg();
                            logger.logInfo("ip:"+ip);
                            logger.logInfo("userName:"+userName+"  "+password);
                            
                            
                            ftpClient = new FTPClient();
                            ftpClient.setControlEncoding("GBK");
                            ftpClient.configure(getFtpConfig());

                            ftpClient.connect(ip);
                            ftpClient.login(userName, password);
                            ftpClient.setDefaultPort(port);
                          
                            
                            reply = ftpClient.getReplyCode();
                            ftpClient.setDataTimeout(120000);
                            if (!FTPReply.isPositiveCompletion(reply)) {
                                    ftpClient.disconnect();
                                    logger.logError("FTP server refused connection.");
                                    flag = false;
                            }
                            i++;
                    } catch (SocketException e) {
                            flag = false;
                            e.printStackTrace();
                            logger.logError("登录ftp服务器 " + ip + " 失败,连接超时！");
                    } catch (IOException e) {
                            flag = false;
                            e.printStackTrace();
                            logger.logError("登录ftp服务器 " + ip + " 失败，FTP服务器无法打开！");
                    }
            }
            logger.logInfo("connect ftp server! flag:"+flag);
            return flag;
    }
    
    public static String getWorkSpace(){
    	try {
			return ftpClient.printWorkingDirectory();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
			
		}
    }
    
    /**
     * 列出服务器上文件和目录
     *
     * @param regStr --匹配的正则表达式
     * @throws IOException 
     */
    public static void listRemoteFiles(String regStr) throws IOException {
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
                                    System.out.println(files[i].getName()+"    "+files[i].getTimestamp().getTime()+"   "+files[i].isDirectory()+" "+files[i].getSize()/1024+"k");
                            }
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
  


    
 
    
    /**<b>功能说明：上传文件或者文件夹</b>
     * 日期：Sep 13, 2010
     * @author luo
     * @param localFile
     * @param localRootFile
     * @param distFolder
     * @return
     */
    public static String uploadManyFile_bk(File localFile,  File localRootFile,  String distFolder) {
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
                /**
                ftpClient.makeDirectory(distFolder + "/" + localFile.getParent());
                System.out.println("创建文件夹"+distFolder + "/" + localFile.getParent());
                File fileList[] = localFile.listFiles();
                for (File upfile : fileList) {
                        if (upfile.isDirectory()) {// 文件夹中还有文件夹
                                uploadManyFile(upfile, localRootFile, distFolder);
                        } else {
                                flag = uploadFile(upfile, localRootFile, distFolder);
                        }
                        if (!flag) {
                                strBuf.append(upfile.getName() + "\r\n");
                        }
                }*/
                System.out.println(strBuf.toString());
        } catch (NullPointerException e) {
                e.printStackTrace();
                logger.logDebug("本地文件上传失败！找不到上传文件！", e);
        } catch (Exception e) {
                e.printStackTrace();
                logger.logDebug("本地文件上传失败！", e);
        }finally{
        	distory();
        }
        return strBuf.toString();
}
    /**<b>功能说明：上传多个文件</b>
     * 日期：Sep 13, 2010
     * @author luo
     * @param localFile 本地的文件夹路径
     * @param localRootFile
     * @param distFolder 目标路径
     * @return
     */
    public static String uploadManyFile(File localFile,  File localRootFile,  String distFolder) {
        StringBuffer strBuf = new StringBuffer();
        try {
        	    if(localFile.isDirectory()){
        	    	logger.logInfo("----------------------------upload folder "+localFile.getName()+" begin---------------------------");
        	    	String workingDir = FTPUtil.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                    ftpClient.changeWorkingDirectory("/");
        	    	ftpClient.changeWorkingDirectory(distFolder+workingDir);
                    ftpClient.makeDirectory(localFile.getName());
                    File fileList[] = localFile.listFiles();
                    for (File upfile : fileList) {
                            if (upfile.isDirectory()) {// 文件夹中还有文件夹
                                    uploadManyFile(upfile, localRootFile, distFolder);
                            } else {
                                     uploadFile(upfile, localRootFile, distFolder);
                            }
                    }
                    logger.logInfo("----------------------------upload folder "+localFile.getName()+" end---------------------------");
        	    }
        	    //logger.info(strBuf.toString());
        } catch (NullPointerException e) {
                e.printStackTrace();
                logger.logDebug("本地文件上传失败！找不到上传文件！", e);
        } catch (Exception e) {
                e.printStackTrace();
                logger.logDebug("本地文件上传失败！", e);
        }
        return strBuf.toString();
}

    /**
     * 上传单个文件，并重命名
     * @param localFile--本地文件路径
     * @param distFolder--新的文件名,可以命名为空""
     * @return true 上传成功，false 上传失败
     */
    public static boolean uploadFile(File localFile,  File localRootFile, final String distFolder) {
    	    logger.logInfo("------------------------- upload file:"+localFile.getName());
            boolean flag = true;
            try {   
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                    InputStream input = new FileInputStream(localFile);
                    String objFolder = FTPUtil.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                    objFolder = distFolder+objFolder;
                    ftpClient.changeWorkingDirectory("/");
                    ftpClient.changeWorkingDirectory(objFolder);
                    flag = ftpClient.storeFile(localFile.getName(), input);
                    if (flag) {
                    	logger.logInfo("upload file success:"+objFolder+localFile.getName());
                    } else {
                    	logger.logInfo("upload file filed:"+objFolder+localFile.getName());
                    }
                    input.close();
            } catch (IOException e) {
                    e.printStackTrace();
                    logger.logDebug("upload file filed！", e);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return flag;
    }
    
    /**
     * 上传单个文件，并重命名
     * @param localFile--本地文件路径
     * @param distFolder--新的文件名,可以命名为空
     * @return true 上传成功，false 上传失败
     */
    public static boolean uploadFile(File localFile,  File localRootFile, final String distFolder,String remotDirectory) {
    	    logger.logInfo("------------------------- upload file:"+localFile.getName());
            boolean flag = true;
            try {   
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                    InputStream input = new FileInputStream(localFile);
                    String objFolder = FTPUtil.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                    objFolder = distFolder+objFolder;
                    ftpClient.changeWorkingDirectory("/");
                    ftpClient.changeWorkingDirectory(remotDirectory);
                    flag = ftpClient.storeFile(localFile.getName(), input);
                    if (flag) {
                    	logger.logInfo("upload file success:"+objFolder+localFile.getName());
                    } else {
                    	logger.logInfo("upload file filed:"+objFolder+localFile.getName());
                    }
                    input.close();
            } catch (IOException e) {
                    e.printStackTrace();
                    logger.logDebug("upload file filed！", e);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return flag;
    }
    
    /**
     * 上传单个文件，并重命名
     * @param localFile--要上传的文件
     * @param target--上传的路径
     * @param file--文件名
     * @return true 上传成功，false 上传失败
     */
    public static boolean uploadFile(File localFile,  String savePath,String fileName, final String distFolder) {
	    logger.logInfo("------------------------- upload file:"+localFile.getName());
        boolean flag = true;
        try {   
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                InputStream input = new FileInputStream(localFile);
                //String objFolder = FTPUtil.getWorkingDirectory(localFile.getParentFile(), localRootFile);
                String objFolder = distFolder+savePath;
                logger.logInfo("upload file path:"+distFolder+savePath+fileName);
                ftpClient.changeWorkingDirectory("/");
                ftpClient.changeWorkingDirectory(objFolder);
                flag = ftpClient.storeFile(fileName, input);
                if (flag) {
                	logger.logInfo("upload file success:"+distFolder+savePath+fileName);
                } else {
                	logger.logInfo("upload file filed:"+distFolder+savePath+fileName);
                }
                input.close();
        } catch (IOException e) {
                e.printStackTrace();
                logger.logDebug("upload file filed！", e);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return flag;
    }

    public static void  sendSiteCommand(String commod) throws IOException{
    	ftpClient.sendSiteCommand(commod);
    }
    /**<b>功能说明：获取工作区域</b>
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
    /**<b>功能说明：创建目录</b>
     * 日期：Aug 20, 2010
     * @author luo
     * @param dir
     */
    public static void mkdCatalog(String dir){
    	try {
    	logger.logInfo("----------------mkdir folder:"+dir+" begin-----------------------");
    	String[] folder = dir.split("//|/");
    	String contact = "/";
    	for(int i = 0,j=folder.length;i<j;i++){
    		if(null!=folder[i]&&!"".equals(folder[i])){
    			contact=contact+folder[i]+"/";
    			boolean bool = ftpClient.changeWorkingDirectory(folder[i]);
    			if(!bool){
	    			logger.logInfo(contact);
	    			ftpClient.makeDirectory(contact);//makeDirectory一次只能创建一个目录
	    			logger.logInfo("make dir:"+contact);
    			}
    		}
    	}
    	} catch (Exception e) {
    	   e.printStackTrace();
    	}
    	logger.logInfo("----------------mkdir folder:"+dir+" done!!-----------------------");
     }
    
    /**<b>功能说明：从ftp服务器上获取文件流</b>
     * 日期：Aug 20, 2010
     * @author luo
     * @param path 服务器文件路径
     * @return
     * 
     */
    public InputStream retrieveFileStream(String path){
    	try {
			return ftpClient.retrieveFileStream(path);
		} catch (IOException e) {
			logger.logError("get inputStream error! file path "+path);
			e.printStackTrace();
		}
		return null;
    }
    /**<b>功能说明：从ftp上下载文件到本地</b>
     * 日期：Aug 20, 2010
     * @author luo
     * @param remoteFileName 服务器上文件名
     * @param localFileName 本地文件名
     */
    public static boolean loadFile(String remoteFileName, String localFileName) {
        boolean flag = true;
        connectServer();
        // 下载文件
        BufferedOutputStream buffOut = null;
        try {
                buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
                flag = ftpClient.retrieveFile(remoteFileName, buffOut);
        } catch (Exception e) {
                e.printStackTrace();
                logger.logDebug("本地文件下载失败！", e);
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
    public static boolean deleteFile(String pathname){
    	logger.logInfo("delete file in ftp : path "+pathname);
    	try {
    		
			return ftpClient.deleteFile(pathname);
		} catch (IOException e) {
			logger.logError("delete file pathname fail:"+e.getMessage());
			return false;
		}
    }
    
    
    
     /**<b>功能说明：关闭连接</b>
     * 日期：Aug 20, 2010
     * @author luo
     */
    public static void distory(){
    	try{
	    	ftpClient.disconnect();
	    	ftpClient = null;
	    	logger.logInfo("--------------------ftpclient desconnect-----------------");
    	}catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
    	
   public static void main(String[] args) throws IOException{
	   FTPUtil.connectServer();
	   
	   FTPUtil.listRemoteFiles("/");
	   File file = new File("/Users/luoyouhua/Downloads/APP.png");
	   File local = new File("/Users/luoyouhua");
	   //FTPUtil.mkdCatalog("/aaaaa");
	   //FTPUtil.deleteFile("/userfile/ftp/a/");
	   FTPUtil.uploadFile(file, local, "");
	   
	   //FTPUtil.listRemoteFiles("/");
//	   System.out.println(getWorkSpace());
	   //FTPUtil.distory();
	  // File file = new File("D:/userfile/ftp/ftp.jpg");
	  // File local = new File("D:/userfile");
	  // FTPUtil.uploadFile(file, local, "");
   }
   /**
	 * 获取文件夹大小
	 */
   public static long getDirSize(String path){
	   
	   return 1;
   }
   /**
	 * 获取文件大小
	 */
  public static double getFileSize(String path){
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
	 * 正则表达式判断数字
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

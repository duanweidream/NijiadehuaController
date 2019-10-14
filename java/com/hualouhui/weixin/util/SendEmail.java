package com.hualouhui.weixin.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class SendEmail {
	
	private static Logger log = Logger.getLogger(SendEmail.class);
	
	//********************************* 2016-5-19 张睿改，修改从mail.propertise读取即可  ********************************************
	/**
	 * 配置文件
	 */
    public static final String HOST ;

    public static final String PROTOCOL ;

    public static Integer PORT ;

    public static final String FROM ;// 发件人的email

    public static final String PWD ;// 发件人密码
    
    public static final String FROM_NICK = "【山东齐发智慧高速服务区运营有限公司】";// 发件人昵称
    
    static {
    	
    	//ResourceBundle bundle = ResourceBundle.getBundle("mail");
		/**
		 * 进行数据初始化
		 */
    	HOST = Config.getValue("HOST");
    	PROTOCOL  = Config.getValue("PROTOCOL");
    	try {
			PORT   = Integer.parseInt(Config.getValue("PORT"));
		} catch (NumberFormatException e) {
			PORT = 25 ;
		}
    	FROM = Config.getValue("FROM");
    	PWD  = Config.getValue("PWD");
    	
	}
    
  //********************************* 2016-5-19 张睿改，修改从mail.propertise读取  ********************************************
    
    static Properties props = new Properties();

    // public static final String FROM = "jinjing_0316@126.com";//发件人的email
    // public static final String PWD = "jinjing";//发件人密码

    /**
     * 获取Session
     * 
     * @return
     */
    private static Session getSession() {

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");// 发送邮件协议
        properties.setProperty("mail.smtp.auth", "true");// 需要验证
        properties.put("mail.smtp.localhost", "localhost");
        
        Session session = Session.getInstance(properties);
        session.setDebug(true);// debug模式
        return session;
    }

    public static void send(String toEmail, String content,String subject) throws Exception {
        Session session = getSession();
        try {
        	log.debug("--send email FROM--" + FROM);
        	log.debug("--send email PWD--" + PWD);
        	log.debug("--send email toEmail--" + toEmail);
        	log.info("--send email content--" + content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            // Set message attributes
            String nick = javax.mail.internet.MimeUtility.encodeText(FROM_NICK);
            msg.setFrom(new InternetAddress(nick+" <"+FROM+">"));
            InternetAddress[] address = { new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");

            // Send the message
            Transport transport = session.getTransport();

            // tran.connect("smtp.sohu.com", 25, "wuhuiyao@sohu.com",
            // "xxxx");//连接到新浪邮箱服务器
            transport.connect(HOST, 25, FROM, PWD);// 连接到新浪邮箱服务器
            // tran.connect("smtp.qq.com", 25, "Michael8@qq.vip.com",
            // "xxxx");//连接到QQ邮箱服务器
            transport.sendMessage(msg, new Address[] { new InternetAddress(toEmail) });// 设置邮件接收人
            transport.close();
        } catch (MessagingException mex) {
        	mex.printStackTrace();
        	throw new Exception("邮件发送失败");
         
        }
    }
    
    public static void main(String[] args) throws Exception{
    	
    	
    	SendEmail.send("duanwei@wobmob.cn", "test", "mail-test");
    }
    
    
    
}

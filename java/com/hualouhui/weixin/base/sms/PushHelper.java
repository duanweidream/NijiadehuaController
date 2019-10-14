package com.hualouhui.weixin.base.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hualouhui.weixin.base.log.Logger;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

/**
 * 
 * 项目名称：API01
 * @author hshuai
 * 创建时间：2016-10-10 下午4:01:18
 */

public class PushHelper {
	private static Logger logger = Logger.getLogger(MessagePusher.class);
	
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    // android test
    static String appkey = "7YZSaEUuDz8AzW2x4qIvq8";
    static String master = "0nbVEc2zIdAehPnc2LC1c6";
    static String appId = "cHjn7CXbOa60agA6PdAGZ3";
    
    static String CID = "9b78558dca8d80a4f594392b473867dd";
    
    // ios test
//    static String appkey = "SSXCYhC6EA7neeM98peDP4";
//    static String master = "hfXEKeMBra7cKTrlEvNXu3";
//    static String appId = "Uniwhf4PIc79fkXyROjjA3";
    
    
//    static String CID = "3fcccf274e3cc63c3b95c335f5cf8973";

    
    public static void push(String cid, String title, String msgContent, String transContent,String type) throws Exception {
    	//安卓
    	if("android".equals(type)){
    		pushAndroid( cid,  title,  msgContent,  transContent);
    	//苹果
    	}else if("ios".equals(type)){
    		pushIos( cid,  title,  msgContent,  transContent);
    	//未知
    	}else{
    		//pushAndroid( cid,  title,  msgContent,  transContent);
    	}
    	
    }
    
    
    /**
     * 安卓
     * @param cid
     * @param title
     * @param msgContent
     * @param transContent
     * @throws Exception
     */
    public static void pushAndroid(String cid, String title, String msgContent, String transContent) throws Exception {
    	if (logger != null) {
    		//logger = logger.derive(PushHelper.class);
    		logger.debug("pushing message to (%s) with title: %s", cid, title);
    	}
    	if (cid == null || cid.length() == 0) {
        	if (logger != null) {
        		logger.debug("ignore pushing for is CID");        		
        	}
            
        }else{
        	if (logger != null) {
        		logger.debug("ignore pushing for null CID");        		
        	}
        }
        IGtPush push = new IGtPush(host, appkey, master);
        push.connect();

        /**
         * 组装信息
         */
        NotificationTemplate template = createNotificationTemplate( title, msgContent, transContent);
        
        //LinkTemplate template = createAndroidLinkTemplate(title, msgContent, transContent);
        IPushResult ret = null;
        	
        //个人用户
        if (cid != null && cid.length() > 0) {
        	logger.debug("pushing  singleapp "+cid);
            SingleMessage message = new SingleMessage();
            message.setOffline(true);
            message.setOfflineExpireTime(72 * 3600 * 1000);
            message.setData(template);

            Target target = new Target();
            target.setClientId(cid);
            target.setAppId(appId);
            
            try {
            	//发送消息
            	ret = push.pushMessageToSingle(message, target);
            } catch (RequestException e) {
            	e.printStackTrace();
            	//失败后重新发送消息
            	ret = push.pushMessageToSingle(message, target, e.getRequestId());
            	
            }
        //所有用户
        }else{
        	logger.debug("pushing  allapp ");
        	AppMessage message = new AppMessage();
        	message.setData(template);
        	message.setOffline(true);
        	message.setOfflineExpireTime(72 * 3600 * 1000);
        	
        	
        	
        	
        	//App􁌱􁍓􀺽􁊠􀲁􁵱􁥝􁃿􁪃􁌱􀹵􀕯
        	AppConditions cdt = new AppConditions();
        	
        	//手机类型􀹢􁔄􀣳
        	List<String> phoneTypeList = new ArrayList<String>();
        	phoneTypeList.add("ANDROID");
        	cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        	List<String> appIdList = new ArrayList<String>();
        	appIdList.add(appId);
        	message.setAppIdList(appIdList);
        	message.setConditions(cdt);
        	
        	ret = push.pushMessageToApp(message);
        }

        //发送是否成功
        if (ret != null) {
        	logger.debug("pushing result: %s", ret.getResponse().toString());
        } else {
        	logger.debug("pushing result: fail");
        }
        
    }

    /**
     * 安卓
     * @param cid
     * @param title
     * @param msgContent
     * @param transContent
     * @throws Exception
     */
    public static void pushIos(String cid, String title, String msgContent, String transContent) throws Exception {
    	if (logger != null) {
    		//logger = logger.derive(PushHelper.class);
    		logger.debug("pushing message to (%s) with title: %s", cid, title);
    	}
    	if (cid == null || cid.length() == 0) {
        	if (logger != null) {
        		logger.debug("ignore pushing for is CID");        		
        	}
            
        }else{
        	if (logger != null) {
        		logger.debug("ignore pushing for null CID");        		
        	}
        }
        IGtPush push = new IGtPush(host, appkey, master);
        push.connect();

        /**
         * 组装信息
         */
        TransmissionTemplate template = getTransmissionTemplate( title, msgContent, transContent);
        
        //LinkTemplate template = createAndroidLinkTemplate(title, msgContent, transContent);
        IPushResult ret = null;
        	
        //个人用户
        if (cid != null && cid.length() > 0) {
        	logger.debug("pushing  singleapp "+cid);
            SingleMessage message = new SingleMessage();
            message.setOffline(true);
            message.setOfflineExpireTime(72 * 3600 * 1000);
            message.setData(template);

            Target target = new Target();
            target.setClientId(cid);
            target.setAppId(appId);
            
            try {
            	//发送消息
            	ret = push.pushMessageToSingle(message, target);
            } catch (RequestException e) {
            	e.printStackTrace();
            	//失败后重新发送消息
            	ret = push.pushMessageToSingle(message, target, e.getRequestId());
            	
            }
        //所有用户
        }else{
        	logger.debug("pushing  allapp ");
        	AppMessage message = new AppMessage();
        	message.setData(template);
        	message.setOffline(true);
        	message.setOfflineExpireTime(72 * 3600 * 1000);
        	//App􁌱􁍓􀺽􁊠􀲁􁵱􁥝􁃿􁪃􁌱􀹵􀕯
        	AppConditions cdt = new AppConditions();
        	
        	//手机类型􀹢􁔄􀣳
        	List<String> phoneTypeList = new ArrayList<String>();
        	phoneTypeList.add("IOS");
        	cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        	
        	List<String> appIdList = new ArrayList<String>();
        	appIdList.add(appId);
        	
        	message.setAppIdList(appIdList);
        	message.setConditions(cdt);
        	
        	ret = push.pushMessageToApp(message);
        }

        //发送是否成功
        if (ret != null) {
        	logger.debug("pushing result: %s", ret.getResponse().toString());
        } else {
        	logger.debug("pushing result: fail");
        }
        
    }    
    
    
    private static NotificationTemplate createNotificationTemplate(String cid, String title, String msgContent, String transContent) throws Exception {
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appkey);
        template.setTitle(title);
        template.setText(msgContent);
        template.setLogo("icon.png");
        template.setLogoUrl("");
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        template.setTransmissionType(1);
        template.setTransmissionContent(transContent);
        template.setPushInfo("actionLocKey", 1, transContent, "sound", "payload", msgContent, "locArgs", "launchImage");
        return template;
    }
    
    private static TransmissionTemplate createTransmissionTemplate(String cid, String title, String msgContent, String transContent) throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTransmissionType(2);
		
		String content = String.format("{\"title\":\"%s\", \"content\":\"%s\", \"operation\":%s}", title, msgContent, transContent);
		template.setTransmissionContent(content);
		template.setPushInfo("actionLocKey", 1, transContent, "sound", "payload", title, "locArgs", "launchImage");
		return template;
	}
    
    /**
     * 新个推SDK demo 安卓版 多个用户推送实体 启动应用版
     * @param cid
     * @param title
     * @param msgContent
     * @param transContent
     * @return
     */
    private static NotificationTemplate createNotificationTemplate(String title, String msgContent, String transContent) throws Exception{
    	NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		//设置app方式
		template.setTransmissionType(2);
		//设置通知栏标题
		template.setTitle(title);
		//设置通知栏内容
		template.setText(msgContent);
    	//透传的内容
		template.setTransmissionContent(transContent);
		//receiver
		//template.setPushInfo(actionLocKey, badge, message, sound, payload, locKey, locArgs, launchImage, contentAvailable)
    	return template;
    }
    
    /**
     * 综合安卓版实体  链接版
     * @param title
     * @param msgContent
     * @param transContent
     * @return
     * @throws Exception
     */
    private static LinkTemplate createAndroidLinkTemplate(String title, String msgContent, String transContent) throws Exception{
    	
    	LinkTemplate template = new LinkTemplate();
    	template.setAppId(appId);
		template.setAppkey(appkey);
    	template.setTitle(title);
    	template.setText(msgContent);
    	template.setIsRing(true);
    	template.setIsVibrate(true);
    	template.setIsClearable(true);
    	template.setPushInfo("actionLocKey", 1, transContent, "sound", "payload", title, "locArgs", "launchImage");
    	template.setUrl("http://www.wooboo.com.cn/");
    	
    	return template;
    }
    
    
    public static TransmissionTemplate getTransmissionTemplate(String title, String msgContent, String transContent) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appkey);
        template.setTransmissionContent(transContent);
        template.setTransmissionType(2);
        APNPayload payload = new APNPayload();
        //payload.setBadge(1);  //将应用icon上显示的数字设为1
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，如10，效果和setBadge一样，
        //应用icon上显示指定数字。不能和setBadge同时使用
        //tAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        //payload.setCategory(transContent);
        payload.addCustomMsg("payload", transContent);
        //简单模式APNPayload.SimpleMsg 
        //payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
        //字典模式使用下者
        payload.setAlertMsg(getDictionaryAlertMsg(title,msgContent));
        template.setAPNInfo(payload);
        return template;
    }
    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title, String msgContent){
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody(msgContent);
        alertMsg.setActionLocKey("ActionLockey");
        alertMsg.setLocKey("LocKey");
        alertMsg.addLocArg("loc-args");
        alertMsg.setLaunchImage("launch-image");
        // IOS8.2以上版本支持
        alertMsg.setTitle(title);
        alertMsg.setTitleLocKey("TitleLocKey");
        alertMsg.addTitleLocArg("TitleLocArg");
        return alertMsg;
    }

    
    


 
    
    public static void main(String[] args)throws Exception {
    	String str="qltraffic://page/webview?link=http://wx.zaiziha.com/article/detail,12935.do";
       //{"scheme":"qltraffic://page/webview?link=http://wx.zaiziha.com/article/detail,12935.do"}
    	Map<String,String> mapUrl=new HashMap<String, String>();
		mapUrl.put("scheme", str);
		String urlJson =JSONObject.fromObject(mapUrl).toString();
    	
    	String[][] data = new String[][]{
    			//{"感人至深！", "我不敢休息，因为我没有存款。", "我不敢休息，因为我没有存款。"},
    			{"种植秘法", "自己的土，自己的地，种啥都得人民币", urlJson},
    			//{"奥利奥！", "让你快乐长高", "让你快乐长高"},
    			//{"恭喜中奖了！", "您已赢得百万大奖！", "百万大奖等你来拿！"},
    			//{"齐鲁服务器活动！", "只要你敢来，我就让你满载而归！", "只要你敢来，我就让你满载而归！"},
    	};
    	
    	
    	for (String[] item : data) {
    		
    		
    		String title = item[0];
    		String msgContent = item[1];
    		String transContent = item[2];
        	push(null, title, msgContent, transContent,"ios");
            //break;
        	//Thread.sleep(60000);
    	}
	    
    }
}

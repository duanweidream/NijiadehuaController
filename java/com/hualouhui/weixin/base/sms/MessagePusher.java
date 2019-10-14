package com.hualouhui.weixin.base.sms;

import java.util.List;

import com.hualouhui.weixin.base.log.Logger;


public class MessagePusher {
	private static Logger logger = Logger.getLogger(MessagePusher.class);
	private static MessagePusher instance = new MessagePusher();
	
	public static MessagePusher getInstance() {
		return instance;
	}
	
    public void sendSms(List<String> phone_numbers, String message) {
    		for(String phone_number:phone_numbers){
    			logger.debug("sendSms phone_number: %s", phone_number);
    			try {
					SmsWrapper.send(phone_number, message);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    }
    public void sendSms(String phone_number, String message) {
    	logger.debug("sendSms phone_number: %s", phone_number);
		try {
			SmsWrapper.send(phone_number, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args){
    	MessagePusher.getInstance().sendSms("13522328241", "验证码8796,请勿向任何人泄露，验证码提供他人将导致账户被盗,请勿转发.");;
    }
    
    
}

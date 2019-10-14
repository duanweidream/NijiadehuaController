package com.hualouhui.weixin.base.wechat;

import java.util.ArrayList;
import java.util.List;

import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.base.wechat.message.XMLMessage;
import com.hualouhui.weixin.base.wechat.message.XMLNewsMessage;
import com.hualouhui.weixin.base.wechat.message.XMLTextMessage;
import com.hualouhui.weixin.base.wechat.message.XMLNewsMessage.Article;
import com.hualouhui.weixin.constants.WeChat;
import com.hualouhui.weixin.constants.WeChat.Event;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Fans;
import com.hualouhui.weixin.model.WechatQa;
import com.hualouhui.weixin.util.Config;
import com.hualouhui.weixin.util.StringUtil;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.XMLConverUtil;

/**
 * 消息管理
 * */
public class WeChatManager {
	public static Logger logger = Logger.getLogger(WeChatManager.class);
	// 重复通知过滤
	private static ExpireKey expireKey = new DefaultExpireKey();

	public static String weChatEventProcess(String xmlString) throws ApiError {
		logger.logInfo(xmlString);
		EventMessage eventMessage = XMLConverUtil.convertToObject(
				EventMessage.class, xmlString);
		String key = eventMessage.getFromUserName() + "__"
				+ eventMessage.getToUserName() + "__" + eventMessage.getMsgId()
				+ "__" + eventMessage.getCreateTime();
		if (expireKey.exists(key)) {
			throw ApiError.Type.LOGIC_ERROR.toException();
		} else {
			expireKey.add(key);
		}
		if ("event".equalsIgnoreCase(eventMessage.getMsgType())) {
			Event evt = WeChat.Event.fromValue(eventMessage.getEvent());
			if (WeChat.Event.subscribe == evt) {
				// 关注，保存用户信息
				WeChatApi.loadUserInfo(eventMessage.getFromUserName());
				return subscribe(eventMessage);
			} else if (WeChat.Event.unsubscribe == evt) {
				//取消关注
				WeChatApi.unsubscribe(eventMessage.getFromUserName());
			} else if (WeChat.Event.click == evt) {
				String event_key = eventMessage.getEventKey();
				if ("get_news".equals(event_key)) {
					// 最新资讯

				}else if ("shangwuhezuo".equals(event_key)) {
					// 商务合作
					return shangwuhezuo(eventMessage);
				}
			}
		} else if ("text".equalsIgnoreCase(eventMessage.getMsgType())) {
			// QA问答 2018.04.27 modify by duanwei
			//return userQa(eventMessage);

		}

		return "";
	}

	public static String subscribe(EventMessage eventMessage) {
		
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("花楼会查看丰镇市本地信息，便利市民生活，足不出户，了解丰镇的那些事儿~ 免费发布信息，方便简单~ ");
		XMLMessage xmlTextMessage = new XMLTextMessage(
				eventMessage.getFromUserName(), eventMessage.getToUserName(),
				sbuf.toString());
		return xmlTextMessage.toXML();
	}

	/**
	 * 商务合作
	 * 
	 * @param message
	 * @return
	 * @throws ApiError
	 */
	public static String shangwuhezuo(EventMessage message) throws ApiError {

		StringBuffer sbuf = new StringBuffer();

		sbuf.append("服务区广告位宣传推广、经营业态商务合作，请联系：齐发智慧高速服务区运营有限公司 张经理 13256104119"
				+ "\n");
		XMLMessage xmlTextMessage = new XMLTextMessage(
				message.getFromUserName(), message.getToUserName(),
				sbuf.toString());
		return xmlTextMessage.toXML();

	}

}

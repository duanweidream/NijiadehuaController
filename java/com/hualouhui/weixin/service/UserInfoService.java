package com.hualouhui.weixin.service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.hualouhui.weixin.base.db.JdbcTemplate;
import com.hualouhui.weixin.base.db.Sql;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.UserInfo;


/**
 * 粉丝模块数据库操作
 * */
public class UserInfoService {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * 微信表情字符串。utf8字符保存会报错。 1：修改为utf8bm4 2:过滤掉emoji字符.
	 * */
	private static String filterEmoji(String source) {  
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find()) 
            {
                source = emojiMatcher.replaceAll("M");
                return source ; 
            }
        return source;
       }
       return source;  
    }

	public void unsubscribe(String openid) throws ApiError{
		Sql sql = new Sql("update user_info set subscribe=?,subscribe_time = now() where openid=?");
	    sql.addParam("0",openid);
	    jdbcTemplate.updateObject(sql);
	}

	public Long saveUserInfo(UserInfo fans) throws ApiError{
		fans.nickname=filterEmoji(fans.nickname);//过滤掉表情字符
		Sql sql = new Sql("insert into user_info (`subscribe`,`openid`,`nick_name`,`sex`,`city`,`country`,`province`,`language`,`headimgurl`,`subscribe_time`,`created_time`)");
	    sql.append("values (?,?,?,?,?,?,?,?,?,?,?) ");
	    sql.addParam(fans.subscribe,fans.openid,fans.nickname,fans.sex,fans.city,fans.country,fans.province,fans.language,fans.headimgurl,new Date(),new Date());
	    return jdbcTemplate.saveObject(sql);
	}
	
	public void updateUserInfo(UserInfo fans) throws ApiError{
		fans.nickname=filterEmoji(fans.nickname);//过滤掉表情字符
	    Sql sql = new Sql(" update user_info set subscribe=?,nick_name=?,headimgurl=?,sex=?,city=?,province=?,country=?,language=?,`subscribe_time` = now() where id = ? ");
	    sql.addParam(fans.subscribe,fans.nickname,fans.headimgurl,fans.sex,fans.city,fans.province,fans.country,fans.language,fans.id);
	    jdbcTemplate.updateObject(sql);
	}
	
	
	public UserInfo findUserInfoByOpenid(String openid) throws ApiError{
		Sql sql = new Sql("select * from user_info f where f.openid=?");
		sql.addParam(openid);
		return jdbcTemplate.findObject(sql, UserInfo.class);
	}
	
}

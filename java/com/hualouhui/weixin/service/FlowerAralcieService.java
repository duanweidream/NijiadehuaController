package com.hualouhui.weixin.service;

import com.hualouhui.weixin.base.db.JdbcTemplate;
import com.hualouhui.weixin.base.db.Sql;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.FlowerInfo;
import com.hualouhui.weixin.model.Page;

/**
 * ClassName:FlowerInfoService</br> Function: 文章基本信息处理 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-04-16 上午10:09:34</br>
 * 
 */
public class FlowerAralcieService {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public void searchFlowerInfoForPage(Page page,String sort_id) throws ApiError{
		Sql sql = new Sql(" select a.id,a.info_sort,a.info_content,info_contact,info_mobile,DATE_FORMAT(a.create_time,'%Y年%m月%d日') as create_time_format,b.headimgurl as wx_header,b.nick_name as wx_nick from flower_info a,user_info b where a.user_id = b.id ");
		sql.append("and", "a.info_sort", "=", sort_id);
		sql.append(" order by a.create_time desc ");
		jdbcTemplate.search(page, sql, FlowerInfo.class);
	}
	
}

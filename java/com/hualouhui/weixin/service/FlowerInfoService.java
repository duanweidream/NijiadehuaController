package com.hualouhui.weixin.service;

import java.util.Date;
import java.util.List;

import com.hualouhui.weixin.base.db.JdbcTemplate;
import com.hualouhui.weixin.base.db.Sql;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.FlowerImg;
import com.hualouhui.weixin.model.FlowerInfo;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.model.Sales;
import com.hualouhui.weixin.model.Shangcheng;
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;

/**
 * ClassName:FlowerInfoService</br> Function: 花楼会基本信息处理 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-04-16 上午10:09:34</br>
 * 
 */
public class FlowerInfoService {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public Long saveFlowerInfo(FlowerInfo flower) throws ApiError{
		Sql sql = new Sql("insert into flower_info (`user_id`,`info_sort`,`info_content`,`info_contact`,`info_mobile`,`create_time`)");
		sql.append("values (?,?,?,?,?,?) ");
		sql.addParam(flower.user_id,flower.info_sort,flower.info_content,flower.info_contact,flower.info_mobile,new Date());
		Long flower_id  = jdbcTemplate.saveObject(sql);
		
		for(FlowerImg flowerImg : flower.getInfo_imgs()){
			Sql img = new Sql("insert into flower_imgs (`flower_id`,`flower_url`)");
			img.append("values (?,?) ");
			img.addParam(flower_id,flowerImg.getDataURL());
			jdbcTemplate.saveObject(img);
		}
		
		return flower_id;
		
	}
	
	
	public void searchFlowerInfoForPage(Page page,String sort_id) throws ApiError{
		Sql sql = new Sql(" select a.id,a.info_sort,a.info_content,info_contact,info_mobile,DATE_FORMAT(a.create_time,'%Y年%m月%d日') as create_time_format,b.headimgurl as wx_header,b.nick_name as wx_nick from flower_info a,user_info b where a.user_id = b.id ");
		sql.append("and", "a.info_sort", "=", sort_id);
		sql.append(" order by a.create_time desc ");
		jdbcTemplate.search(page, sql, FlowerInfo.class);
	}
	
	public List<FlowerImg> searchFlowerImgList() throws ApiError{
		Sql sql = new Sql(" select flower_id as flowerid,flower_url as dataURL from flower_imgs ");
		sql.append(" order by id desc ");
		return jdbcTemplate.queryForList(sql, FlowerImg.class);
	}
	
}

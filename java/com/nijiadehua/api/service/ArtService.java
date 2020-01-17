package com.nijiadehua.api.service;

import org.springframework.stereotype.Service;
import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.art.response.ArtResponse;

import com.nijiadehua.api.exception.ServiceException;


@Service
public class ArtService {
	
	private Logger log = Logger.getLogger(ArtService.class);
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public ArtResponse queryArtInfoByArtId(Long art_id) throws ServiceException{
		
		try {
			
		
			
			Sql queryUser = new Sql(" select id as art_id,real_name art_name,art_desc from artist_info where id = ? ");
			queryUser.addParam(art_id);
			ArtResponse art = jdbcTemplate.findObject(queryUser, ArtResponse.class);
			
			return art;
		}catch (Exception e) {
			e.printStackTrace();
			log.logError("名家查信息询失败",e);
			throw new ServiceException("名家信息查询失败："+e.getMessage());
		}
		
	}

	
	
}

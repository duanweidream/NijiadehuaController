package com.nijiadehua.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.address.response.AddressResponse;
import com.nijiadehua.api.exception.ServiceException;

/**
 * ClassName:FlowerInfoService</br>
 * Function: 地域信息 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2020-01-16 上午10:09:34</br>
 * 
 */
@Service
public class AddressService {

	private static Logger log = Logger.getLogger(AddressService.class);

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public List<AddressResponse> queryAdressListByUserId(Long user_id) throws ServiceException {
		try {
			Sql sql = new Sql(" select * from art_user_address where user_id = ? ORDER BY id ");
			sql.addParam(user_id);
			return jdbcTemplate.queryForList(sql, AddressResponse.class);

		} catch (Exception e) {
			log.logError("收货地址查询失败", e);
			throw new ServiceException("收货地址查询失败：" + e.getMessage());
		}

	}

}

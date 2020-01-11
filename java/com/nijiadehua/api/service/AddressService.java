package com.nijiadehua.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.address.request.AddressCreateRequest;
import com.nijiadehua.api.controller.v1.address.request.AddressUpdateRequest;
import com.nijiadehua.api.controller.v1.address.response.AddressResponse;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.util.JsonUtil;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;

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
			Sql sql = new Sql(" select * from art_user_address where user_id = ? and state = ? ORDER BY id ");
			sql.addParam(user_id);
			sql.addParam(1);
			return jdbcTemplate.queryForList(sql, AddressResponse.class);

		} catch (Exception e) {
			log.logError("收货地址查询失败", e);
			throw new ServiceException("收货地址查询失败：" + e.getMessage());
		}

	}
	
	public Long createUserAddress(String json) throws ServiceException {
		try {
			AddressCreateRequest addressCreateRequest = (AddressCreateRequest)JsonUtil.jsongToObject(json, AddressCreateRequest.class);
			if(StringUtil.isEmpty(addressCreateRequest) || StringUtil.isEmpty(addressCreateRequest.getUser_id())) {
				throw new ServiceException("缺少必须参数");
			}
			
			Date current_time = new Date();
			
			Sql sql = new Sql(" insert into art_user_address (user_id,delivery_name,delivery_phone,delivery_country,delivery_province,delivery_city,delivery_district,delivery_address,delivery_postal_code,create_time,update_time) values (?,?,?,?,?,?,?,?,?,?,?) ");
			sql.addParam(addressCreateRequest.getUser_id(),addressCreateRequest.getDelivery_name(),addressCreateRequest.getDelivery_phone(),addressCreateRequest.getDelivery_country(),addressCreateRequest.getDelivery_province(),addressCreateRequest.getDelivery_city(),addressCreateRequest.getDelivery_district(),addressCreateRequest.getDelivery_address(),addressCreateRequest.getDelivery_postal_code(),current_time,current_time);
			Long delivery_id = jdbcTemplate.saveObject(sql);
			
			if(delivery_id == null) {
				throw new ServiceException("数据保存失败");
			}
			
			return delivery_id;
		} catch (Exception e) {
			log.logError("收货地址添加失败", e);
			throw new ServiceException("收货地址添加失败：" + e.getMessage());
		}

	}
	
	public int updateUserAddress(String json) throws ServiceException {
		try {
			AddressUpdateRequest addressCreateRequest = (AddressUpdateRequest)JsonUtil.jsongToObject(json, AddressUpdateRequest.class);
			if(StringUtil.isEmpty(addressCreateRequest) || StringUtil.isEmpty(addressCreateRequest.getUser_id()) || StringUtil.isEmpty(addressCreateRequest.getDelivery_id())) {
				throw new ServiceException("缺少必须参数");
			}
			
			Date current_time = new Date();
			
			Sql sql = new Sql(" update art_user_address set delivery_name=?,delivery_phone=?,delivery_country=?,delivery_province=?,delivery_city=?,delivery_district=?,delivery_address=?,delivery_postal_code=?,update_time=? where delivery_id = ? and user_id = ? ");
			sql.addParam(addressCreateRequest.getDelivery_name(),addressCreateRequest.getDelivery_phone(),addressCreateRequest.getDelivery_country(),addressCreateRequest.getDelivery_province(),addressCreateRequest.getDelivery_city(),addressCreateRequest.getDelivery_district(),addressCreateRequest.getDelivery_address(),addressCreateRequest.getDelivery_postal_code(),current_time);
			sql.addParam(addressCreateRequest.getDelivery_id(),addressCreateRequest.getUser_id());
			int result = jdbcTemplate.updateObject(sql);
			
			if(result == 0) {
				throw new ServiceException("数据修改失败");
			}
			
			return result;
		} catch (Exception e) {
			log.logError("收货地址修改失败", e);
			throw new ServiceException("收货地址修改失败：" + e.getMessage());
		}

	}
	
	public int removeUserAddress(String json) throws ServiceException {
		try {
			 JSONObject jsonObject = JSONObject.fromObject(json);
			 if(StringUtil.isEmpty(jsonObject) || StringUtil.isEmpty(jsonObject.get("delivery_id")) || StringUtil.isEmpty(jsonObject.get("user_id"))) {
				throw new ServiceException("缺少必须参数");
			 }
			 
			Sql sql = new Sql(" delete from art_user_address where delivery_id = ? and user_id = ? ");
			sql.addParam(jsonObject.get("delivery_id"),jsonObject.get("user_id"));
			int result = jdbcTemplate.updateObject(sql);
			
			if(result == 0) {
				throw new ServiceException("数据删除失败");
			}
			
			return result;
		} catch (Exception e) {
			log.logError("收货地址删除失败", e);
			throw new ServiceException("收货地址删除失败：" + e.getMessage());
		}

	}
	
	public int collectUserAddress(String json) throws ServiceException {
		try {
			 JSONObject jsonObject = JSONObject.fromObject(json);
			 if(StringUtil.isEmpty(jsonObject) || StringUtil.isEmpty(jsonObject.get("delivery_id")) || StringUtil.isEmpty(jsonObject.get("user_id"))) {
				throw new ServiceException("缺少必须参数");
			 }
			 
			Sql sql = new Sql(" update art_user_address set is_collect = 1 where delivery_id = ? and user_id = ? ");
			sql.addParam(jsonObject.get("delivery_id"),jsonObject.get("user_id"));
			int result = jdbcTemplate.updateObject(sql);
			
			if(result == 0) {
				throw new ServiceException("数据更新失败");
			}
			
			return result;
		} catch (Exception e) {
			log.logError("收货地址默认设置失败", e);
			throw new ServiceException("收货地址默认设置失败：" + e.getMessage());
		}

	}
	
	
	
	
}

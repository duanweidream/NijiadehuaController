package com.wooboo.dsp.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.dao.base.BaseDao;

@Service
public class BaseService {
	@Autowired
    private BaseDao baseDao;
	
	public void saveObject(Object obj){
		baseDao.saveObject(obj);
	}
	
	public void saveOrUpdateObject(Object obj){
		baseDao.saveObject(obj);
	}
	public void updateObject(Object obj){
		baseDao.updateObject(obj);
	}
	
	public <T> T getObject(Serializable id, Class<T> toType) {
		return baseDao.getObject(id, toType);
	}
	public <T> T loadObject(Serializable id, Class<T> toType) {
		return baseDao.loadObject(id, toType);
	}
	public void deleteObject(Object obj){
		baseDao.deleteObject(obj);
	}
	public void deleteObject(Serializable id, Class clazz){
		baseDao.deleteObject(clazz, id);
	}
	public void savebatchObject(List list){
		baseDao.savebatchObject(list);
	}
}

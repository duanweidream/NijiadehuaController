package com.wooboo.dsp.dao.base;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wooboo.dsp.system.util.statement.mysql.StatementFactoryImpl;

@Repository
public class BaseDao {
	
	public StatementFactoryImpl stmsFactory=StatementFactoryImpl.getInstance();
	public HibernateDao hibernateDao;

	public StatementFactoryImpl getStmsFactory() {
		return stmsFactory;
	}

	public void setStmsFactory(StatementFactoryImpl stmsFactory) {
		this.stmsFactory = stmsFactory;
	}

	public HibernateDao getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public void saveObject(Object obj) {
		hibernateDao.createObject(obj);
	}
	public void saveOrUpdate(Object obj) {
		hibernateDao.saveOrUpdate(obj);
	}
	
	public void savebatchObject(Collection<Object> entities) {
		hibernateDao.saveObjects(entities);
	}

	/**删除数据*/
    public void deleteObject(Class clazz,Serializable id){
    	hibernateDao.deleteObject(clazz, id);
    }
	public void deleteObject(Object obj) {
		hibernateDao.deleteObject(obj);
	}
	public void deleteObjects(Collection entities) {
		hibernateDao.deleteObjects(entities);
	}
	public void deleteObject(Serializable id, Class clazz){
		hibernateDao.deleteObject(clazz, id);
	}
	/**更新数据*/
	public void updateObject(Object obj) {
		hibernateDao.updateObject(obj);
	}
    

	
    /**查询单个数据*/
	public <T> T getObject(Serializable id, Class <T> toType) {
		return hibernateDao.get(id, toType);
	}
	public <T> T loadObject(Serializable id, Class <T> toType){
		return hibernateDao.load(id, toType);
	}
	public Object loadObject(Class clazz, Serializable id) {
		return hibernateDao.loadObject(clazz, id);
	}
	public Object getObject(Class clazz, Serializable id) {
		return hibernateDao.getObject(clazz, id);
	}
	
	

	@Autowired
	protected static JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		BaseDao.jdbcTemplate = jdbcTemplate;
	}


	
	
}

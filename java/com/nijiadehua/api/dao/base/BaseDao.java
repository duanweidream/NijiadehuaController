package com.nijiadehua.api.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nijiadehua.api.base.system.util.statement.mysql.StatementFactoryImpl;

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
	
	/**
	 * 查询方法返回指定类型的实体
	 * <p>
	 *      必须返回唯一值
	 * </p >
	 * @method querySingleEntity
	 * @param sql 要执行的SQL语句
	 * @param clazz 指定返回的数据类型
	 * @return T 返回的实体类型
	 * @throws SQLException
	 * @author WangYG
	 * @date 2019-08-29 19:09:04
	 */
	public <T> T querySingleEntity(String sql, Class<T> clazz) throws SQLException {
	    try {
	        RowMapper<T> entityMapper = BeanPropertyRowMapper.newInstance(clazz);
	        return jdbcTemplate.queryForObject(sql,entityMapper);
	       
	    }catch (EmptyResultDataAccessException e){
	       // log.error("调用querySingleEntity(sql,clazz)方法查询结果为空!");
	        return null;
	    } catch (DataAccessException e) {
	       // log.error("调用querySingleEntity(sql,clazz)方法抛出异常!");
	        e.printStackTrace();
	        throw new SQLException("调用querySingleEntity(sql,clazz)方法抛出异常!");
	    }
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

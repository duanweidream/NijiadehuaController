package com.nijiadehua.api.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.nijiadehua.api.base.system.util.Page.Page;
import com.nijiadehua.api.base.system.util.statement.Statement;

public interface HibernateDao {
	//save object
	public void createObject(Object obj);
	public void saveOrUpdate(Object obj);
	public void saveObjects(Collection<Object> entities);
	//delete object
	public void deleteObject(Class clazz,Serializable id);
	public void deleteObject(Object obj);
	public void deleteObjects(Collection entities);
	//update
	public void updateObject(Object obj);
	
	/**
     * 根据指定的原生SQL和参数 查询 返回对应的java实体
     * @param sql 原生SQL查询语句
     * @param params SQL参数数组
     * @param clazz 实体类
     * @return List
     */
    public List executeNativeSqlQueryForClass(final String sql, final Object[] params, final Class clazz);
	
	//find get load search
	public <T> T get(Serializable id, Class <T> toType);
	public <T> T load(Serializable id, Class <T> toType);
	public Object loadObject(Class clazz, Serializable id);
	public Object getObject(Class clazz, Serializable id);
	//about statement
	public int update(Statement statement);
	public Object find(Statement statement);
	public <T> T find(Statement statement, Class <T> toType);
	public Integer queryCount(Statement statement);
	public List query(Statement statement);
	public List query(Statement statement, int maxResult);
	public void search(Statement statement, Page page);
}

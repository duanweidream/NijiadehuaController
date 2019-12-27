package com.nijiadehua.api.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.nijiadehua.api.base.system.util.Page.Page;
import com.nijiadehua.api.base.system.util.statement.Statement;
import com.nijiadehua.api.util.NumberUtil;

@Repository
public class HibernateDaoImpl extends HibernateDaoSupport implements HibernateDao{
	
	
	/**保存数据对象*/
	public void createObject(Object obj) {
		getHibernateTemplate().save(obj);
	}
	
	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}
	public void saveObjects(Collection<Object> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}
	
	/**删除数据*/
    public void deleteObject(Class clazz,Serializable id){
    	Object obj = get(id,clazz);
    	getHibernateTemplate().delete(obj);
    }
	public void deleteObject(Object obj) {
		getHibernateTemplate().delete(obj);
	}
	public void deleteObjects(Collection entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}
	
	/**更新数据*/
	public void updateObject(Object obj) {
		getHibernateTemplate().update(obj);
	}

    /**查询单个数据*/
	public <T> T get(Serializable id, Class <T> toType) {
		return (id==null)?null:(T)getHibernateTemplate().get(toType, id);
	}
	public <T> T load(Serializable id, Class <T> toType){
		return (id==null)?null:(T)getHibernateTemplate().load(toType, id);
	}
	public Object loadObject(Class clazz, Serializable id) {
		return getHibernateTemplate().load(clazz, id);
	}
	public Object getObject(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	/***/
	public int update(Statement statement) {
		Query query = createQuery(false, this.getSession(), statement);
		statement.close();
		return query.executeUpdate();
	}
	
	public Object find(Statement statement) {
		Query query = createQuery(false, this.getSession(), statement);
		query.setMaxResults(1);
		statement.close();
		return query.uniqueResult();
	}
	
	public <T> T find(Statement statement, Class <T> toType) {
		Query query = createQuery(false, this.getSession(), statement);
		statement.close();
		return (T)query.uniqueResult();
	}
	
	public Integer queryCount(Statement statement) {
		Query query = createQuery(false, getSession(), statement);
		statement.close();
		Object count = query.uniqueResult();
		return NumberUtil.getInt(count, 0);
	}
	
	public List query(Statement statement) {
		Query query = createQuery(false, this.getSession(), statement);
		if(statement.isNativeSQL()){this.setSQLQuery(query, statement);}
		statement.close();
		return query.list();
	}

	
	public List query(Statement statement, int maxResult) {
		Query query = createQuery(false, this.getSession(), statement);
		if(statement.isNativeSQL()){this.setSQLQuery(query, statement);}
		query.setMaxResults(maxResult);
		statement.close();
		return query.list();
	}	
	private Query createQuery(boolean countSQL, Session session, Statement statement) {
		String strSQL = countSQL? statement.countSQL() : statement.toString();
		List values = countSQL? statement.getCountParams() : statement.getParams();
		Query query = statement.isNativeSQL() ? session.createSQLQuery(strSQL) : session.createQuery(strSQL);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				Object value = values.get(i);
				query.setParameter(i, value);
			}
		}
		//设置二级缓存
		if(!countSQL && statement.isCacheable()){
			String cacheRegin = statement.getCacheRegion();
			query.setCacheable(true);
			if(cacheRegin!=null){
				query.setCacheRegion(cacheRegin);
			}
		}
		return query;
	}
	
	private int queryCount(Session session,  Statement statement, Page page) {
		//String sqlStr = statement.countSQL();		
		Query query = createQuery(true, session, statement);
		Object count = query.uniqueResult();
		return NumberUtil.getInt(count, 0);
	}
	public void search(Statement statement, Page page) {
		Session session = this.getSession();
		Query query = createQuery(false, session, statement);
		if(statement.isNativeSQL()){this.setSQLQuery(query, statement);}
		if (page != null) {
			query.setFirstResult(page.getStartRow());
			query.setMaxResults(page.getPageSize());
			if (page.isNew()) {
				page.setTotalRows(queryCount(session, statement, page));
			}
		}
		page.setList(query.list());
		statement.close();
	}
	private void setSQLQuery(Query query, Statement statement){
		addEntity(query, statement);
		setScalar(query, statement);
	}
	private void addEntity(Query query, Statement statement) {
		if (query != null && statement != null) {
			SQLQuery q = (SQLQuery) query;
			Map<String, Class> entitys = statement.getEntitys();
			if(null!=entitys){
				Iterator iParams = entitys.keySet().iterator();
				String name = null;
				while (iParams.hasNext()) {
					name = (String) iParams.next();
					q.addEntity(name, entitys.get(name));
				}
			}
		}
	}
	private void setScalar(Query query,  Statement statement) {
		if (query != null && statement != null) {
			List scalars = statement.getScalars();
			if(null!=scalars){
				SQLQuery q = (SQLQuery) query;
				for (int i = 0; i < scalars.size(); i = i + 2) {
					String name = (String) scalars.get(i);
					q.addScalar(name, (Type) scalars.get(i + 1));
				}
			}
		}
	}
	
	
	
}

package com.hualouhui.weixin.base.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.util.StringUtil;

public class JdbcTemplate {
	
	public static final Logger logger = Logger.getLogger(JdbcTemplate.class);
	private Pool pool = null;
	
	public JdbcTemplate(){
		 pool=ConnectionPool.getInstance();
	}
	
	public <T> T findObject(Sql sql,RowMapper mapper,Class<T> toType) throws ApiError{
	        try{
	        	Connection connection =  pool.getConnection();
	            PreparedStatement pStat = connection.prepareStatement(sql.toString());
	            for(int i=1;i<=sql.params.size();i++){
	            	setParameter(pStat, i, sql.params.get(i-1));
	            }
	            ResultSet rs = pStat.executeQuery();
	            Object o = mapper.mapRow(rs);
	            pStat.close();
	            pool.returnConnection(connection);
	            return (T)o;
	        }catch(Exception e) {
	        	e.printStackTrace();
	            throw ApiError.Type.INTERNAL_ERROR.toException();
	        }
	
	}
	
	public void search(Page page,Sql sql,Class toType) throws ApiError{
		//page.totalCount=queryCount(sql);
		page.setTotalCount(queryCount(sql));
		sql.limit(page.startIndex, page.itemCount);
		page.list=queryForList(sql, toType);
	}
	
    public Integer findInteger(Sql sql)throws ApiError{
		Integer count=0;
 		try{
 			    logger.debug(sql.toString());
 	        	Connection connection =  pool.getConnection();
 	            PreparedStatement pStat = connection.prepareStatement(sql.toString());
 	            for(int i=1;i<=sql.params.size();i++){
 	            	setParameter(pStat, i, sql.params.get(i-1));
 	            }
 	            ResultSet rs = pStat.executeQuery();
 	           if (rs.next()) {
 	        	   count= rs.getInt(1);
 	            }
 	            pStat.close();
 	            pool.returnConnection(connection);
 	        }catch(Exception e) {
 	        	e.printStackTrace();
 	            throw ApiError.Type.INTERNAL_ERROR.toException();
 	        }
		return count;             
	
    }
	
	public Integer queryCount(Sql sql) throws ApiError{
		Integer count=0;
 		try{
 			    logger.debug(sql.countSql());
 	        	Connection connection =  pool.getConnection();
 	            PreparedStatement pStat = connection.prepareStatement(sql.countSql());
 	            for(int i=1;i<=sql.params.size();i++){
 	            	setParameter(pStat, i, sql.params.get(i-1));
 	            }
 	            ResultSet rs = pStat.executeQuery();
 	           if (rs.next()) {
 	        	   count= rs.getInt(1);
 	            }
 	            pStat.close();
 	            pool.returnConnection(connection);
 	        }catch(Exception e) {
 	        	e.printStackTrace();
 	            throw ApiError.Type.INTERNAL_ERROR.toException();
 	        }
		return count;             
	}
	public String findForString(Sql sql) throws ApiError{
		String str=null;
 		try{
 			    logger.debug(sql.toString());
 	        	Connection connection =  pool.getConnection();
 	            PreparedStatement pStat = connection.prepareStatement(sql.toString());
 	            for(int i=1;i<=sql.params.size();i++){
 	            	setParameter(pStat, i, sql.params.get(i-1));
 	            }
 	            ResultSet rs = pStat.executeQuery();
 	           if (rs.next()) {
 	        	   str= rs.getString(1);
 	            }
 	            pStat.close();
 	            pool.returnConnection(connection);
 	        }catch(Exception e) {
 	        	e.printStackTrace();
 	            throw ApiError.Type.INTERNAL_ERROR.toException();
 	        }
		return str;
	}
	

	public <T> List<T> queryForList(Sql sql, Class<T> toType) throws ApiError {
		List<T> results = new ArrayList<T>(); 
		try{
			    logger.debug(sql.toString());
	        	Connection connection =  pool.getConnection();
	            PreparedStatement pStat = connection.prepareStatement(sql.toString());
	            for(int i=1;i<=sql.params.size();i++){
	            	setParameter(pStat, i, sql.params.get(i-1));
	            }
	            ResultSet rs = pStat.executeQuery();
	            BeanHandler<T> handler = new BeanHandler<T>(toType);
	            results = handler.toBeanList(rs);
	            pStat.close();
	            pool.returnConnection(connection);
	            return results;
	        }catch(Exception e) {
	        	e.printStackTrace();
	            throw ApiError.Type.INTERNAL_ERROR.toException();
	        }
	}
	

	
	public <T> List<T> queryForList(Sql sql, Class<T> toType,Integer count) throws ApiError {
		List<T> results = new ArrayList<T>(); 
		try{
			    sql.limit(0, count);
			    logger.debug(sql.toString());
                
			    Connection connection =  pool.getConnection();
	            PreparedStatement pStat = connection.prepareStatement(sql.toString());
	            for(int i=1;i<=sql.params.size();i++){
	            	setParameter(pStat, i, sql.params.get(i-1));
	            }
	            ResultSet rs = pStat.executeQuery();
	            BeanHandler<T> handler = new BeanHandler<T>(toType);
	            results = handler.toBeanList(rs);
	            pStat.close();
	            pool.returnConnection(connection);
	            return results;
	        }catch(Exception e) {
	        	e.printStackTrace();
	            throw ApiError.Type.INTERNAL_ERROR.toException();
	        }
	}
	
	public <T> T findObject(Sql sql,Class <T> toType) throws ApiError{
        try{
        	logger.debug(sql.toString());
        	Connection connection =  pool.getConnection();
            PreparedStatement pStat = connection.prepareStatement(sql.toString());
            for(int i=1;i<=sql.params.size();i++){
            	setParameter(pStat, i, sql.params.get(i-1));
            }
            ResultSet rs = pStat.executeQuery();
            BeanHandler<T> handler = new BeanHandler<T>(toType);
            Object o=handler.handle(rs);
            pStat.close();
            pool.returnConnection(connection);
            return (T)o;
        }catch(Exception e) {
        	e.printStackTrace();
            throw ApiError.Type.INTERNAL_ERROR.toException();
        }
    }
	
	public  Object[] findForArray(Sql sql) throws ApiError{
		try{
        	logger.debug(sql.toString());
        	Connection connection =  pool.getConnection();
            PreparedStatement pStat = connection.prepareStatement(sql.toString());
            for(int i=1;i<=sql.params.size();i++){
            	setParameter(pStat, i, sql.params.get(i-1));
            }
            ResultSet rs = pStat.executeQuery();
            Object[] obj = null;
            while(rs.next()){
            	int size = rs.getMetaData().getColumnCount();
            	obj = new Object[size];
                for(int i=0;i<size;i++){
                	obj[i]=rs.getObject(i+1);
                }
            }
            pStat.close();
            pool.returnConnection(connection);
            return obj;
        }catch(Exception e) {
        	e.printStackTrace();
            throw ApiError.Type.INTERNAL_ERROR.toException();
        }
	}
	
	
	
	
	
	public int updateObject(Sql sql) throws ApiError{
        try{
        	logger.debug(sql.toString());
        	Connection connection =  pool.getConnection();
            PreparedStatement pStat = connection.prepareStatement(sql.toString());
            for(int i=1;i<=sql.params.size();i++){
            	setParameter(pStat, i, sql.params.get(i-1));
            }
            int i =pStat.executeUpdate();
            pStat.close();
            pool.returnConnection(connection);
            return i;
        }catch(Exception e) {
        	e.printStackTrace();
            throw ApiError.Type.INTERNAL_ERROR.toException();
        }
    }
	private void setParameter(PreparedStatement stms,int i,Object value) throws SQLException{
		if(value==null){
			//stms.setNull(i, java.sql.Types.VARCHAR);
			stms.setNull(i, java.sql.Types.JAVA_OBJECT);
		}
		if (value instanceof String) {
		    stms.setString(i, StringUtil.dealNull(value));
		}else if(value instanceof Date){
			Date d = value==null?new Date():(Date)value;
			stms.setTimestamp(i,  new Timestamp(d.getTime()));
		}else if(value instanceof Integer){
			stms.setInt(i, (Integer) value);
		}else if(value instanceof Long){
			stms.setLong(i, (Long)value);
		}else if(value instanceof Short){
			stms.setShort(i,(Short)value);
		}else if(value instanceof Double){
			stms.setDouble(i, (Double)value);
		}else if(value instanceof Float){
			stms.setFloat(i, (Float)value);
		}
	}
	public Long saveObject(Sql sql) throws ApiError{
		Long id = null;
		try{
			logger.debug(sql.toString());
        	Connection connection =  pool.getConnection();
            PreparedStatement pStat = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            for(int i=1;i<=sql.params.size();i++){
            	setParameter(pStat, i, sql.params.get(i-1));
            }
            int row = pStat.executeUpdate();
            if (row > 0) {
	    		ResultSet rs = pStat.getGeneratedKeys();
	    		if (rs != null) {
	    			if (rs.next()) {
	    				id = rs.getLong(1);
	    			}
	    			rs.close();
	    		}
	    	}
            pStat.close();
            pool.returnConnection(connection);
            return id;
        }catch(Exception e) {
        	e.printStackTrace();
            throw ApiError.Type.INTERNAL_ERROR.toException();
        }
	}
	
    public Integer findCount(Sql sql)throws ApiError{
    	logger.debug(sql.toString());
    	Integer count=0;
		try{
			    Connection connection =  pool.getConnection();
 	            PreparedStatement pStat = connection.prepareStatement(sql.toString());
 	            for(int i=1;i<=sql.params.size();i++){
 	            	setParameter(pStat, i, sql.params.get(i-1));
 	            }
 	            ResultSet rs = pStat.executeQuery();
 	           if (rs.next()) {
 	        	   count= rs.getInt(1);
 	            }
 	            pStat.close();
 	            pool.returnConnection(connection);
 	        }catch(Exception e) {
 	        	//pool.returnConnection(connection);
 	        	e.printStackTrace();
 	            throw ApiError.Type.INTERNAL_ERROR.toException();
 	        }
		return count;             
	
    }
	
	
}

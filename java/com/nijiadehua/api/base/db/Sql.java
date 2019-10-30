package com.nijiadehua.api.base.db;

import java.util.ArrayList;
import java.util.List;

import com.nijiadehua.api.util.StringUtil;

public class Sql {
	public static String BLANK="  ";
	public String select,limit;
	public StringBuffer sbuf = new StringBuffer();
	public List<Object> params = new ArrayList<Object>();
	
	public Sql(){
		
	}
	public Sql(String sql){
		sbuf.append(sql);
	}
	
	public void addParam(Object... objects){
		for(Object obj:objects){
			params.add(obj);
		}
	}
	public void limit(Integer begin,Integer end){
		sbuf.append(" LIMIT "+begin+", "+end+"");
	}
	public void append(String sql){
		sbuf.append(sql);
	}
	
	public String toString(){
		return sbuf.toString();
	}
	//example append("and","userName","=","test");
	public void append(String logic,String columnName,String operate,Object value){
		if(!StringUtil.isEmpty(value)){
			sbuf.append(BLANK+logic+BLANK+columnName+BLANK+operate+BLANK+"?");
			addParam(value);
		}
	}
	/* 追加Like条件 */
	public void appendLike(String model, String logical, String fieldname, Object value) {
		if (!StringUtil.isEmpty(value)) {
			StringBuffer bfSQL = new StringBuffer();
			if (StringUtil.isEmpty(model)) {
				append(logical, fieldname, "like", "%"+value+"%");
				return;
			}
			
			if ("^".equals(model)) {
				value = value + "%";
			} else if ("$".equals(value)) {
				value = "%" + value;
			}
			bfSQL.append(logical);
			bfSQL.append(BLANK);
			bfSQL.append(fieldname);
			bfSQL.append(BLANK);
			bfSQL.append("like");
			bfSQL.append(BLANK);
			bfSQL.append("?");
			sbuf.append(bfSQL.toString());
			addParam(value);
		}
	}
	public void appendBetween(String logical, String fieldname, Object from, Object to) {
		StringBuffer bfSQL = new StringBuffer();
		if (!StringUtil.isEmpty(from) && !StringUtil.isEmpty(to)) {
			if (from instanceof String) {
				bfSQL.append(BLANK);
				bfSQL.append(logical);
				bfSQL.append(BLANK);
				bfSQL.append(fieldname);
				bfSQL.append(">=? AND ");
				bfSQL.append(fieldname);
				bfSQL.append("<=?");
				bfSQL.append(BLANK);
			} else {
				bfSQL.append(BLANK);
				bfSQL.append(logical);
				bfSQL.append(BLANK);
				bfSQL.append(fieldname);
				bfSQL.append(" between ? and ?");
				bfSQL.append(BLANK);
			}
			sbuf.append(bfSQL);
			addParam(from,to);
			//appendSQL(condition, logical, bfSQL.toString(), from, to);
		} else if (!StringUtil.isEmpty(from)) {
			append(logical, fieldname, ">=", from);
		} else {
			append(logical, fieldname, "<=", to);
		}
	}
	
	
	public void appendIn(String logical, String fieldname, Object[] objs) {		
		if (null!=objs&&objs.length>0) {
			
			StringBuffer bfSQL = new StringBuffer();
			bfSQL.append(BLANK);
			bfSQL.append(logical);
			bfSQL.append(BLANK);
			bfSQL.append(fieldname);
			bfSQL.append(BLANK);
			bfSQL.append(" IN (");
			boolean b = true;
			for(Object obj:objs){
				bfSQL.append(b?"?":",?");
				if(b){b=false;}
			}
			bfSQL.append(") ");
			sbuf.append(bfSQL.toString());
            for(Object obj:objs){
            	addParam(obj);
			}
		}
	}
	
	public void appendNotIn(String logical, String fieldname, List<?> ids) {
		if (!StringUtil.isEmpty(ids)&&!ids.isEmpty()) {
			StringBuffer bfSQL = new StringBuffer();
			bfSQL.append(logical);
			bfSQL.append(BLANK);
			bfSQL.append(fieldname);
			bfSQL.append(BLANK);
			bfSQL.append("NOT IN (");
			boolean b = true;
			for(Object obj:ids){
				bfSQL.append(b?"?":",?");
				if(b){b=false;}
			}
			bfSQL.append(")");
			sbuf.append(bfSQL.toString());
            for(Object obj:ids){
            	addParam(obj);
			}
		}
	}
	
	public String countSql(){
		StringBuffer sbuf = new StringBuffer("SELECT count(*) from ( ");
        sbuf.append(toString());
        sbuf.append(" ) as tmp") ;
        return sbuf.toString();
	}
	
}

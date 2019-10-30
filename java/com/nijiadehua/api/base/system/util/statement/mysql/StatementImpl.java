package com.nijiadehua.api.base.system.util.statement.mysql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nijiadehua.api.base.system.util.Logger;
import com.nijiadehua.api.base.system.util.statement.Statement;



public class StatementImpl implements Statement {

	private int paraCount = 0;// select 语句中参数个数,用于count(*)设置参数

	private StringBuffer from = null;

	private boolean isNative;

	private String cacheRegion = null;

	private boolean cacheable = false;
	
	private String where = null;
	
	private String countColumn = null;

	private List listParam = new ArrayList();

	private List scalars = new ArrayList();

	private Map<String, Class> entitys = new LinkedHashMap<String, Class>();

	private StringBuffer select = new StringBuffer();

	private StringBuffer condition = new StringBuffer();

	private StringBuffer bfOrder = new StringBuffer();

	private StringBuffer bfGroup = new StringBuffer();;

	private StringBuffer bfHaving = new StringBuffer();

	private final Logger log = Logger.getLogger(StatementImpl.class);// LogFactory.getLog(StatementImpl.class);

	public StatementImpl(String sql, String where) {
		if (!isNull(sql)) {
			select.append(sql);
		}
		this.where = where;
	}

	public StatementImpl(String sql) {
		this(sql, null);
	}

	/* 追加SQL条件 */
	public void append(String sql) {
		condition.append(SPACE);
		condition.append(sql);
	}

	/* 动态追加条件 */
	public void append(String fieldname, String operator, Object value) {
		append(null, fieldname, operator, value);
	}

	/* 动态追加条件 */
	public void append(String logical, String fieldname, String operator, Object value) {
		StringBuffer bfSQL = new StringBuffer();
		if (!isNull(value)) {
			if ("like".equalsIgnoreCase(operator)) {
				value = "%" + value + "%";
			}
			bfSQL.append(fieldname);
			bfSQL.append(SPACE);
			bfSQL.append(operator);
			bfSQL.append(SPACE);
			bfSQL.append("?");
			appendSQL(condition, logical, bfSQL.toString(), value);
		}
	}

	/* 追加Like条件 */
	public void appendLike(String model, String logical, String fieldname, Object value) {
		if (!isNull(value)) {
			StringBuffer bfSQL = new StringBuffer();
			if (isNull(model)) {
				append(logical, fieldname, "like", value);
				return;
			}
			if (LEFT.equals(model)) {
				value = value + "%";
			} else if (RIGHT.equals(value)) {
				value = "%" + value;
			}
			bfSQL.append(fieldname);
			bfSQL.append(SPACE);
			bfSQL.append("like");
			bfSQL.append(SPACE);
			bfSQL.append("?");
			appendSQL(condition, logical, bfSQL.toString(), value);
		}
	}

	/* and (a.cate like '001001%' or a.cate like '001002%') */
	public void appendLike(String model, String logical, String innerLogical, String fieldname, Object[] parms) {
		StringBuffer bfSQL = new StringBuffer();
		if (parms != null && parms.length > 0) {
			Object[] likeValues = new Object[parms.length];
			bfSQL.append("(");
			for (int i = 0; i < parms.length; i++) {
				String value = parms[i] instanceof String ? (String) parms[i] : parms[i].toString();
				if (i > 0) {
					bfSQL.append(SPACE);
					bfSQL.append(innerLogical);
					bfSQL.append(SPACE);
				}
				if (isNull(model)) {
					value = "%" + value + "%";
				} else if (LEFT.equals(model)) {
					value = value + "%";
				} else if (RIGHT.equals(value)) {
					value = "%" + value;
				}
				bfSQL.append(fieldname);
				bfSQL.append(SPACE);
				bfSQL.append("like");
				bfSQL.append(SPACE);
				bfSQL.append("?");
				likeValues[i] = value;
			}
			bfSQL.append(")");
			appendSQL(condition, logical, bfSQL.toString(), likeValues);
		}
	}
	/* and (a.name like 'test' or a.area like 'test') */
	public void appendLike(String model, String logical, String innerLogical, String[] fieldname, Object parms) {
		StringBuffer bfSQL = new StringBuffer();
        Object[] likeValues = new Object[fieldname.length];
		if (parms != null ) {
			String value =  parms instanceof String ? (String) parms : parms.toString();
			bfSQL.append("(");
			for(int i=0;i<fieldname.length;i++){
				if (i > 0) {
					bfSQL.append(SPACE);
					bfSQL.append(innerLogical);
					bfSQL.append(SPACE);
				}
				if (isNull(model)) {
					value = "%" + value + "%";
				} else if (LEFT.equals(model)) {
					value = value + "%";
				} else if (RIGHT.equals(value)) {
					value = "%" + value;
				}
				bfSQL.append(fieldname[i]);
				bfSQL.append(SPACE);
				bfSQL.append("like");
				bfSQL.append(SPACE);
				bfSQL.append("?");
				likeValues[i] = value;
			}
			bfSQL.append(")");
			appendSQL(condition, logical, bfSQL.toString(), likeValues);
		}
	}
	

	/* and (name='1' or name='2') */
	public void append(String logical, String innerLogical, String fieldname, String operator, Object[] parms) {
		StringBuffer bfSQL = new StringBuffer();
		if (parms != null && parms.length > 0) {
			bfSQL.append("(");
			for (int i = 0; i < parms.length; i++) {
				if (i > 0) {
					bfSQL.append(SPACE);
					bfSQL.append(innerLogical);
					bfSQL.append(SPACE);
				}
				bfSQL.append(fieldname);
				bfSQL.append(SPACE);
				bfSQL.append(operator);
				bfSQL.append(SPACE);
				bfSQL.append("?");
			}
			bfSQL.append(")");
			appendSQL(condition, logical, bfSQL.toString(), parms);
		}
	}

	/* a.money between 1 and 100 */
	public void appendBetween(String logical, String fieldname, Object from, Object to) {
		StringBuffer bfSQL = new StringBuffer();
		if (!isNull(from) && !isNull(to)) {
			if (from instanceof String) {
				bfSQL.append(fieldname);
				bfSQL.append(">=? and ");
				bfSQL.append(fieldname);
				bfSQL.append("<=?");
			} else {
				bfSQL.append(fieldname);
				bfSQL.append(" between ? and ?");
			}
			appendSQL(condition, logical, bfSQL.toString(), from, to);
		} else if (!isNull(from)) {
			append(logical, fieldname, ">=", from);
		} else {
			append(logical, fieldname, "<=", to);
		}
	}

	/* name in ('1','2') */
	public void appendIn(String logical, String fieldname, Object[] parms) {
		StringBuffer bfSQL = new StringBuffer();
		if (parms != null && parms.length > 0) {
			bfSQL.append(fieldname);
			bfSQL.append(SPACE);
			bfSQL.append("in");
			bfSQL.append(SPACE);
			bfSQL.append("(");
			for (int i = 0; i < parms.length; i++) {
				if (i > 0) {
					bfSQL.append(",");
				}
				bfSQL.append("?");
			}
			bfSQL.append(")");
			appendSQL(condition, logical, bfSQL.toString(), parms);
		}
	}
	public void appendInStatement(String logical, String fieldname, Statement stms,Object value ){
		StringBuffer bfSQL = new StringBuffer();
		if(stms!=null && value!=null){
			List params = stms.getParams();
			
			
			bfSQL.append(fieldname);
			bfSQL.append(SPACE);
			bfSQL.append("in");
			bfSQL.append(SPACE);
			bfSQL.append("(");
			bfSQL.append(stms.toString());
			bfSQL.append(")");
			
			appendSQL(condition, logical, bfSQL.toString(), params.toArray());
			
		}
	}
	/* name in ('1','2') */
	public void appendIn(String logical, String fieldname, Collection parms) {
		if (parms != null) {
			appendIn(logical, fieldname, parms.toArray());
		}
	}

	/* 追加From语句 */
	public void appendFrom(String fromSQL, Object...params) {
		if(from==null){
			from = new StringBuffer();
		}
//		from.append(fromSQL);
		appendSQL(from, null, fromSQL, params);
		freshCount();
	}
	
	public void appendFrom(Statement obj, String startSql, String endSql) {
		StringBuffer sql = new StringBuffer();
		if(!isNull(startSql))sql.append(startSql);
		sql.append(obj.toString());
		if(!isNull(endSql))sql.append(endSql);
		appendFrom(sql.toString(), obj.getArrayParams());
	}

	public void appendSelect(String sql) {
		select.append(sql);
	}

	/* 追加select语句 */
	public void appendSelect(Statement obj, String alias) {
		StringBuffer bfSQL = new StringBuffer();
		if (obj != null) {
			List params = obj.getParams();
//			if (!params.isEmpty()) {
				if ("select".equalsIgnoreCase(select.toString().trim())) {
					bfSQL.append(SPACE);
				} else {
					bfSQL.append(",");
				}
				bfSQL.append("(");
				bfSQL.append(obj);
				bfSQL.append(")");
				if (!isNull(alias)) {
					bfSQL.append(SPACE);
					bfSQL.append(alias);
				}
				appendSQL(select, null, bfSQL.toString(), params.toArray());
				freshCount();
//			}
		}
	}

	public void append(Statement obj, String logical, String startSql, String endSql) {
		StringBuffer bfSQL = new StringBuffer();
		if (obj != null) {
			List params = obj.getParams();
			if (!params.isEmpty()) {
				if (!isNull(startSql)) {
					bfSQL.append(startSql);
				}
				bfSQL.append(obj);
				if (!isNull(endSql)) {
					bfSQL.append(endSql);
				}
				appendSQL(condition, logical, bfSQL.toString(), params.toArray());
			}
		}
	}

	/* 只增加字查询中的条件 */
	public void appendCondition(Statement obj, String logical, String startSql, String endSql) {
		StringBuffer bfSQL = new StringBuffer();
		if (obj != null) {
			List params = obj.getParams();
			if (!params.isEmpty()) {
				if (!isNull(startSql)) {
					bfSQL.append(startSql);
				}
				bfSQL.append(obj.getConditon(true));
				if (!isNull(endSql)) {
					bfSQL.append(endSql);
				}
				appendSQL(condition, logical, bfSQL.toString(), params.toArray());
			}
		}
	}

	/* and (a.name is null or a.price > ?) */
	public void appendSQL(String logical, String subSQL, Object... parms) {
		if (parms != null) {
			List list = new ArrayList();
			for (int i = 0; i < parms.length; i++) {
				if (!isNull(parms[i])) {
					list.add(parms[i]);
				}
			}
			if (!list.isEmpty()) {
				appendSQL(condition, logical, subSQL, parms);
			}
		}
	}

	private void appendSQL(StringBuffer bfSQL, String logical, String sql, Object... parms) {
		if (!isNull(logical)) {
			bfSQL.append(SPACE);
			bfSQL.append(logical);
		}
		bfSQL.append(SPACE);
		bfSQL.append(sql);
		if (parms != null) {
			for (Object obj : parms) {
				listParam.add(obj);
			}
		}
	}

	public static boolean isNull(Object obj) {
		return obj == null || BLANK.equals(obj);
	}

	/* 获取where语句 */
	public String whereSQL() {
		StringBuffer bfSQL = new StringBuffer();
		if (!isNull(condition.toString())) {
			if (!isNull(where)) {
				bfSQL.append(where);
				bfSQL.append(SPACE);
			}
			bfSQL.append(condition.toString().trim());
			// bfSQL.append(condition.toString().replaceFirst("^(?im)\\s*(?:(and|or|not)\\s+)?",
			// ""));
		}
		return bfSQL.toString();
	}

	/* 获取from语句 */
	public String fromSQL() {
		StringBuffer bfSQL = new StringBuffer();
		if (!isNull(from)) {
			bfSQL.append(SPACE);
			bfSQL.append(from);
		} else {
			if (select.length() > 0) {
				int i = select.toString().toLowerCase().indexOf("from");
				if (i > -1) {
					bfSQL.append(select.substring(i));
				}
			}
		}
		return bfSQL.toString();
	}

	/* 获取count语句 */
	public String countSQL() {
		return countSQL(null);
	}

	public String countSQL(String alias) {
		StringBuffer bfSQL = new StringBuffer();
		if (countColumn!=null) {
			bfSQL.append("select count(");
			bfSQL.append(countColumn);
			bfSQL.append(") ");
			if(alias!=null){
				bfSQL.append("as");
				bfSQL.append(SPACE);
				bfSQL.append(alias);
			}
			bfSQL.append(" from (");			
			bfSQL.append(toString(false));
			bfSQL.append(") as tmp");
		} else if (bfGroup.length() > 0) {
			bfSQL.append("select count(*) ");
			if(alias!=null){
				bfSQL.append("as");
				bfSQL.append(SPACE);
				bfSQL.append(alias);
			}			 
			bfSQL.append(" from (");
			bfSQL.append(toString(false));
			bfSQL.append(") as tmp");
		} else {
			bfSQL.append("select count(*) ");
			if(alias!=null){
				bfSQL.append("as");
				bfSQL.append(SPACE);
				bfSQL.append(alias);
				bfSQL.append(SPACE);
			}			
			bfSQL.append(fromSQL().trim());
			bfSQL.append(SPACE);
			bfSQL.append(whereSQL());
			if (bfGroup.length() > 0) {
				bfSQL.append(" group by ");
				bfSQL.append(bfGroup);
			}
			if (bfHaving.length() > 0) {
				bfSQL.append(" having ");
				bfSQL.append(bfHaving);
			}
		}
		
		return replaceSQL(bfSQL);		
	}

	/* 获取整个SQL */
	public String toString() {
		return toString(true);
	}

	public String toString(boolean isOrder) {
		String where = whereSQL();
		StringBuffer bfSQL = new StringBuffer();
		if (select.length() > 0) {
			bfSQL.append(select);
		}
		if (!isNull(from)) {
			bfSQL.append(SPACE);
			bfSQL.append(from);
		}
		if (!isNull(where)) {
			bfSQL.append(SPACE);
			bfSQL.append(where);
		}
		if (bfGroup.length() > 0) {
			bfSQL.append(" group by ");
			bfSQL.append(bfGroup);
		}
		if (bfHaving.length() > 0) {
			bfSQL.append(" having ");
			bfSQL.append(bfHaving);
		}
		if (isOrder && bfOrder.length() > 0) {
			bfSQL.append(" order by ");
			bfSQL.append(bfOrder);
		}
		return replaceSQL(bfSQL);
	}

	public Statement addParam(Object value) {
		listParam.add(value);
		return this;
	}
	public Statement addParams(Object... objs) {
		for(Object obj:objs){
			listParam.add(obj);
		}
		return this;
	}
	public Statement putAll(List parms) {
		listParam.addAll(parms);
		return this;
	}

	public Statement addEntity(String s, Class cls) {
		entitys.put(s, cls);
		return this;
	}

	public Statement addOrderBy(String orderBy) {
		if (!isNull(orderBy)) {
			if (bfOrder.length() > 0) {
				bfOrder.append(", ");
				bfOrder.append(orderBy);
			} else {
				bfOrder.append(orderBy);
			}
		}
		return this;
	}

	public Statement addOrderBy(String[] orderBy) {
		if (orderBy != null) {
			for (int i = 0; i < orderBy.length; i += 2) {
				addOrderBy(orderBy[i] + " " + orderBy[i + 1]);
			}
		}
		return this;
	}

	public Statement addGroupBy(String groupBy) {
		if (!isNull(groupBy)) {
			if (bfGroup.length() > 0) {
				bfGroup.append(", ");
				bfGroup.append(groupBy);
			} else {
				bfGroup.append(groupBy);
			}
		}
		return this;
	}

	private void addHavingContion(String operation, String condition) {
		if (condition != null) {
			if (bfHaving.length() > 0) {
				bfHaving.append(" and " + condition);
			} else {
				bfHaving.append(" " + operation + " ");
				bfHaving.append(condition);
			}
		}
	}

	public Map<String, Class> getEntitys() {
		return entitys;
	}

	public List getScalars() {
		return scalars;
	}

	public void addScalars(Object... scalars) {
		if (scalars != null) {
			if (scalars.length % 2 == 0) {
				for (int i = 0; i < scalars.length; i = i + 2) {
					this.scalars.add(scalars[i]);
					this.scalars.add(scalars[i + 1]);
				}
			} else {
				log.logError("error add addScalar");
			}
		}
	}

	/* 更新count参数个数 */
	private void freshCount() {
		paraCount = listParam.size();
	}

	public List getParams() {
		return listParam;
	}
	
	public Object[] getArrayParams() {
		return listParam!=null ? listParam.toArray() : null;
	}

	public List getCountParams() {
//		2010.4.20 修改部分参数不对
//		if (paraCount > 0) {
//			List list = new ArrayList(listParam.size());
//			for (int i = paraCount; i < listParam.size(); i++) {
//				list.add(listParam.get(i));
//			}
//			return list;
//		} else
			return listParam;
	}

	private String replaceSQL(StringBuffer sql) {
		String str = "";
		if (sql != null) {
			str = sql.toString().replaceAll("(?im)where\\s+(?:(and|or|not))\\s+", "where ");
		}
		return str;
	}

	public String getConditon(boolean withoutLogic) {
		String str = whereSQL();
		if (withoutLogic && str != null) {
			str = str.replaceFirst("^(?im)\\s*(?:(and|or|not))\\s+", "").replaceFirst("^(?im)\\s*where\\s+(?:(and|or|not))\\s+", "");
		}
		return str;
	}

	public String getCacheRegion() {
		return cacheRegion;
	}

	public void setCacheRegion(String cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

	public boolean isCacheable() {
		return cacheable;
	}

	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	public void setCacheable(boolean cacheable, String cacheRegion) {
		setCacheable(cacheable);
		setCacheRegion(cacheRegion);
	}

	public void setNativeSQL(boolean isNative) {
		this.isNative = isNative;
	}

	public boolean isNativeSQL() {
		return isNative;
	}

	public void close() {
		from = null;
		where = null;
		listParam = null;
		scalars = null;
		select = null;
		condition = null;
		bfOrder = null;
		bfGroup = null;
		bfHaving = null;
		if (entitys != null) {
			entitys.clear();
			entitys = null;
		}
	}

	public void setCountColmn(String colName) {
		this.countColumn = colName;		
	}

	public boolean addCountEntity() {
		return countColumn == null;
	}

	public void setParaCount(int paraCount) {
		this.paraCount = paraCount;
	}

	

}

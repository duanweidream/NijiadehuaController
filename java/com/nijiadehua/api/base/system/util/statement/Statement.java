package com.nijiadehua.api.base.system.util.statement;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface Statement {

	public final static String LEFT = "^"; //like左匹配('a%')
	public final static String RIGHT = "$";//like右匹配('%a')	
	public final static String COUNT_ALIAS = "countAlias";//like右匹配('%a')	
	public final static String SPACE = " "; //空格	
	public final static String BLANK = ""; //空串
	
	/**
	 * 追加SQL语句
	 * @param sql SQL语句
	 */
	public void append(String sql);

	/**
	 * 根据字段名和参数值动态追加SQL语句
	 * @param fieldname 字段名
	 * @param operator 操作符
	 * @param value 参数值
	 */
	public void append(String fieldname, String operator, Object value);

	/**
	 * 根据字段名和参数值动态追加SQL语句
	 * @param logical 逻辑运算符
	 * @param fieldname 字段名
	 * @param operator 操作符
	 * @param value 参数值
	 */
	public void append(String logical, String fieldname, String operator, Object value);

	/**
	 * 根据字段名和参数值动态追加Like语句
	 * @param model 匹配模式(null全匹配)
	 * @param logical  逻辑运算符
	 * @param fieldname 字段名
	 * @param value 参数值
	 */
	public void appendLike(String model, String logical, String fieldname, Object value);

	/**
	 * 根据字段名和参数列表值动态追加Like语句 exp: and (a.cate like '001001%' or a.cate like '001002%') 
	 * @param model 匹配模式(null全匹配)
	 * @param logical 逻辑运算符
	 * @param innerLogical 参数间的逻辑运算符
	 * @param fieldname 字段名
	 * @param parms 参数值列表
	 */
	public void appendLike(String model, String logical, String innerLogical, String fieldname, Object[] parms);
    
	/**
	 * 根据字段名和参数列表值动态追加Like语句 exp: and (a.name like '足球%' or a.areaname like '足球%') 
	 * @param model 匹配模式(null全匹配)
	 * @param logical 逻辑运算符
	 * @param innerLogical 参数间的逻辑运算符
	 * @param fieldname 字段名数组
	 * @param parms 参数
	 */
	public void appendLike(String model, String logical, String innerLogical, String[] fieldname, Object parms);
	
	/**
	 * 根据字段名和参数列表值动态追加SQL语句 exp: and (name='1' or name='2')
	 * @param logical 逻辑运算符
	 * @param innerLogical 参数间的逻辑运算符
	 * @param fieldname 字段名
	 * @param operator 操作符
	 * @param parms 参数值列表
	 */
	public void append(String logical, String innerLogical, String fieldname, String operator, Object[] parms);

	/**
	 * 根据字段名和参数值动态追加Between语句 exp: a.money between 1 and 100
	 * @param logical 逻辑运算符
	 * @param fieldname 字段名
	 * @param from 起始参数
	 * @param to 结束参数
	 */
	public void appendBetween(String logical, String fieldname, Object from, Object to);

	
	/**
	 * @功能描述:in连接追加一个statement
	 * author：luo
	 * date：2011-11-11 上午10:49:18 
	 * @param logical
	 * @param fieldname
	 * @param stms
	 * @param value
	 * void 
	 */
	public void appendInStatement(String logical, String fieldname, Statement stms,Object value );
	
	
	/**
	 * 根据字段名和参数值动态追加In语句 exp: name in ('1','2')
	 * @param logical 逻辑运算符
	 * @param fieldname 字段名
	 * @param parms 参数值列表
	 */
	public void appendIn(String logical, String fieldname, Object[] parms);

	/**
	 * 根据字段名和参数值动态追加In语句 exp: name in ('1','2')
	 * @param logical 逻辑运算符
	 * @param fieldname 字段名
	 * @param parms 参数值列表
	 */
	public void appendIn(String logical, String fieldname, Collection parms);

	/**
	 * 静态追加SQL语句到From字句中
	 * @param fromSQL
	 * @param params
	 */
	public void appendFrom(String fromSQL, Object...params);
	
	/**
	 * 静态追加SQL语句到From字句中
	 * @param obj
	 * @param startSql
	 * @param endSql
	 */
	public void appendFrom(Statement obj, String startSql, String endSql);

	/**
	 * 静态追加SQL语句到Select中
	 * @param sql
	 */
	public void appendSelect(String sql);

	/**
	 * 动态追加嵌套子SQL语句
	 * @param obj 子SQL语句
	 * @param alias 别名
	 */
	public void appendSelect(Statement obj, String alias);

	/**
	 * 动态追加嵌套子SQL语句
	 * @param obj 子SQL语句
	 * @param logical 逻辑运算符
	 * @param startSql 该语句追加到obj前
	 * @param endSql 该语句追加到obj后
	 */
	public void append(Statement obj, String logical, String startSql, String endSql);

	/**
	 * 动态追加嵌套子SQL的条件(where部分)语句
	 * @param obj 子SQL语句
	 * @param logical 逻辑运算符
	 * @param startSql 该语句追加到obj前
	 * @param endSql 该语句追加到obj后
	 */
	public void appendCondition(Statement obj, String logical, String startSql, String endSql);

	/**
	 * 动态追加SQL语句(exp:and (a.name is null or a.price > ?)
	 * @param logical 逻辑运算符
	 * @param subSQL sql语句
	 * @param parms 参数列表
	 */
	public void appendSQL(String logical, String subSQL, Object... parms);

	/**
	 * 获取where子句
	 * @return
	 */
	public String whereSQL();

	/**
	 * 获取from子名
	 * @return
	 */
	public String fromSQL();

	/**
	 * 获取count字句(及本次查询记录条数)
	 * @return
	 */
	public String countSQL();
	
	/**
	 * 获取count()语句
	 * @param alias
	 * @return
	 */
	public String countSQL(String alias);
	
	/**
	 * 追加参数
	 * @author mao
	 * @date Feb 19, 2009
	 * @param value 参数值
	 * @return
	 */
	public Statement addParam(Object value);

	public Statement addParams(Object... objs);
	
	/**
	 * 追加多个参数
	 * @param parms 参数列表
	 * @return
	 */
	public Statement putAll(List parms);

	/**
	 * 设置查询实体别名，参考Hibernate的addEntity
	 */
	public Statement addEntity(String s, Class cls);

	/**
	 * 追加排序
	 */
	public Statement addOrderBy(String orderBy);
	
	/**
	 * 追加排序
	 * @param orderBy 排序语句(索引    0:例名 1:升或降序 2 例名 3 升或降序 ..类推)
	 * @return 语句本身
	 */
	public Statement addOrderBy(String[] orderBy);

	/**
	 * 追加分组
	 */
	public Statement addGroupBy(String groupBy);

	/**
	 * 获取别名实体Entitys
	 */
	public Map<String, Class> getEntitys();

	/**
	 * 获取列类型
	 */
	public List getScalars();

	/**
	 * 给本地SQL设置每列的返回类型(格式: 列名， 列类型)
	 */
	public void addScalars(Object... scalars);

	/**
	 * 获取参数列表
	 */
	public List getParams();
	
	/**
	 * 获取参数组
	 */
	public Object [] getArrayParams();

	/**
	 * 获取分页count的参数
	 */
	public List getCountParams();

	/**
	 * 获取没有逻辑运算的条件
	 */
	public String getConditon(boolean withoutLogic);

	/**
	 * 关闭
	 */
	public void close();

	/**
	 * 获取二级缓存key
	 */
	public String getCacheRegion();

	/**
	 * 设置使用哪个查询缓存
	 */
	public void setCacheRegion(String cacheRegion);

	/**
	 * 是否开启二级缓存
	 */
	public boolean isCacheable();

	/**
	 * 设置是否启用查询缓存
	 */
	public void setCacheable(boolean cacheable);

	/**
	 * 设置二级缓存
	 */
	public void setCacheable(boolean cacheable, String cacheRegion);
	
	/**
	 * 设置是否是本地sql
	 */
	public void setNativeSQL(boolean isNative);
	
	/**
	 * 是否是本地sql
	 * @return
	 */
	public boolean isNativeSQL();
	
	/**
	 * 设置count列名
	 */
	public void setCountColmn(String colName);
	
	/**
	 * 分页是否追加实体
	 */
	public boolean addCountEntity();	

    /**
     * toString
     */
    public String toString();
}

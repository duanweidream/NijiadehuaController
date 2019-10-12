package com.wooboo.dsp.system.util.statement;

public interface StatementFactory {
	
	/**<b>功能说明 创建原生的SQL语句查询</b>
	 * @author luo
	 * @param sql
	 * @return
	 */
	public Statement createNativeStatement(String sql);
	
	/**<b>功能说明 创建原生的SQL语句查询</b>
	 * @author luo
	 * @param sql SQL语句
	 * @param where where 语句
	 * @return
	 */
	public Statement createNativeStatement(String sql, String where);
}

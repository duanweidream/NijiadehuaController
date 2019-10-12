package com.wooboo.dsp.system.util.statement.mysql;

import com.wooboo.dsp.system.util.statement.Statement;
import com.wooboo.dsp.system.util.statement.StatementFactory;

public class StatementFactoryImpl implements StatementFactory {

	public static StatementFactoryImpl instance;
	
	public synchronized static StatementFactoryImpl getInstance(){
		if(instance==null){
			instance = new StatementFactoryImpl();
		}
		return instance;
	}
	
	
	public Statement createNativeStatement(String sql) {
		Statement stmt = new StatementImpl(sql);
		stmt.setNativeSQL(true);
		return stmt;
	}

	public Statement createNativeStatement(String sql, String where) {
		Statement stmt = new StatementImpl(sql, where);
		stmt.setNativeSQL(true);
		return stmt;
	}

	public Statement createStatement(String sql) {
		Statement stmt = new StatementImpl(sql);
		return stmt;
	}

	public Statement createStatement(String sql, String where) {
		Statement stmt = new StatementImpl(sql, where);
		return stmt;
	}

}

package com.nijiadehua.api.base.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Pool {
	  public abstract Connection getConnection() throws SQLException;
	  public abstract void returnConnection(Connection conn);
}

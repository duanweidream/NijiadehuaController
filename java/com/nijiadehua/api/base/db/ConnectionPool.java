package com.nijiadehua.api.base.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.util.Config;

public class ConnectionPool extends Pool {
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
    private static String jdbcDriver = Config.getValue("jdbc.driverClassName"); // 数据库驱动
    private static String dbUrl = Config.getValue("jdbc.url"); // 数据 URL
    private static String dbUsername = Config.getValue("jdbc.username"); // 数据库用户名
    private static String dbPassword = Config.getValue("jdbc.password"); // 数据库用户密码
    private static int initialConnections = Config.getInteger("jdbc.initialConnections"); // 连接池的初始大小
    private static int incrementalConnections = Config.getInteger("jdbc.incrementalConnections"); // 连接池自动增加的大小
    private static int maxConnections = Config.getInteger("jdbc.maxConnections"); // 连接池最大的大小
    private static long removeAbandonedTimeout=Config.getLong("jdbc.removeAbandonedTimeout","3600"); ;//连接最大空闲时间
    private Vector<PooledConnection> connections = null; // 存放连接池中数据库连接的向量 , 初始时为 null
    private static ConnectionPool connectionPool=null;
    static {
		try {
			// 启动属性文件监视线程
			ConnectionMonitor monitor = new ConnectionPool().new ConnectionMonitor();
			monitor.start();
			logger.logInfo("ConnectionMonitor start================================");
		} catch (Exception e) {
			
		}
	}

    
    public synchronized static ConnectionPool getInstance(){
        try{
        	if(connectionPool==null){
            	connectionPool = new ConnectionPool();
            	connectionPool.createPool();
            }	
            return connectionPool;
        }catch(Exception e){
        	e.printStackTrace();
        	return null;
        }
    	
    }
    

    public synchronized void createPool() throws Exception {
        if (connections != null) {
            return; 
        }
        logger.debug("createPool(driver="+jdbcDriver+")");
        Driver driver = (Driver) (Class.forName(jdbcDriver).newInstance());
        DriverManager.registerDriver(driver);
        connections = new Vector<PooledConnection>();
        createConnections(initialConnections);
    }
    private void createConnections(int numConnections) throws SQLException {	
        for (int x = 0; x < numConnections; x++) {
        	logger.logInfo("createConnections(id="+(connections.size())+")");
            if (maxConnections > 0 && connections.size() >= maxConnections) {
                break;
            }
            try {
                connections.addElement(new PooledConnection(newConnection(),connections.size()));
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new SQLException();
            }
        }
    
    }
    
    private Connection newConnection() throws SQLException {
    	logger.debug("newConnection(dburl="+dbUrl+",username="+dbUsername+",password=***)");
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        if (connections.size() == 0) {
            DatabaseMetaData metaData = conn.getMetaData();
            int driverMaxConnections = metaData.getMaxConnections();
            if (driverMaxConnections > 0 &&
                maxConnections > driverMaxConnections) {
                maxConnections = driverMaxConnections;
            }
        }
        return conn;
    }
    public synchronized Connection getConnection() throws SQLException {
        if (connections == null) {
            return null; 
        }
        Connection conn = getFreeConnection(); 
        while (conn == null) {
            wait(250);
            conn = getFreeConnection();
        }
        
        return conn; 
    }
    private Connection getFreeConnection() throws SQLException {
        Connection conn = findFreeConnection();
        if (conn == null  ) {
            if(connections.size()<maxConnections){
            	createConnections(incrementalConnections);
                conn = findFreeConnection();
                if (conn == null) {
                    return null;
                }
            }else{
            	logger.logInfo("maxConnections error");
            }
        }
        return conn;
    }
    private Connection findFreeConnection() throws SQLException {
        Connection conn = null;
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            pConn =  enumerate.nextElement();
            if (!pConn.isBusy()) {
                conn = pConn.getConnection();
                pConn.setBusy(true);
                pConn.createTime=new Date();
                //logger.debug("findFreeConnection(id="+pConn.id+")");
                break; 
            }
        }
        return conn; 
    }
    public void returnConnection(Connection conn) {
    	if (connections == null) {
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            pConn = enumerate.nextElement();
            if (conn==pConn.getConnection()) {	
                pConn.setBusy(false);
                break;
            }
        }
    }
    public synchronized void refreshConnections() throws SQLException {
        if (connections == null) {
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            pConn =  enumerate.nextElement();
            if (pConn.isBusy()) {
                wait(5000); 
            }
            closeConnection(pConn.getConnection());
            pConn.setConnection(newConnection());
            pConn.setBusy(false);
        }
    }
    public synchronized void closeConnectionPool() throws SQLException {
        if (connections == null) {
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            pConn =  enumerate.nextElement();
            if (pConn.isBusy()) {
                wait(5000); 
            }
            closeConnection(pConn.getConnection());
            connections.removeElement(pConn);
        }
        connections = null;
    }
   
    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
        }
    }
    
    
    class PooledConnection {
    	public Integer id;
    	public Date createTime;
        Connection connection = null; 
        boolean busy = false; 
        public PooledConnection(Connection connection,Integer id) {
            this.connection = connection;
            this.id=id;
            this.createTime=new Date();
        }
        public Connection getConnection() {
            return connection;
        }
        public void setConnection(Connection connection) {
            this.connection = connection;
        }
        public boolean isBusy() {
            return busy;
        }
        public void setBusy(boolean busy) {
            this.busy = busy;
        }
        public Statement createStatement() throws SQLException{
        	return connection.createStatement();
        }
    }


    
    /**
	 * 查看属性文件是否更新的监视线程, 每隔一段时间执行
	 */
	 class ConnectionMonitor extends Thread {
		 
		public void run() {
			while (true) {
				try {
					sleep(60*60*1000);
				} catch (InterruptedException e) {
					return;
				}
				try {
					logger.logInfo("ConnectionMonitor.run()");
					List<PooledConnection> list = new ArrayList<PooledConnection>();
					for(PooledConnection c:connectionPool.connections){
						long freetime = (System.currentTimeMillis()-c.createTime.getTime())/1000;
						if(freetime>=removeAbandonedTimeout){
							list.add(c);
						}
					}
					if(list.size()>0){
						for(PooledConnection c:list){
							logger.debug("ConnectionMonitor.remove("+c.id+")");
							connectionPool.connections.remove(c);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		
	}
}

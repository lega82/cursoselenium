package beanUtil;

/**
 * Author			        :	V.Ramachandran
 * Filename					:	ConnectDatabase 
 * Scope			        :	Java based development
 * Supporting files			:	dbconnect.properties
 * Description				:	This java component is used to connect with any database.
 */

import java.sql.DriverManager;
import java.sql.SQLException;

//import logger.AccessLog;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectDB {
	public static String dbUrl = null;
	public static String username = null;
	public static String password = null;
	public static String driver = null;
	private static String propfilewithpath = "dbconnect.properties";
	private static int maxConnections = 10;

	static boolean loaded = false;

	public static void init() {
		try {
			if (dbUrl == null || driver == null) {

				java.io.InputStream is = ConnectDB.class.getClassLoader()
						.getResourceAsStream(propfilewithpath);
				java.util.Properties p = new java.util.Properties();
				if (is != null)
					p.load(is);
				// username=p.getProperty("username");
				// password=p.getProperty("password");
				driver = p.getProperty("driver");
				dbUrl = p.getProperty("dbUrl");
				if (p.getProperty("maxConnections") != null) {
					try {
						Integer maxCon = new Integer(
								p.getProperty("maxConnections"));
						maxConnections = maxCon.intValue();
					} catch (Exception ex) {
						// nothing to do with the exception.
						ex.printStackTrace();
					}
				}
			}
			Class.forName(driver);
			setupDriver(dbUrl);
		} catch (Exception ex) {
			// AccessLog.Log(ex);
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

	static void setupDriver(String connectURI) throws Exception {
		GenericObjectPool connectionPool = new GenericObjectPool(null,
				maxConnections);
		connectionPool.setMinIdle(maxConnections / 2);
		connectionPool.setTimeBetweenEvictionRunsMillis(60000L); // Every 60
																	// seconds.

		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				connectURI, null);

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, connectionPool, null,
				"select sysdate from dual", false, false);

		PoolingDriver driver = new PoolingDriver();
		driver.registerPool("mydbpool", connectionPool);
	}

	public static java.sql.Connection getConnection() throws SQLException {
		if (!loaded) {
			init();
			loaded = true;
		}
		java.sql.Connection dbCon = null;
		dbCon = DriverManager
				.getConnection("jdbc:apache:commons:dbcp:mydbpool");

		return dbCon;
	}

	/*
	 * public static java.sql.Connection getConnection() throws Exception { try
	 * { if(dbUrl==null || username==null || password==null || driver==null) {
	 * 
	 * java.io.InputStream is =
	 * ConnectDB.class.getClassLoader().getResourceAsStream(propfilewithpath);
	 * AccessLog.Log("Inside connectdb "+is.available());
	 * //java.io.FileInputStream file=new
	 * java.io.FileInputStream(propfilewithpath); java.util.Properties p=new
	 * java.util.Properties(); if(is!=null) p.load(is);
	 * 
	 * username=p.getProperty("username"); password=p.getProperty("password");
	 * driver=p.getProperty("driver"); dbUrl=p.getProperty("dbUrl");
	 * AccessLog.Log("USERNAME "+username); AccessLog.Log("PASSWORD "+password);
	 * AccessLog.Log("DRIVER "+driver); AccessLog.Log("DBURL "+dbUrl); }
	 * Class.forName(driver); java.sql.Connection
	 * con=java.sql.DriverManager.getConnection(dbUrl,username,password);
	 * if(con!=null) { AccessLog.Log("CONNECTION IN CONNECT DB "+con); return
	 * con;
	 * 
	 * } else { return null; } } catch(Exception e) {
	 * AccessLog.Log("Error ....."+e); e.printStackTrace(); return null; } }
	 */
}
package edu.wm.as.cs.codeclones.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {
	private static Dao instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/code_clones";
	
	public static Dao getInstance() throws Exception {
		if (instance == null) {
			instance = new Dao();
		}
		return instance;
	}
	
	private Dao() throws Exception {
		dataSource = getDataSource();
	}
	
	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = dataSource.getConnection();
		return conn;
	}
	
	public void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
	
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}

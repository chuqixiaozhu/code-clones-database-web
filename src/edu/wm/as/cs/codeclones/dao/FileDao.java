package edu.wm.as.cs.codeclones.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.wm.as.cs.codeclones.entities.File;


public class FileDao {
	private static FileDao instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/code_clones";
	
	public static FileDao getInstance() throws Exception {
		if (instance == null) {
			instance = new FileDao();
		}
		return instance;
	}
	
	private FileDao() throws Exception {
		dataSource = getDataSource();
	}
	
	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;
	}
	
	private Connection getConnection() throws Exception {
		Connection conn = dataSource.getConnection();
		return conn;
	}
	
	private void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
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
	
	public void addFileByInputStream(File theFile, InputStream is) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "insert into File "
						+ "(fileName, projectName, revisionName, fileData) "
						+ "values (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, theFile.getFileName());
			stmt.setString(2, theFile.getProjectName());
			stmt.setString(3, theFile.getRevisionName());
			Reader reader = new InputStreamReader(is); 
			stmt.setClob(4, reader);
			
			stmt.execute();			
		}
		finally {
			close (conn, stmt);
		}
	}
	
	public File getFileByFileProjectRevisionNames(String projectName,
													String revisionName,
													String fileName) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from File "
						+ "where projectName=?"
						+ "and revisionName=?"
						+ "and fileName=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectName);
			stmt.setString(2, revisionName);
			stmt.setString(3, fileName);
			rs = stmt.executeQuery();
			File theFile = null;
			
			if(rs.next()) {
				int fileID = rs.getInt("fileID");
				Clob fileData = rs.getClob("fileData");
				theFile = new File(fileID,
						fileName,
						projectName,
						revisionName,
						fileData);
			} else {
				throw new Exception("Could not find file name: " + fileName + ", P: " + projectName + ", R: " + revisionName);
			}
			return theFile;
		} finally {
			close(conn, stmt, rs);
		}
	}
}

package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.wm.as.cs.codeclones.entities.Project;


public class ProjectDao {
	private static ProjectDao instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/code_clones";
	
	public static ProjectDao getInstance() throws Exception {
		if (instance == null) {
			instance = new ProjectDao();
		}
		return instance;
	}
	
	private ProjectDao() throws Exception {
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
	
//	private void close(Connection conn, Statement stmt) {
//		close(conn, stmt, null);
//	}
	
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
	
	public List<Project> getProjects() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from Project order by projectName";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Project> projects = new ArrayList<>();
			while (rs.next()) {
				int projectID = rs.getInt("projectID");
				String projectName = rs.getString("projectName");
				String authorName = rs.getString("authorName");
				Date submitTime = rs.getTimestamp("submitTime");
				
				Project tempProject = new Project(projectID, projectName, 
												authorName, submitTime);
				projects.add(tempProject);
			}
			return projects;
		}
		finally {
			close(conn, stmt, rs);
		}
	}
//	
//	public Project getProject() throws Exception {
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			conn = getConnection();
//			String sql = "select * from Project order by projectName";
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			Project theClone = new
//		}
//	}
}

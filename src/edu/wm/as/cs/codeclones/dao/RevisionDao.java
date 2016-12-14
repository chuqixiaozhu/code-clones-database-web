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

import edu.wm.as.cs.codeclones.entities.Revision;

public class RevisionDao {
	private static RevisionDao instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/code_clones";
	
	public static RevisionDao getInstance() throws Exception {
		if (instance == null) {
			instance = new RevisionDao();
		}
		return instance;
	}
	
	private RevisionDao() throws Exception {
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
	
	public List<Revision> getRevisions() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from Revision order by RevisionName";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Revision> revisions = new ArrayList<>();
			while (rs.next()) {
				int revisionID = rs.getInt("revisionID");
				String projectName = rs.getString("projectName");
				String revisionName = rs.getString("revisionName");
				String authorName = rs.getString("authorName");
				Date submitTime = rs.getTimestamp("submitTime");
				
				Revision tempRevision = new Revision(revisionID, projectName, revisionName, 
												authorName, submitTime);
				revisions.add(tempRevision);
			}
			return revisions;
		}
		finally {
			close(conn, stmt, rs);
		}
	}
}

package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.WebUser;
import edu.wm.as.cs.codeclones.util.Dao;


public class UserDao {
	private Dao dao;
	
	public UserDao() throws Exception {
		dao = Dao.getInstance();
	}
	
	public List<WebUser> getWebUsers() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from WebUser";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<WebUser> webUsers = new ArrayList<>();
			while (rs.next()) {
				int userID = rs.getInt("userID");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String userType = rs.getString("userType");
				WebUser tempWebUser = new WebUser(userID,
													userName,
													password,
													userType);
				webUsers.add(tempWebUser);
			}
			return webUsers;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public WebUser getWebUserByID(int userID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from WebUser where userID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			rs = stmt.executeQuery();
			WebUser webUser = null;
			if(rs.next()) {
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String userType = rs.getString("userType");
				webUser = new WebUser(userID,
										userName,
										password,
										userType);
			} else {
				throw new Exception("Could not find webUser id: " + userID);
			}
			return webUser;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public WebUser validateUser(String userName, String password) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from WebUser where userName=? and password=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			WebUser webUser = null;
			if(rs.next()) {
				int userID = rs.getInt("userID");
				String userType = rs.getString("userType");
				webUser = new WebUser(userID,
										userName,
										password,
										userType);
			} else {
				throw new Exception("Could not find User name: " + userName);
			}
			return webUser;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public void addWebUser(WebUser webUser) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into WebUser "
						+ "(userName, password, userType) "
						+ "values (?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, webUser.getUserName());
			stmt.setString(2, webUser.getPassword());
			stmt.setString(3, webUser.getUserType());
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public void deleteWebUser(int userID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from WebUser where webUserID=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userID);
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
}

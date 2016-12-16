package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.Fragment;
import edu.wm.as.cs.codeclones.util.Dao;

public class FragmentDao {
	private Dao dao;
	
	public FragmentDao() throws Exception {
		dao = Dao.getInstance();
	}
	
	public List<Fragment> getFragments() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Fragment";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Fragment> fragments = new ArrayList<>();
			while (rs.next()) {
				int fragmentID = rs.getInt("fragmentID");
				int projectID = rs.getInt("projectID");
				int revisionID = rs.getInt("revisionID");
				String filePath = rs.getString("filePath");
				int startLine = rs.getInt("startLine");
				int endLine = rs.getInt("endLine");
				
				Fragment tempFragment = new Fragment(fragmentID, 
														projectID,
														revisionID,
														filePath,
														startLine,
														endLine);
				fragments.add(tempFragment);
			}
			return fragments;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public Fragment getFragmentByID(int fragmentID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Fragment where fragmentID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fragmentID);
			rs = stmt.executeQuery();
			Fragment fragment = null;
			if(rs.next()) {
				int projectID = rs.getInt("projectID");
				int revisionID = rs.getInt("revisionID");
				String filePath = rs.getString("filePath");
				int startLine = rs.getInt("startLine");
				int endLine = rs.getInt("endLine");
				fragment = new Fragment(fragmentID, 
										projectID,
										revisionID,
										filePath,
										startLine,
										endLine);
			} else {
				throw new Exception("Could not find fragment id: " + fragmentID);
			}
			return fragment;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public void addFragment(Fragment fragment) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into Fragment "
						+ "(projectID, revisionID, filePath, startLine, endLine) "
						+ "values (?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, fragment.getProjectID());
			stmt.setInt(2, fragment.getRevisionID());
			stmt.setString(3, fragment.getFilePath());
			stmt.setInt(4, fragment.getStartLine());
			stmt.setInt(5, fragment.getEndLine());
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public void deleteFragment(int fragmentID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from Fragment where fragmentID=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, fragmentID);
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
}

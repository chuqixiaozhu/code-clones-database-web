package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.util.Dao;

public class CloneDao {
	private Dao dao;
	
	public CloneDao() throws Exception {
		dao = Dao.getInstance();
	}
	
	public List<CodeClone> getClones() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from CodeClone";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<CodeClone> clones = new ArrayList<>();
			while (rs.next()) {
				int cloneID = rs.getInt("cloneID");
				int fragment1ID = rs.getInt("fragment1ID");
				int fragment2ID = rs.getInt("fragment1ID");
				int detectorID = rs.getInt("detectorID");
				
				CodeClone tempClone = new CodeClone(cloneID,
												fragment1ID,
												fragment2ID,
												detectorID);
				clones.add(tempClone);
			}
			return clones;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public CodeClone getCloneByID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from CodeClone where cloneID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			CodeClone clone = null;
			if(rs.next()) {
				int fragment1ID = rs.getInt("fragment1ID");
				int fragment2ID = rs.getInt("fragment1ID");
				int detectorID = rs.getInt("detectorID"); 
				clone = new CodeClone(cloneID,
						fragment1ID,
						fragment2ID,
						detectorID);
			} else {
				throw new Exception("Could not find clone id: " + cloneID);
			}
			return clone;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public void addClone(CodeClone clone) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into CodeClone "
						+ "(fragment1ID, fragment2ID, detectorID) "
						+ "values (?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, clone.getFragment1ID());
			stmt.setInt(2, clone.getFragment2ID());
			stmt.setInt(3, clone.getDetectorID());
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public void deleteClone(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from CodeClone where cloneID=? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, cloneID);
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public List<String> getProject1Names() throws Exception {
		List<String> project1Names = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select projectName from Project "
					+ "where projectID in "
					+ "(select projectID from Fragment "
					+ "where fragmentID in "
					+ "(select fragment1ID from CodeClone))";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String project1Name = rs.getString("project1Name");
				project1Names.add(project1Name);
			}
			return project1Names;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public List<String> getproject2Names() throws Exception {
		List<String> project2Names = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select projectName from Project "
					+ "where projectID in "
					+ "(select projectID from Fragment "
					+ "where fragmentID in "
					+ "(select fragment2ID from CodeClone))";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String project2Name = rs.getString("project2Name");
				project2Names.add(project2Name);
			}
			return project2Names;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public List<String> getRevision1Names() throws Exception {
		List<String> revision1Names = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select revisionName from Revision "
					+ "where revisionID in "
					+ "(select revisionID from Fragment "
					+ "where fragmentID in "
					+ "(select fragment1ID from CodeClone))";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String revision1Name = rs.getString("revision1Name");
				revision1Names.add(revision1Name);
			}
			return revision1Names;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public List<String> getRevision2Names() throws Exception {
		List<String> revision2Names = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select revisionName from Revision "
					+ "where revisionID in "
					+ "(select revisionID from Fragment "
					+ "where fragmentID in "
					+ "(select fragment2ID from CodeClone))";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String revision2Name = rs.getString("revision2Name");
				revision2Names.add(revision2Name);
			}
			return revision2Names;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
//	public List<CodeClone> getClonesBy2CloneName()
}

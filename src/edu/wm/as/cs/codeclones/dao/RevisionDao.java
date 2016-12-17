package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.Revision;
import edu.wm.as.cs.codeclones.util.Dao;

public class RevisionDao {
	private Dao dao;
	
	public RevisionDao() throws Exception {
		dao = Dao.getInstance();
	}
	
	public List<Revision> getRevisions() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Revision";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Revision> revisions = new ArrayList<>();
			while (rs.next()) {
				int revisionID = rs.getInt("revisionID");
				int projectID = rs.getInt("projectID");
				String revisionName = rs.getString("revisionName");
				
				Revision tempRevision = new Revision(revisionID, 
														projectID, 
														revisionName);
				revisions.add(tempRevision);
			}
			return revisions;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public Revision getRevisionByID(int revisionID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Revision where revisionID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, revisionID);
			rs = stmt.executeQuery();
			Revision revision = null;
			if(rs.next()) {
				int projectID = rs.getInt("projectID");
				String revisionName = rs.getString("revisionName");
				revision = new Revision(revisionID, 
									projectID,
									revisionName);
			} else {
				throw new Exception("Could not find revision id: " + revisionID);
			}
			return revision;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public Revision getRevisionByName(String revisionName) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Revision where revisionName=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, revisionName);
			rs = stmt.executeQuery();
			Revision revision = null;
			if(rs.next()) {
				int revisionID = rs.getInt("revisionID");
				int projectID = rs.getInt("projectID");
				revision = new Revision(revisionID, 
										projectID,
										revisionName);
			} else {
//				throw new Exception("Could not find revision name: " + revisionName);
				System.out.println("Could not find revision name: " + revisionName);
			}
			return revision;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public void addRevision(Revision revision) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into Revision "
						+ "(projectID, revisionName) "
						+ "values (?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, revision.getProjectID());
			stmt.setString(2, revision.getRevisionName());
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public void deleteRevision(int revisionID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from Revision where revisionID=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, revisionID);
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
}

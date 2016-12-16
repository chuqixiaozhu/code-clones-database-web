package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.util.Dao;


public class ProjectDao{
	private Dao dao;
	
	public ProjectDao() throws Exception {
		dao = Dao.getInstance();
	}
	
	public List<Project> getProjects() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Project";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Project> projects = new ArrayList<>();
			while (rs.next()) {
				int projectID = rs.getInt("projectID");
				String projectName = rs.getString("projectName");
				Project tempProject = new Project(projectID, 
												projectName);
				projects.add(tempProject);
			}
			return projects;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public Project getProjectByID(int projectID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Project where projectID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			rs = stmt.executeQuery();
			Project project = null;
			if(rs.next()) {
				String projectName = rs.getString("projectName");
				project = new Project(projectID, 
									projectName);
			} else {
				throw new Exception("Could not find project id: " + projectID);
			}
			return project;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public void addProject(Project project) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into Project "
						+ "(projectName) "
						+ "values (?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, project.getProjectName());
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public void deleteProject(int projectID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from Project where projectID=? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, projectID);
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
}

package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.Detector;
import edu.wm.as.cs.codeclones.util.Dao;

public class DetectorDao {
	private Dao dao;
	
	public DetectorDao() throws Exception {
		dao = Dao.getInstance();
	}
	public List<Detector> getDetectors() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Detector";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Detector> detectors = new ArrayList<>();
			while (rs.next()) {
				int detectorID = rs.getInt("detectorID");
				String detectorName = rs.getString("detectorName");
				String detectorConfig = rs.getString("detectorConfig");
				Detector tempDetector = new Detector(detectorID, 
												detectorName,
												detectorConfig);
				detectors.add(tempDetector);
			}
			return detectors;
		}
		finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public Detector getDetectorByID(int detectorID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Detector where detectorID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, detectorID);
			rs = stmt.executeQuery();
			Detector detector = null;
			if(rs.next()) {
				String detectorName = rs.getString("detectorName");
				String detectorConfig = rs.getString("detectorConfig");
				detector = new Detector(detectorID, 
						detectorName,
						detectorConfig);
			} else {
				throw new Exception("Could not find detector id: " + detectorID);
			}
			return detector;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public Detector getDetectorByDetector(Detector detector) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dao.getConnection();
			String sql = "select * from Detector "
						+ "where detectorName=? "
						+ "and detectorConfig=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, detector.getDetectorName());
			stmt.setString(2, detector.getDetectorConfig());
			rs = stmt.executeQuery();
			if(rs.next()) {
				detector.setDetectorID(rs.getInt("detectorID"));
			} else {
				throw new Exception("Could not find detector: " + detector);
			}
			return detector;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public void addDetector(Detector detector) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into Detector "
						+ "(detectorName, detectorConfig) "
						+ "values (?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, detector.getDetectorName());
			stmt.setString(2, detector.getDetectorConfig());
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
	
	public void deleteDetector(int detectorID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from Detector where detectorID=? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, detectorID);
						
			stmt.execute();			
		}
		finally {
			dao.close (conn, stmt);
		}
	}
}

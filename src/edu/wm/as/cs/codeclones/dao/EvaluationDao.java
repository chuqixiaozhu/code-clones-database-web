package edu.wm.as.cs.codeclones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.wm.as.cs.codeclones.entities.Evaluation;
import edu.wm.as.cs.codeclones.util.Dao;

//import edu.wm.as.cs.codeclones.entities.Evaluation;

public class EvaluationDao {
	private Dao dao;
	
	public EvaluationDao() throws Exception {
		dao = Dao.getInstance();
	}
	
	public void addEvaluation(Evaluation evaluation) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "insert into Evaluation "
						+ "(cloneID, cloneType, similarity,	truePositive, score) "
						+ "values (?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, evaluation.getCloneID());
			stmt.setInt(2, evaluation.getCloneType());
			stmt.setFloat(3, evaluation.getSimilarity());
			stmt.setBoolean(4, evaluation.getTruePositive());
			stmt.setFloat(5, evaluation.getScore());
						
			stmt.execute();			
		}
		finally {
			dao.close(conn, stmt);
		}
	}
	
	public void deleteEvaluation(int evaluationID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dao.getConnection();
			String sql = "delete from Evaluation where evaluationID=? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, evaluationID);
						
			stmt.execute();			
		}
		finally {
			dao.close(conn, stmt);
		}
	}
	
	public float getSimilarityMeanByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select AVG(similarity) from Evaluation where cloneID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			float similarityMean = 0;
			
			if(rs.next()) {
				similarityMean = rs.getFloat("AVG(similarity)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return similarityMean;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public int getType1NumByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select COUNT(cloneType) from Evaluation where cloneID=? and cloneType=1";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			int type1Num = 0;
			
			if(rs.next()) {
				type1Num = rs.getInt("COUNT(cloneType)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return type1Num;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public int getType2NumByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select COUNT(cloneType) from Evaluation where cloneID=? and cloneType=2";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			int type2Num = 0;
			
			if(rs.next()) {
				type2Num = rs.getInt("COUNT(cloneType)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return type2Num;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public int getType3NumByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select COUNT(cloneType) from Evaluation where cloneID=? and cloneType=3";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			int type3Num = 0;
			
			if(rs.next()) {
				type3Num = rs.getInt("COUNT(cloneType)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return type3Num;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public int getType4NumByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select COUNT(cloneType) from Evaluation where cloneID=? and cloneType=4";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			int type4Num = 0;
			
			if(rs.next()) {
				type4Num = rs.getInt("COUNT(cloneType)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return type4Num;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public int getTruePositiveNumByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select COUNT(truePositive) from Evaluation where cloneID=? and truePositive=TRUE";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			int tpNum = 0;
			
			if(rs.next()) {
				tpNum = rs.getInt("COUNT(truePositive)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return tpNum;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public int getFalsePositiveNumByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select COUNT(truePositive) from Evaluation where cloneID=? and truePositive=FALSE";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			int fpNum = 0;
			
			if(rs.next()) {
				fpNum = rs.getInt("COUNT(truePositive)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return fpNum;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public float getScoreMeanByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			String sql = "select AVG(score) from Evaluation where cloneID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			float scoreMean = 0;
			
			if(rs.next()) {
				scoreMean = rs.getFloat("AVG(score)");
			} else {
				throw new Exception("Could not find code clone id: " + cloneID);
			}
			return scoreMean;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
	
	public List<Evaluation> getEvaluationsByCloneID(int cloneID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Evaluation> evaluations = new ArrayList<>();
		
		try {
			conn = dao.getConnection();
			String sql = "select * from Evaluation where cloneID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cloneID);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int evaluationID = rs.getInt("evaluationID");
				int cloneType = rs.getInt("cloneType");
				float similarity = rs.getFloat("similarity");
				Boolean truePositive = rs.getBoolean("truePositive");
				float score = rs.getFloat("score");
				
				Evaluation tempEvaluation = new Evaluation(evaluationID,
														cloneID,
														cloneType,
														similarity,
														truePositive,
														score);
				evaluations.add(tempEvaluation);
			}
			return evaluations;
		} finally {
			dao.close(conn, stmt, rs);
		}
	}
}

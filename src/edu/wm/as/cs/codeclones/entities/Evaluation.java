package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Evaluation {
	private int evaluationID;
	private int cloneID;
	private int cloneType;
	private float similarity;
	private Boolean truePositive;
	private float score;
	
	public Evaluation() {
	}

	public Evaluation(int evaluationID, int cloneID, int cloneType, float similarity, Boolean truePositive,
			float score) {
		super();
		this.evaluationID = evaluationID;
		this.cloneID = cloneID;
		this.cloneType = cloneType;
		this.similarity = similarity;
		this.truePositive = truePositive;
		this.score = score;
	}

	public Evaluation(int cloneID, int cloneType, float similarity, Boolean truePositive, float score) {
		super();
		this.cloneID = cloneID;
		this.cloneType = cloneType;
		this.similarity = similarity;
		this.truePositive = truePositive;
		this.score = score;
	}

	public int getEvaluationID() {
		return evaluationID;
	}

	public void setEvaluationID(int evaluationID) {
		this.evaluationID = evaluationID;
	}

	public int getCloneID() {
		return cloneID;
	}

	public void setCloneID(int cloneID) {
		this.cloneID = cloneID;
	}

	public int getCloneType() {
		return cloneType;
	}

	public void setCloneType(int cloneType) {
		this.cloneType = cloneType;
	}

	public float getSimilarity() {
		return similarity;
	}

	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}

	public Boolean getTruePositive() {
		return truePositive;
	}

	public void setTruePositive(Boolean truePositive) {
		this.truePositive = truePositive;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Evaluation [evaluationID=" + evaluationID + ", cloneID=" + cloneID + ", cloneType=" + cloneType
				+ ", similarity=" + similarity + ", truePositive=" + truePositive + ", score=" + score + "]";
	}
	
	
}

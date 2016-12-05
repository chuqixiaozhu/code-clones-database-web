package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CodeClone {
	private int cloneID;
	private int projectID1;
	private int revisionID1;
	private String fileName1;
	private int startLine1;
	private int endLine1;
	private int projectID2;
	private int revisionID2;
	private String fileName2;
	private int startLine2;
	private int endLine2;
	private int type;
	
	public CodeClone() {
	}

	public CodeClone(int cloneID, int projectID1, int revisionID1, String fileName1, int startLine1, int endLine1,
			int projectID2, int revisionID2, String fileName2, int startLine2, int endLine2, int type) {
		super();
		this.cloneID = cloneID;
		this.projectID1 = projectID1;
		this.revisionID1 = revisionID1;
		this.fileName1 = fileName1;
		this.startLine1 = startLine1;
		this.endLine1 = endLine1;
		this.projectID2 = projectID2;
		this.revisionID2 = revisionID2;
		this.fileName2 = fileName2;
		this.startLine2 = startLine2;
		this.endLine2 = endLine2;
		this.type = type;
	}

	public int getCloneID() {
		return cloneID;
	}

	public void setCloneID(int cloneID) {
		this.cloneID = cloneID;
	}

	public int getProjectID1() {
		return projectID1;
	}

	public void setProjectID1(int projectID1) {
		this.projectID1 = projectID1;
	}

	public int getRevisionID1() {
		return revisionID1;
	}

	public void setRevisionID1(int revisionID1) {
		this.revisionID1 = revisionID1;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public int getStartLine1() {
		return startLine1;
	}

	public void setStartLine1(int startLine1) {
		this.startLine1 = startLine1;
	}

	public int getEndLine1() {
		return endLine1;
	}

	public void setEndLine1(int endLine1) {
		this.endLine1 = endLine1;
	}

	public int getProjectID2() {
		return projectID2;
	}

	public void setProjectID2(int projectID2) {
		this.projectID2 = projectID2;
	}

	public int getRevisionID2() {
		return revisionID2;
	}

	public void setRevisionID2(int revisionID2) {
		this.revisionID2 = revisionID2;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public int getStartLine2() {
		return startLine2;
	}

	public void setStartLine2(int startLine2) {
		this.startLine2 = startLine2;
	}

	public int getEndLine2() {
		return endLine2;
	}

	public void setEndLine2(int endLine2) {
		this.endLine2 = endLine2;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CodeClone [cloneID=" + cloneID + ", projectID1=" + projectID1 + ", revisionID1=" + revisionID1
				+ ", fileName1=" + fileName1 + ", startLine1=" + startLine1 + ", endLine1=" + endLine1 + ", projectID2="
				+ projectID2 + ", revisionID2=" + revisionID2 + ", fileName2=" + fileName2 + ", startLine2="
				+ startLine2 + ", endLine2=" + endLine2 + ", type=" + type + "]";
	}
	
}

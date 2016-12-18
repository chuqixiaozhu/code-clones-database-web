package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CloneView {
	int cloneID;
	String project1Name;
	String revision1Name;
	String filePath1;
	int startLine1;
	int endLine1;
	String project2Name;
	String revision2Name;
	String filePath2;
	int startLine2;
	int endLine2;
	
	public CloneView() {
		super();
	}

	public CloneView(int cloneID, String project1Name, String revision1Name, String filePath1, int startLine1,
			int endLine1, String project2Name, String revision2Name, String filePath2, int startLine2, int endLine2) {
		super();
		this.cloneID = cloneID;
		this.project1Name = project1Name;
		this.revision1Name = revision1Name;
		this.filePath1 = filePath1;
		this.startLine1 = startLine1;
		this.endLine1 = endLine1;
		this.project2Name = project2Name;
		this.revision2Name = revision2Name;
		this.filePath2 = filePath2;
		this.startLine2 = startLine2;
		this.endLine2 = endLine2;
	}

	public int getCloneID() {
		return cloneID;
	}


	public void setCloneID(int cloneID) {
		this.cloneID = cloneID;
	}


	public String getProject1Name() {
		return project1Name;
	}

	public void setProject1Name(String project1Name) {
		this.project1Name = project1Name;
	}

	public String getRevision1Name() {
		return revision1Name;
	}

	public void setRevision1Name(String revision1Name) {
		this.revision1Name = revision1Name;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
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

	public String getProject2Name() {
		return project2Name;
	}

	public void setProject2Name(String project2Name) {
		this.project2Name = project2Name;
	}

	public String getRevision2Name() {
		return revision2Name;
	}

	public void setRevision2Name(String revision2Name) {
		this.revision2Name = revision2Name;
	}

	public String getFilePath2() {
		return filePath2;
	}

	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
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

	@Override
	public String toString() {
		return "CloneView [project1Name=" + project1Name + ", revision1Name=" + revision1Name + ", filePath1="
				+ filePath1 + ", startLine1=" + startLine1 + ", endLine1=" + endLine1 + ", project2Name=" + project2Name
				+ ", revision2Name=" + revision2Name + ", filePath2=" + filePath2 + ", startLine2=" + startLine2
				+ ", endLine2=" + endLine2 + "]";
	}

	
	
}

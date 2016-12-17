package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CloneView {
	String projectName1;
	String revisionName1;
	String filePath1;
	int startLine1;
	int endLine1;
	String projectName2;
	String revisionName2;
	String filePath2;
	int startLine2;
	int endLine2;
	
	public CloneView() {
		super();
	}

	public CloneView(String projectName1, String revisionName1, String filePath1, int startLine1, int endLine1,
			String projectName2, String revisionName2, String filePath2, int startLine2, int endLine2) {
		super();
		this.projectName1 = projectName1;
		this.revisionName1 = revisionName1;
		this.filePath1 = filePath1;
		this.startLine1 = startLine1;
		this.endLine1 = endLine1;
		this.projectName2 = projectName2;
		this.revisionName2 = revisionName2;
		this.filePath2 = filePath2;
		this.startLine2 = startLine2;
		this.endLine2 = endLine2;
	}

	public String getProjectName1() {
		return projectName1;
	}

	public void setProjectName1(String projectName1) {
		this.projectName1 = projectName1;
	}

	public String getRevisionName1() {
		return revisionName1;
	}

	public void setRevisionName1(String revisionName1) {
		this.revisionName1 = revisionName1;
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

	public String getProjectName2() {
		return projectName2;
	}

	public void setProjectName2(String projectName2) {
		this.projectName2 = projectName2;
	}

	public String getRevisionName2() {
		return revisionName2;
	}

	public void setRevisionName2(String revisionName2) {
		this.revisionName2 = revisionName2;
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
	
	
	
}

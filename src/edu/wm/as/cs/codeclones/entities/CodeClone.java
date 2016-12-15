package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CodeClone {
	private int cloneID;
//	private int projectID1;
//	private int revisionID1;
	private String project1Name;
	private String revision1Name;
	private String fileName1;
	private int startLine1;
	private int endLine1;
//	private int projectID2;
//	private int revisionID2;
	private String project2Name;
	private String revision2Name;
	private String fileName2;
	private int startLine2;
	private int endLine2;
//	private int detectorID;
	private String detectorName;
	private String configuration;
	
	public CodeClone() {
		project1Name = null;
	}

	

	public CodeClone(int cloneID, String project1Name, String revision1Name, String fileName1, int startLine1,
			int endLine1, String project2Name, String revision2Name, String fileName2, int startLine2, int endLine2,
			String detectorName, String configuration) {
		super();
		this.cloneID = cloneID;
		this.project1Name = project1Name;
		this.revision1Name = revision1Name;
		this.fileName1 = fileName1;
		this.startLine1 = startLine1;
		this.endLine1 = endLine1;
		this.project2Name = project2Name;
		this.revision2Name = revision2Name;
		this.fileName2 = fileName2;
		this.startLine2 = startLine2;
		this.endLine2 = endLine2;
		this.detectorName = detectorName;
		this.configuration = configuration;
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


	public int getCloneID() {
		return cloneID;
	}

	public void setCloneID(int cloneID) {
		this.cloneID = cloneID;
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



	public String getDetectorName() {
		return detectorName;
	}



	public void setDetectorName(String detectorName) {
		this.detectorName = detectorName;
	}



	public String getConfiguration() {
		return configuration;
	}



	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}



	@Override
	public String toString() {
		return "CodeClone [cloneID=" + cloneID + ", project1Name=" + project1Name + ", revision1Name=" + revision1Name
				+ ", fileName1=" + fileName1 + ", startLine1=" + startLine1 + ", endLine1=" + endLine1
				+ ", project2Name=" + project2Name + ", revision2Name=" + revision2Name + ", fileName2=" + fileName2
				+ ", startLine2=" + startLine2 + ", endLine2=" + endLine2 + ", detectorName=" + detectorName
				+ ", configuration=" + configuration + "]";
	}


}

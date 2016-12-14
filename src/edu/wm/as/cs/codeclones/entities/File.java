package edu.wm.as.cs.codeclones.entities;

import java.sql.Clob;

public class File {
	private int fileID;
	private String fileName;
	private String projectName;
	private String revisionName;
	private Clob fileData;
	
	public File() {
		
	}


	public File(int fileID, String fileName, String projectName, String revisionName, Clob fileData) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.projectName = projectName;
		this.revisionName = revisionName;
		this.fileData = fileData;
	}


	public int getFileID() {
		return fileID;
	}


	public void setFileID(int fileID) {
		this.fileID = fileID;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public Clob getFileData() {
		return fileData;
	}

	public void setFileData(Clob fileData) {
		this.fileData = fileData;
	}
	
	
}

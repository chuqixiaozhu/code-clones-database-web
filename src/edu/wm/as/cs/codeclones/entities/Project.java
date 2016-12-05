package edu.wm.as.cs.codeclones.entities;

import java.util.Date;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class Project {
	private int projectID;
	private String projectName;
	private String authorName;
	private java.util.Date submitTime;
	
	public Project() {
	}

	public Project(int projectID, String projectName, String authorName, Date submitTime) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.authorName = authorName;
		this.submitTime = submitTime;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public java.util.Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(java.util.Date submitTime) {
		this.submitTime = submitTime;
	}

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", projectName=" + projectName + ", authorName=" + authorName
				+ ", submitTime=" + submitTime + "]";
	}
	
	
}

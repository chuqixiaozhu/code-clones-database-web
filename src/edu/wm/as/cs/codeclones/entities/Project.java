package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class Project {
	private int projectID;
	private String projectName;
	
	public Project() {
	}

	public Project(int projectID, String projectName) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
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

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", projectName=" + projectName + "]";
	}
	
}

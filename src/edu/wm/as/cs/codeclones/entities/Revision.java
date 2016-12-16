package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Revision {
	private int revisionID;
	private int projectID;
	private String revisionName;
	
	public Revision() {
	}

	public Revision(int revisionID, int projectID, String revisionName) {
		super();
		this.revisionID = revisionID;
		this.projectID = projectID;
		this.revisionName = revisionName;
	}

	public int getRevisionID() {
		return revisionID;
	}

	public void setRevisionID(int revisionID) {
		this.revisionID = revisionID;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	@Override
	public String toString() {
		return "Revision [revisionID=" + revisionID + ", projectID=" + projectID + ", revisionName=" + revisionName
				+ "]";
	}

	
}

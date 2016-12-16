package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Fragment {
	private int fragmentID;
	private int projectID;
	private int revisionID;
	private String filePath;
	private int startLine;
	private int endLine;
	
	public Fragment() {
		
	}

	public Fragment(int fragmentID, int projectID, int revisionID, String filePath, int startLine, int endLine) {
		super();
		this.fragmentID = fragmentID;
		this.projectID = projectID;
		this.revisionID = revisionID;
		this.filePath = filePath;
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public Fragment(int projectID, int revisionID, String filePath, int startLine, int endLine) {
		super();
		this.projectID = projectID;
		this.revisionID = revisionID;
		this.filePath = filePath;
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public int getFragmentID() {
		return fragmentID;
	}

	public void setFragmentID(int fragmentID) {
		this.fragmentID = fragmentID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getRevisionID() {
		return revisionID;
	}

	public void setRevisionID(int revisionID) {
		this.revisionID = revisionID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	@Override
	public String toString() {
		return "Fragment [fragmentID=" + fragmentID + ", projectID=" + projectID + ", revisionID=" + revisionID
				+ ", filePath=" + filePath + ", startLine=" + startLine + ", endLine=" + endLine + "]";
	}
	
}

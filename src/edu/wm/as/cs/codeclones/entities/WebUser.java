package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class WebUser {
	private int userID;
	private String userName;
	private String password;
	private String userType;
	
	public WebUser() {
		
	}

	public WebUser(int userID, String userName, String password, String userType) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "WebUser [userID=" + userID + ", userName=" + userName + ", password=" + password + ", userType="
				+ userType + "]";
	}
}

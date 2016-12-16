package edu.wm.as.cs.codeclones.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//import edu.wm.as.cs.codeclones.dao.UserDao;
//import edu.wm.as.cs.codeclones.entities.User;
/*
@ManagedBean
@SessionScoped
public class UserController {
//	private UserDao userDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private String userName;
	private String password;
//	private String userType;
	
	public UserController () throws Exception {
//		userDao = UserDao.getInstance();
//		userNames = new ArrayList<>();
//		inOneuserChecked = false;
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public String validateUser() {
		logger.info("logging in user name: " + userName);
//		System.out.println("Controller cloneID: " + cloneID);//test
		try {
			String userType = userDao.validateUserByNamePassword(userName, password);
			if (userType.equals("Administrator")) {
				return "clones_list";
			} else if (userType.equals("Contributor")) {
				return "clones_list_contributor";
			} else if (userType.equals("Evaluator")) {
				return "clones_list_evaluator";
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));
				return null;
			}
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding students", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}
		
	}
	
	public String logout(){
		try{
			userDao = UserDao.getInstance();
			return userDao.logout();
		} catch (Exception exc) {
			return "index";
		}
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
	
}
*/
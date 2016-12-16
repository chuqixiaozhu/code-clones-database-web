package edu.wm.as.cs.codeclones.test;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import edu.wm.as.cs.codeclones.controller.*;

public class TestController {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public TestController() {
		
	}
	public static void main(String[] args) {
		TestController tc = new TestController();
		try {
			tc.test();

		} catch (Exception exc) {
			// send this to server logs
			tc.logger.log(Level.SEVERE, "Error adding students", exc);
			
			// add error message for JSF page
			tc.addErrorMessage(exc);
		}
	}

	private void test() throws Exception {

		ProjectController projectController = new ProjectController();
		List<String> projectNames = projectController.getProjectNames();
		for (String tmp : projectNames) {
			System.out.println(tmp);

		}

	}
}

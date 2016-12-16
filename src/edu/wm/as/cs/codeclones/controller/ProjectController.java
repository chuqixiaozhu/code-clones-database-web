package edu.wm.as.cs.codeclones.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.entities.Project;

@ManagedBean
@SessionScoped
public class ProjectController {
	private ProjectDao projectDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private List<String> projectNames;
//	private String project1SelectedName;
//	private String project2SelectedName;
//	private Boolean inOneProjectChecked;
	
	public ProjectController () throws Exception {
		projectDao = new ProjectDao();
		projectNames = new ArrayList<>();
//		inOneProjectChecked = false;
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void loadProjects() {
		logger.info("Loading projects");
		projectNames.clear();
		try {
			List<Project> projects = projectDao.getProjects();
			projectNames.add("ALL");
			for (Project temp : projects) {
				projectNames.add(temp.getProjectName());
			}
			for (String tmp : projectNames) {
				System.out.println(tmp);
			}//test
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading projects", exc);
			addErrorMessage(exc);
		}
	}

	public List<String> getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(List<String> projectNames) {
		this.projectNames = projectNames;
	}
	
//	public static void main(String[] argv) {
//		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
//                "org.apache.naming.java.javaURLContextFactory");
//		try {
//		ProjectController pc = new ProjectController();
//		List<String> projectNames = pc.getProjectNames();
//		for (String tmp : projectNames) {
//			System.out.println(tmp);
//		}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//	}
}

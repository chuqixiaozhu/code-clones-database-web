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
//	private List<Project> projects;
	private ProjectDao projectDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
//	private Project projectSelected;
	private List<String> projectsNames;
	private String project1SelectedName;
	private String project2SelectedName;
	private Boolean inOneProjectChecked;
	
	public ProjectController () throws Exception {
//		projects = new ArrayList<>();
		projectDao = ProjectDao.getInstance();
		projectsNames = new ArrayList<>();
		inOneProjectChecked = false;
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
//	public List<Project> getProjects() {
//		return projects;
//	}
	
	public void loadProjects() {
		logger.info("Loading projects");
//		projects.clear();
		projectsNames.clear();
		try {
			List<Project> projects = projectDao.getProjects();
			projectsNames.add("ALL");
			for (Project temp : projects) {
				projectsNames.add(temp.getProjectName());
			}
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading projects", exc);
			addErrorMessage(exc);
		}
	}
	
//	public void projectSelectedChanged(ValueChangeEvent event) {
////	public void projectSelectedChanged(AjaxBehaviorEvent event) {
//		project_selected = event.getNewValue();
//		System.out.println(project_selected);//test
//	}
//	public void handleProject1SelectedChange() {
//		this.project1SelectedName = (String)
//		System.out.println(project1SelectedName);
//	}

//	public Project getProjectSelected() {
//		return projectSelected;
//	}
//
//	public void setProjectSelected(Project projectSelected) {
//		this.projectSelected = projectSelected;
//		System.out.println(this.projectSelected);
//	}
	
	public List<String> getProjectsNames() {
		return projectsNames;
	}

	public String getproject1SelectedName() {
		return project1SelectedName;
	}

	public void setproject1SelectedName(String project1SelectedName) {
		this.project1SelectedName = project1SelectedName;
	}

	public Boolean getInOneProjectChecked() {
		return inOneProjectChecked;
	}

	public void setInOneProjectChecked(Boolean inOneProjectChecked) {
		this.inOneProjectChecked = inOneProjectChecked;
	}

	public String getProject2SelectedName() {
		return project2SelectedName;
	}

	public void setProject2SelectedName(String project2SelectedName) {
		this.project2SelectedName = project2SelectedName;
	}
	
	
}

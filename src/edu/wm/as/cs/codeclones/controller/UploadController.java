package edu.wm.as.cs.codeclones.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;


import edu.wm.as.cs.codeclones.util.Dao;

@ManagedBean
@SessionScoped
public class UploadController {
	private Logger logger = Logger.getLogger(getClass().getName());
	private Dao dao;
	
	private static final String FOLDER_PATH = new File(".").getAbsolutePath() 
										+ File.separator
										+ "code_clone_projects";
	private Part zipFile;
	private String projectName;
	private String revisionName;
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public UploadController() {
		try {
			dao = Dao.getInstance();
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading dao", exc);
			addErrorMessage(exc);
		}
	}
	
	private Boolean isProjectNameValid(FacesContext context, 
			  UIComponent component, 
			  Object value) throws ValidatorException {
		
		if (value == null) {
			return;
		}
		
		String data = value.toString();
		
		// Course code must start with LUV ... if not, throw exception
		if (!data.startsWith("LUV")) {
			
			FacesMessage message = new FacesMessage("Course code must start with LUV");
			
			throw new ValidatorException(message);
		}
		
		return false;
	}
	
	private Boolean isRevisionNameValid() {
		return true;
	}
	
//	public void uploadFile(FacesContext context, 
//			  UIComponent component) throws ValidatorException {
	public void uploadFile() {
//		if (!isProjectNameValid()) {
//			FacesMessage message = new FacesMessage("Project name exists. Please Change it.");
//			throw new ValidatorException(message);
//		} else if (!isRevisionNameValid()) {
//			FacesMessage message = new FacesMessage("Revision name exists. Please Change it.");
//			throw new ValidatorException(message);
//		}
		if (zipFile.getSize() == 0) {
			return;
		}
		try (InputStream inputStream = zipFile.getInputStream()) {
			String savePath = FOLDER_PATH;
			if (!(new File(savePath).exists())) {
				new File(savePath).mkdir();
			}
			if (projectName == null || projectName.equals("")) {
				String tmpName = zipFile.getSubmittedFileName();
				projectName = tmpName.substring(0, tmpName.length() - 4);
			}
			if (revisionName == null || revisionName.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
				revisionName = sdf.format(new java.util.Date());
			}
			
			String fileName = projectName + revisionName + ".zip";
			System.out.format("savePath: %s, fileName: %s\n", savePath, fileName);//test
			Files.copy(inputStream, new File(savePath, fileName).toPath());
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error upload file", exc);
			addErrorMessage(exc);
		}
		
	}

	public Part getZipFile() {
		return zipFile;
	}

	public void setZipFile(Part zipFile) {
		this.zipFile = zipFile;
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
	
}

package edu.wm.as.cs.codeclones.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


import edu.wm.as.cs.codeclones.dao.CloneDao;
import edu.wm.as.cs.codeclones.entities.CodeClone;

@ManagedBean
@SessionScoped
public class CloneController {
	private CloneDao cloneDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private List<CodeClone> clones;
//	private List<CodeCLone> clones
	
	public CloneController() throws Exception {
		cloneDao = CloneDao.getInstance();
		clones = new ArrayList<>();
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void loadClones() {
		logger.info("Loading clones");
		clones.clear();
		try {
			clones = cloneDao.getClones();
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading projects", exc);
			addErrorMessage(exc);
		}
	}
	
	public List<CodeClone> getClones() {
		return this.clones;
	}
	
	public String loadClone(int cloneID) {
		logger.info("loading clone: " + cloneID);
		
		try {
			CodeClone theClone = cloneDao.getCloneByCloneID(cloneID);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("theClone", theClone);	
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading code clone id:" + cloneID, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		return "view_clone";
	}
	
	public void setProject1Name(String project1Name) {
		logger.info("setProject1Name: " + project1Name);
		this.loadClones();
		if (project1Name.equals("")){
			return;
		}
		if (project1Name.equals("ALL")) {
			return;
		}
		for (Iterator<CodeClone> iter = clones.iterator(); iter.hasNext();) {
			String name = iter.next().getProject1Name();
			if (!name.equals(project1Name)) {
				iter.remove();
			}
		}
	}
}

package edu.wm.as.cs.codeclones.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.Revision;

@ManagedBean
@SessionScoped
public class RevisionController {
	private RevisionDao revisionDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private List<String> revisionNames;
	private String revision1SelectedName;
	private String revision2SelectedName;
	private Boolean inOneRevisionChecked;
	
	public RevisionController () throws Exception {
//		revisionDao = RevisionDao.getInstance();
		revisionNames = new ArrayList<>();
		inOneRevisionChecked = false;
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void loadRevisions() {
		logger.info("Loading revisions");
		revisionNames.clear();
		try {
			List<Revision> revisions = revisionDao.getRevisions();
			revisionNames.add("ALL");
			for (Revision temp : revisions) {
				revisionNames.add(temp.getRevisionName());
			}
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading revisions", exc);
			addErrorMessage(exc);
		}
	}

	public List<String> getRevisionNames() {
		return revisionNames;
	}

	public void setRevisionNames(List<String> revisionNames) {
		this.revisionNames = revisionNames;
	}

	public String getRevision1SelectedName() {
		return revision1SelectedName;
	}

	public void setRevision1SelectedName(String revision1SelectedName) {
		this.revision1SelectedName = revision1SelectedName;
	}

	public String getRevision2SelectedName() {
		return revision2SelectedName;
	}

	public void setRevision2SelectedName(String revision2SelectedName) {
		this.revision2SelectedName = revision2SelectedName;
	}

	public Boolean getInOneRevisionChecked() {
		return inOneRevisionChecked;
	}

	public void setInOneRevisionChecked(Boolean inOneRevisionChecked) {
		this.inOneRevisionChecked = inOneRevisionChecked;
	}
	

}

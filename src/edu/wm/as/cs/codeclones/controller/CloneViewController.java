package edu.wm.as.cs.codeclones.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



import edu.wm.as.cs.codeclones.dao.CloneDao;
import edu.wm.as.cs.codeclones.dao.FragmentDao;
import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.CloneView;
import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.entities.Fragment;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;

@ManagedBean
@SessionScoped
public class CloneViewController {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	List<CloneView> cloneViews;
	List<String> project1Names;
	List<String> project2Names;
	List<String> revision1Names;
	List<String> revision2Names;
	Boolean isInOneProject;
	Boolean isInOneRevision;
	String project1SelectedName;
	String revision1SelectedName;
	String project2SelectedName;
	String revision2SelectedName;
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public CloneViewController() {
		super();
		cloneViews = new ArrayList<>();
		project1Names = new ArrayList<>();
		project2Names = new ArrayList<>();
		revision1Names = new ArrayList<>();
		revision2Names = new ArrayList<>();
		isInOneProject = false;
		isInOneRevision = false;
		project1SelectedName = "ALL";
		revision1SelectedName = "ALL";
		project2SelectedName = "ALL";
		revision2SelectedName = "ALL";
		loadCloneViews();
	}
	
	public String preload() {
		loadCloneViews();
		return "clones_list";
	}
	
	public void loadProjectNames() {
		try {
			CloneDao cloneDao = new CloneDao();
			project1Names = cloneDao.getProject1Names();
			project2Names = cloneDao.getproject2Names();
			project1Names.add(0, "ALL");
			project2Names.add(0, "ALL");
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load project names", exc);
			addErrorMessage(exc);
		}
	}
	
	public void loadRevisionNames() {
		try {
			CloneDao cloneDao = new CloneDao();
			revision1Names = cloneDao.getRevision1Names(project1SelectedName);
			revision2Names = cloneDao.getRevision2Names(project2SelectedName);
			revision1Names.add(0, "ALL");
			revision2Names.add(0, "ALL");
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load revision names", exc);
			addErrorMessage(exc);
		}
	}
	
	public void loadCloneViews() {
		logger.info("loadCloneViews");
		try {
			cloneViews.clear();
			CloneDao cloneDao = new CloneDao();
			FragmentDao fragmentDao = new FragmentDao();
			RevisionDao revisionDao = new RevisionDao();
			ProjectDao projectDao = new ProjectDao();
			
			List<CodeClone> clones = cloneDao.getClones();
			for (CodeClone clone : clones) {
				int fragment1ID = clone.getFragment1ID();
				Fragment fragment1 = fragmentDao.getFragmentByID(fragment1ID);
				int revision1ID = fragment1.getRevisionID();
				Revision revision1 = revisionDao.getRevisionByID(revision1ID);
				String revision1Name = revision1.getRevisionName();
				int project1ID = fragment1.getProjectID();
				Project project1 = projectDao.getProjectByID(project1ID);
				String project1Name = project1.getProjectName();
				String filePath1 = fragment1.getFilePath();
				
				int fragment2ID = clone.getFragment2ID();
				Fragment fragment2 = fragmentDao.getFragmentByID(fragment2ID);
				int revision2ID = fragment2.getRevisionID();
				Revision revision2 = revisionDao.getRevisionByID(revision2ID);
				String revision2Name = revision2.getRevisionName();
				int project2ID = fragment2.getProjectID();
				Project project2 = projectDao.getProjectByID(project2ID);
				String project2Name = project2.getProjectName();
				String filePath2 = fragment2.getFilePath();
				
				Path path = Paths.get(filePath1);
				filePath1 = path.getFileName().toString();
				path = Paths.get(filePath2);
				filePath2 = path.getFileName().toString();
				CloneView cloneView = new CloneView(clone.getCloneID(),
													project1Name, 
													revision1Name, 
													filePath1,
													0,
													0,
													project2Name,
													revision2Name,
													filePath2,
													0,
													0);
				cloneViews.add(cloneView);
			}
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load code clones", exc);
			addErrorMessage(exc);
		}
	}
	
	public void loadCloneTable() {
		logger.info("loadCloneTable");
		loadCloneViews();
		if (!project1SelectedName.equals("ALL")) {
			for (Iterator<CloneView> iter = cloneViews.iterator(); iter.hasNext();) {
				String name = iter.next().getProject1Name();
				if (!name.equals(project1SelectedName)) {
					iter.remove();
				}
			}
		}
		if (isInOneProject) {
			project2SelectedName = project1SelectedName;
		}
		if (!project2SelectedName.equals("ALL")) {
			for (Iterator<CloneView> iter = cloneViews.iterator(); iter.hasNext();) {
				String name = iter.next().getProject2Name();
				if (!name.equals(project2SelectedName)) {
					iter.remove();
				}
			}
		}
		if (!revision1SelectedName.equals("ALL")) {
			for (Iterator<CloneView> iter = cloneViews.iterator(); iter.hasNext();) {
				String name = iter.next().getRevision1Name();
				if (!name.equals(revision1SelectedName)) {
					iter.remove();
				}
			}
		}
		if (isInOneRevision) {
			revision2SelectedName = revision1SelectedName;
		}
		if (!revision2SelectedName.equals("ALL")) {
			for (Iterator<CloneView> iter = cloneViews.iterator(); iter.hasNext();) {
				String name = iter.next().getRevision2Name();
				if (!name.equals(revision2SelectedName)) {
					iter.remove();
				}
			}
		} 
	}
	
	public void deleteClone(int cloneID) {
		logger.info("deleteClone");
		try {
			CloneDao cloneDao = new CloneDao();
			cloneDao.deleteClone(cloneID);
			loadCloneTable();
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error delete code clones", exc);
			addErrorMessage(exc);
		}
	}

	public List<CloneView> getCloneViews() {
		return cloneViews;
	}

	public void setCloneViews(List<CloneView> cloneViews) {
		this.cloneViews = cloneViews;
	}

	public List<String> getProject1Names() {
		return project1Names;
	}

	public void setProject1Names(List<String> project1Names) {
		this.project1Names = project1Names;
	}

	public List<String> getProject2Names() {
		return project2Names;
	}

	public void setProject2Names(List<String> project2Names) {
		this.project2Names = project2Names;
	}

	public List<String> getRevision1Names() {
		return revision1Names;
	}

	public void setRevision1Names(List<String> revision1Names) {
		this.revision1Names = revision1Names;
	}

	public List<String> getRevision2Names() {
		return revision2Names;
	}

	public void setRevision2Names(List<String> revision2Names) {
		this.revision2Names = revision2Names;
	}

	public Boolean getIsInOneProject() {
		return isInOneProject;
	}

	public void setIsInOneProject(Boolean isInOneProject) {
		this.isInOneProject = isInOneProject;
	}

	public Boolean getIsInOneRevision() {
		return isInOneRevision;
	}

	public void setIsInOneRevision(Boolean isInOneRevision) {
		this.isInOneRevision = isInOneRevision;
	}

	public String getProject1SelectedName() {
		return project1SelectedName;
	}

	public void setProject1SelectedName(String project1SelectedName) {
		this.project1SelectedName = project1SelectedName;
	}

	public String getRevision1SelectedName() {
		return revision1SelectedName;
	}

	public void setRevision1SelectedName(String revision1SelectedName) {
		this.revision1SelectedName = revision1SelectedName;
	}

	public String getProject2SelectedName() {
		return project2SelectedName;
	}

	public void setProject2SelectedName(String project2SelectedName) {
		this.project2SelectedName = project2SelectedName;
	}

	public String getRevision2SelectedName() {
		return revision2SelectedName;
	}

	public void setRevision2SelectedName(String revision2SelectedName) {
		this.revision2SelectedName = revision2SelectedName;
	}
}

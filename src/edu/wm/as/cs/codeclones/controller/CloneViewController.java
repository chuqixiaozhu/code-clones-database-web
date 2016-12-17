package edu.wm.as.cs.codeclones.controller;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import edu.wm.as.cs.codeclones.dao.CloneDao;
import edu.wm.as.cs.codeclones.dao.DetectorDao;
import edu.wm.as.cs.codeclones.dao.FragmentDao;
import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.CloneView;
import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.entities.Detector;
import edu.wm.as.cs.codeclones.entities.Fragment;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;

@ManagedBean
@SessionScoped
public class CloneViewController {
	List<CloneView> cloneViews;
	List<String> projectNames1;
	List<String> projectNames2;
	List<String> revisionNames1;
	List<String> revisionNames2;
	Boolean isInOneProject;
	Boolean isInOneRevision;
	
}

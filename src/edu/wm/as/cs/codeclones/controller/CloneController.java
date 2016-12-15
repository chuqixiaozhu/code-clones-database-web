package edu.wm.as.cs.codeclones.controller;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import edu.wm.as.cs.codeclones.dao.CloneDao;
//import edu.wm.as.cs.codeclones.dao.DetectorDao;
import edu.wm.as.cs.codeclones.dao.EvaluationDao;
import edu.wm.as.cs.codeclones.dao.FileDao;
import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
//import edu.wm.as.cs.codeclones.dao.UserDao;
import edu.wm.as.cs.codeclones.entities.CodeClone;
//import edu.wm.as.cs.codeclones.entities.Detector;
import edu.wm.as.cs.codeclones.entities.Evaluation;
import edu.wm.as.cs.codeclones.entities.File;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;


@ManagedBean
@SessionScoped
public class CloneController {
	private CloneDao cloneDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private List<CodeClone> clones;
	private List<String> project1Names;
	private List<String> project2Names;
	private List<String> revision1Names;
	private List<String> revision2Names;
	private String project1SelectedName;
	private String project2SelectedName;
	private String revision1SelectedName;
	private String revision2SelectedName;
	private Boolean inOneProjectChecked;
	private Boolean inOneRevisionChecked;
	
	private CodeClone theClone;
//	private Detector theDetector;
	private Evaluation theEvaluation;
	private File theFile1;
	private File theFile2;
	private int type1Num;
	private int type2Num;
	private int type3Num;
	private int type4Num;
	private float similarityMean;
	private int truePositiveNum;
	private int falseaPositiveNum;
	private float scoreMean;
	private int thetf;
	private List<Evaluation> evaluations;
	private String codeFragment1;
	private String codeFragment2;
	
	private String newProjectName;
	private String newRevisionName;
	private String newAuthorName;
	
	public CloneController() throws Exception {
		cloneDao = CloneDao.getInstance();
		clones = new ArrayList<>();
		project1Names = new ArrayList<>();
		project2Names = new ArrayList<>();
		revision1Names = new ArrayList<>();
		revision2Names = new ArrayList<>();
		project1SelectedName = "ALL";
		project2SelectedName = "ALL";
		revision1SelectedName = "ALL";
		revision2SelectedName = "ALL";
		inOneProjectChecked = false;
		inOneRevisionChecked = false;
		theClone = null;
//		theDetector = null;
		theEvaluation = new Evaluation();
		evaluations = new ArrayList<>();
		loadClones();
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
	
	public void loadProject1Names() {
		logger.info("Loading projects in CloneController");
		project1Names.clear();
		try {
			project1Names = cloneDao.getProject1Names();
			project1Names.add(0, "ALL");
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading projects", exc);
			addErrorMessage(exc);
		}
	}
	
	public void loadProject2Names() {
		logger.info("Loading projects in CloneController");
		project2Names.clear();
		try {
			project2Names = cloneDao.getProject2Names();
			project2Names.add(0, "ALL");
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading projects", exc);
			addErrorMessage(exc);
		}
	}
	
	public void loadRevision1Names() {
		logger.info("Loading revisions");
		revision1Names.clear();
		try {
			revision1Names = cloneDao.getRevision1Names();
			revision1Names.add(0, "ALL");
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading revisions", exc);
			addErrorMessage(exc);
		}
	}
	
	public void loadRevision2Names() {
		logger.info("Loading revisions");
		revision2Names.clear();
		try {
			revision2Names = cloneDao.getRevision2Names();
			revision2Names.add(0, "ALL");
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading revisions", exc);
			addErrorMessage(exc);
		}
	}
	
	
	public List<CodeClone> getClones() {
		return this.clones;
	}
	
	private String getCodeFragment(Clob clob, int startLine, int endLine) {
		String code = new String("");
		int count = 0;
		try (Scanner sc = new Scanner(clob.getAsciiStream())) {
			while (sc.hasNextLine()) {
				count += 1;
				if (count > endLine) {
					break;
				}
				if (count >= startLine) {
					String line = sc.nextLine();
					code += line + "\n";
				}
			}
		} catch (Exception exc) {
			System.out.println("Cannot get file1's code");
		}
		return code;
	}
	
	public String loadClone(int cloneID) {
		logger.info("loading clone: " + cloneID);
		try {
			/* Code Clone Information */
			theClone = cloneDao.getCloneByCloneID(cloneID);
			/* Detector Information */
//			DetectorDao detectorDao = DetectorDao.getInstance();
//			theDetector = detectorDao.getDetectorByID(theClone.getDetectorID());
			/* Evaluation History */
			EvaluationDao evaluationDao = EvaluationDao.getInstance();
			type1Num = evaluationDao.getType1NumByCloneID(cloneID);
			type2Num = evaluationDao.getType2NumByCloneID(cloneID);
			type3Num = evaluationDao.getType3NumByCloneID(cloneID);
			type4Num = evaluationDao.getType4NumByCloneID(cloneID);
			similarityMean = evaluationDao.getSimilarityMeanByCloneID(cloneID);
			truePositiveNum = evaluationDao.getTruePositiveNumByCloneID(cloneID);
			falseaPositiveNum = evaluationDao.getFalsePositiveNumByCloneID(cloneID);
			scoreMean = evaluationDao.getScoreMeanByCloneID(cloneID);
			evaluations = evaluationDao.getEvaluationsByCloneID(cloneID);
			/* Files */
			FileDao fileDao = FileDao.getInstance();
			theFile1 = fileDao.getFileByFileProjectRevisionNames(theClone.getProject1Name(),
																theClone.getRevision1Name(),
																theClone.getFileName1());
			theFile2 = fileDao.getFileByFileProjectRevisionNames(theClone.getProject2Name(),
																theClone.getRevision2Name(),
																theClone.getFileName2());
			codeFragment1 = getCodeFragment(theFile1.getFileData(), 
											theClone.getStartLine1(), 
											theClone.getEndLine1());
			codeFragment2 = getCodeFragment(theFile2.getFileData(), 
											theClone.getStartLine2(), 
											theClone.getEndLine2());
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading code clone id:" + cloneID, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		return "view_clone";
	}
	
	public void getClonesTable() {
		logger.info("getClonesTable()");
		this.loadClones();
		if (!project1SelectedName.equals("ALL")) {
			for (Iterator<CodeClone> iter = clones.iterator(); iter.hasNext();) {
				String name = iter.next().getProject1Name();
				if (!name.equals(project1SelectedName)) {
					iter.remove();
				}
			}
		}
		if (inOneProjectChecked) {
			project2SelectedName = project1SelectedName;
			System.out.println("project2SelectName:" + project2SelectedName);//test
		}
		if (!project2SelectedName.equals("ALL")) {

			for (Iterator<CodeClone> iter = clones.iterator(); iter.hasNext();) {
				String name = iter.next().getProject2Name();
				if (!name.equals(project2SelectedName)) {
					iter.remove();
				}
			}
		}
		if (!revision1SelectedName.equals("ALL")) {
			for (Iterator<CodeClone> iter = clones.iterator(); iter.hasNext();) {
				String name = iter.next().getRevision1Name();
				if (!name.equals(revision1SelectedName)) {
					iter.remove();
				}
			}
		}
		if (inOneRevisionChecked) {
			revision2SelectedName = revision1SelectedName;
		}
		if (!revision2SelectedName.equals("ALL")) {

			for (Iterator<CodeClone> iter = clones.iterator(); iter.hasNext();) {
				String name = iter.next().getRevision2Name();
				if (!name.equals(revision2SelectedName)) {
					iter.remove();
				}
			}
		}
//		return "clones_list";
	}
	
	public void submitEvaluation() {
		logger.info("Submitting evaluation: " + theEvaluation);
//		System.out.println("thetf: " + thetf);//test
		try {
			EvaluationDao evaluationDao = EvaluationDao.getInstance();
			if (thetf == 1) {
				theEvaluation.setTruePositive(true);
			} else {
				theEvaluation.setTruePositive(false);
			}
			evaluationDao.addEvaluationByCloneID(theEvaluation, theClone.getCloneID());
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error adding students", exc);
			addErrorMessage(exc);
		}
		loadClone(theClone.getCloneID());
	}
	
	public void deleteEvaluationByEvaluationID(int evaluationID) {
		logger.info("deleting evaluation ID: " + evaluationID);
		try {
			EvaluationDao evaluationDao = EvaluationDao.getInstance();
			evaluationDao.deleteEvaluationByEvaluationID(evaluationID);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error adding students", exc);
			addErrorMessage(exc);
		}
		loadClone(theClone.getCloneID());
	}
	
	public void deteleCloneByCloneID(int cloneID) {
		logger.info("deleting clone ID: " + cloneID);
		try {
			CloneDao cloneDao = CloneDao.getInstance();
			cloneDao.deleteCloneByCloneID(cloneID);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error adding students", exc);
			addErrorMessage(exc);
		}
//		return "clones_list?faces-redirect=true";
		getClonesTable();
	}
	
	public void validateNewProjectName(FacesContext context, UIComponent component, Object value)
			throws ValidatorException, Exception {

		if (value == null) {
			return;
		}

		String data = value.toString();

		// Course code must start with LUV ... if not, throw exception
		ProjectDao projectDao = ProjectDao.getInstance();
		List<Project> projects = projectDao.getProjects();
		for (Project tmp : projects) {
			if (data.equals(tmp.getProjectName())) {
				FacesMessage message = new FacesMessage("Project name already exists.");
				throw new ValidatorException(message);
			}
		}
	}
	
	public void validateNewRevisionName(FacesContext context, UIComponent component, Object value)
			throws ValidatorException, Exception {

		if (value == null) {
			return;
		}

		String data = value.toString();

		RevisionDao revisionDao = RevisionDao.getInstance();
		List<Revision> revisions = revisionDao.getRevisions();
		for (Revision tmp : revisions) {
			if (data.equals(tmp.getRevisionName())) {
				FacesMessage message = new FacesMessage("Revision name already exists.");
				throw new ValidatorException(message);
			}
		}
	}
	
//	public String logout(){
//		try{
//			UserDao userDao = UserDao.getInstance();
//			return userDao.logout();
//		} catch (Exception exc) {
//			return "index";
//		}
//	}

	public String getProject1SelectedName() {
		return project1SelectedName;
	}

	public void setProject1SelectedName(String project1SelectedName) {
		this.project1SelectedName = project1SelectedName;
	}

	public String getProject2SelectedName() {
		return project2SelectedName;
	}

	public void setProject2SelectedName(String project2SelectedName) {
		this.project2SelectedName = project2SelectedName;
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

	public Boolean getInOneProjectChecked() {
		return inOneProjectChecked;
	}

	public void setInOneProjectChecked(Boolean inOneProjectChecked) {
		this.inOneProjectChecked = inOneProjectChecked;
	}

	public Boolean getInOneRevisionChecked() {
		return inOneRevisionChecked;
	}

	public void setInOneRevisionChecked(Boolean inOneRevisionChecked) {
		this.inOneRevisionChecked = inOneRevisionChecked;
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

	public CodeClone getTheClone() {
		return theClone;
	}

	public void setTheClone(CodeClone theClone) {
		this.theClone = theClone;
	}
	
//	public Detector getTheDetector() {
//		return theDetector;
//	}
//
//	public void setTheDetector(Detector theDetector) {
//		this.theDetector = theDetector;
//	}

	public Evaluation getTheEvaluation() {
		return theEvaluation;
	}

	public void setTheEvaluation(Evaluation theEvaluation) {
		this.theEvaluation = theEvaluation;
	}

	public int getType1Num() {
		return type1Num;
	}

	public void setType1Num(int type1Num) {
		this.type1Num = type1Num;
	}

	public int getType2Num() {
		return type2Num;
	}

	public void setType2Num(int type2Num) {
		this.type2Num = type2Num;
	}

	public int getType3Num() {
		return type3Num;
	}

	public void setType3Num(int type3Num) {
		this.type3Num = type3Num;
	}

	public int getType4Num() {
		return type4Num;
	}

	public void setType4Num(int type4Num) {
		this.type4Num = type4Num;
	}

	public float getSimilarityMean() {
		return similarityMean;
	}

	public void setSimilarityMean(float similarityMean) {
		this.similarityMean = similarityMean;
	}

	public int getTruePositiveNum() {
		return truePositiveNum;
	}

	public void setTruePositiveNum(int truePositiveNum) {
		this.truePositiveNum = truePositiveNum;
	}

	public int getFalseaPositiveNum() {
		return falseaPositiveNum;
	}

	public void setFalseaPositiveNum(int falseaPositiveNum) {
		this.falseaPositiveNum = falseaPositiveNum;
	}

	public float getScoreMean() {
		return scoreMean;
	}

	public void setScoreMean(float scoreMean) {
		this.scoreMean = scoreMean;
	}

	public int getThetf() {
		return thetf;
	}

	public void setThetf(int thetf) {
		this.thetf = thetf;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public String getNewProjectName() {
		return newProjectName;
	}

	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}

	public String getNewRevisionName() {
		return newRevisionName;
	}

	public void setNewRevisionName(String newRevisionName) {
		this.newRevisionName = newRevisionName;
	}

	public String getNewAuthorName() {
		return newAuthorName;
	}

	public void setNewAuthorName(String newAuthorName) {
		this.newAuthorName = newAuthorName;
	}

	public File getTheFile1() {
		return theFile1;
	}

	public void setTheFile1(File theFile1) {
		this.theFile1 = theFile1;
	}

	public File getTheFile2() {
		return theFile2;
	}

	public void setTheFile2(File theFile2) {
		this.theFile2 = theFile2;
	}

	public String getCodeFragment1() {
		return codeFragment1;
	}

	public void setCodeFragment1(String codeFragment1) {
		this.codeFragment1 = codeFragment1;
	}

	public String getCodeFragment2() {
		return codeFragment2;
	}

	public void setCodeFragment2(String codeFragment2) {
		this.codeFragment2 = codeFragment2;
	}
	
}

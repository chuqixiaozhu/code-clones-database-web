package edu.wm.as.cs.codeclones.controller;

//import java.io.BufferedReader;
//import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.wm.as.cs.codeclones.dao.CloneDao;
import edu.wm.as.cs.codeclones.dao.DetectorDao;
import edu.wm.as.cs.codeclones.dao.EvaluationDao;
import edu.wm.as.cs.codeclones.dao.FragmentDao;
import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.CloneView;
import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.entities.Detector;
import edu.wm.as.cs.codeclones.entities.Evaluation;
import edu.wm.as.cs.codeclones.entities.Fragment;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;


@ManagedBean
@SessionScoped
public class EvaluationController {
	private Logger logger = Logger.getLogger(getClass().getName());

	private CloneView theClone;
	private Detector theDetector;
	private List<Evaluation> evaluations;
	private int type1Num;
	private int type2Num;
	private int type3Num;
	private int type4Num;
	private float similarityMean;
	private int truePositiveNum;
	private int falseaPositiveNum;
	private float scoreMean;
	private Evaluation theEvaluation;
	private int thetf;
	private String filePath1;
	private String filePath2;
	private String fragment1;
	private String fragment2;
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public EvaluationController() {
		theEvaluation = new Evaluation();
		evaluations = new ArrayList<>();
	}
	
	public String viewClone(int cloneID) {
		theEvaluation = new Evaluation();
		try {
			/* Clone Info. */
			loadClone(cloneID);
			/* Detector Info. */
			CloneDao cloneDao = new CloneDao();
			CodeClone clone = cloneDao.getCloneByID(cloneID);
			int detectorID = clone.getDetectorID();
			loadDetector(detectorID);
			/* Evaluation history */
			loadEvaluations(cloneID);
			/* Code Fragments */
			loadFragments(cloneID);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error view code clones", exc);
			addErrorMessage(exc);
		}
		return "view_clone";
	}
	
	private void loadClone(int cloneID) {
		try {
			CloneDao cloneDao = new CloneDao();
			FragmentDao fragmentDao = new FragmentDao();
			RevisionDao revisionDao = new RevisionDao();
			ProjectDao projectDao = new ProjectDao();
			
			CodeClone clone = cloneDao.getCloneByID(cloneID);
			int fragment1ID = clone.getFragment1ID();
			Fragment fragment1 = fragmentDao.getFragmentByID(fragment1ID);
			int revision1ID = fragment1.getRevisionID();
			Revision revision1 = revisionDao.getRevisionByID(revision1ID);
			String revision1Name = revision1.getRevisionName();
			int project1ID = fragment1.getProjectID();
			Project project1 = projectDao.getProjectByID(project1ID);
			String project1Name = project1.getProjectName();
			String filePath1 = fragment1.getFilePath();
			this.filePath1 = filePath1;
			int startLine1 = fragment1.getStartLine();
			int endLine1 = fragment1.getEndLine();
			
			int fragment2ID = clone.getFragment2ID();
			Fragment fragment2 = fragmentDao.getFragmentByID(fragment2ID);
			int revision2ID = fragment2.getRevisionID();
			Revision revision2 = revisionDao.getRevisionByID(revision2ID);
			String revision2Name = revision2.getRevisionName();
			int project2ID = fragment2.getProjectID();
			Project project2 = projectDao.getProjectByID(project2ID);
			String project2Name = project2.getProjectName();
			String filePath2 = fragment2.getFilePath();
			this.filePath2 = filePath2;
			int startLine2 = fragment2.getStartLine();
			int endLine2 = fragment2.getEndLine();
			
			Path path = Paths.get(filePath1);
			filePath1 = path.getFileName().toString();
			path = Paths.get(filePath2);
			filePath2 = path.getFileName().toString();
			theClone = new CloneView(cloneID,
										project1Name, 
										revision1Name, 
										filePath1,
										startLine1,
										endLine1,
										project2Name,
										revision2Name,
										filePath2,
										startLine2,
										endLine2); 
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load the code clone", exc);
			addErrorMessage(exc);
		}
	}
	
	private void loadDetector(int detectorID) {
		try {
			DetectorDao detectorDao = new DetectorDao();
			theDetector = detectorDao.getDetectorByID(detectorID);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load the detector", exc);
			addErrorMessage(exc);
		}
	}
	
	private void loadEvaluations(int cloneID) {
		evaluations.clear();
		try {
			EvaluationDao evaluationDao = new EvaluationDao();
			type1Num = evaluationDao.getType1NumByCloneID(cloneID);
			type2Num = evaluationDao.getType2NumByCloneID(cloneID);
			type3Num = evaluationDao.getType3NumByCloneID(cloneID);
			type4Num = evaluationDao.getType4NumByCloneID(cloneID);
			similarityMean = evaluationDao.getSimilarityMeanByCloneID(cloneID);
			truePositiveNum = evaluationDao.getTruePositiveNumByCloneID(cloneID);
			falseaPositiveNum = evaluationDao.getFalsePositiveNumByCloneID(cloneID);
			scoreMean = evaluationDao.getScoreMeanByCloneID(cloneID);
			evaluations = evaluationDao.getEvaluationsByCloneID(cloneID);
			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load the evaluation", exc);
			addErrorMessage(exc);
			
		}
		
	}
	
	private void loadFragments(int cloneID) {
//		int startLine1 = theClone.getStartLine1();
//		int startLine2 = theClone.getStartLine2();
//		int endLine1 = theClone.getEndLine1();
//		int endLine2 = theClone.getEndLine2();
		fragment1 = "";
		fragment2 = "";
//		try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				fragment1 += line + "\n";
//			}
//		} catch (Exception exc) {
//			logger.log(Level.SEVERE, "Error load fragment 1", exc);
//			addErrorMessage(exc);
//		}
//		try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				fragment2 += line + "\n";
//			}
//		} catch (Exception exc) {
//			logger.log(Level.SEVERE, "Error load fragment 2", exc);
//			addErrorMessage(exc);
//		}
		try (Scanner sc = new Scanner(Paths.get(filePath1))) {
			while (sc.hasNextLine()) {
				fragment1 += sc.nextLine() + "\n";
			}
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load fragment 1", exc);
			addErrorMessage(exc);
		}
		
		try (Scanner sc = new Scanner(Paths.get(filePath2))) {
			while (sc.hasNextLine()) {
				fragment2 += sc.nextLine() + "\n";
			}
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error load fragment 2", exc);
			addErrorMessage(exc);
		}
	}
	
	public void submitEvaluation() {
		logger.info("Submitting evaluation: " + theEvaluation);
		try {
			EvaluationDao evaluationDao = new EvaluationDao();
			if (thetf == 1) {
				theEvaluation.setTruePositive(true);
			} else {
				theEvaluation.setTruePositive(false);
			}
			theEvaluation.setCloneID(theClone.getCloneID());
			evaluationDao.addEvaluation(theEvaluation);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error submit evaluation", exc);
			addErrorMessage(exc);
		}
//		loadClone(theClone.getCloneID());
		loadEvaluations(theClone.getCloneID());
//		return "view_clone";
	}
	
	public String addEvaluationByCloneID(Evaluation theEvaluation, int cloneID) {
		logger.info("Adding evaluation: " + theEvaluation);
		System.out.println("Controller cloneID: " + cloneID);//test
		try {
			
			// add student to the database
//			evaluationDao.addEvaluationByCloneID(theEvaluation, cloneID);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding students", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}
		
		return "view_clone";
	}
	
	public String deleteEvaluation(int evaluationID) {
		logger.info("Deleting evaluationID id: " + evaluationID);
		
		try {

			// delete the student from the database
			EvaluationDao evaluationDao = new EvaluationDao();
			evaluationDao.deleteEvaluation(evaluationID);
			loadEvaluations(theClone.getCloneID());
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting evaluation id: " + evaluationID, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-students";	
	}
	
	public void test(int cloneID) {
//		Map<String, int> params =
//                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		String cloneID = params.get("cloneID");
		System.out.println("Test ID: " + cloneID);
	}

	public CloneView getTheClone() {
		return theClone;
	}

	public void setTheClone(CloneView theClone) {
		this.theClone = theClone;
	}

	public Detector getTheDetector() {
		return theDetector;
	}

	public void setTheDetector(Detector theDetector) {
		this.theDetector = theDetector;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
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

	public Evaluation getTheEvaluation() {
		return theEvaluation;
	}

	public void setTheEvaluation(Evaluation theEvaluation) {
		this.theEvaluation = theEvaluation;
	}

	public int getThetf() {
		return thetf;
	}

	public void setThetf(int thetf) {
		this.thetf = thetf;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public String getFilePath2() {
		return filePath2;
	}

	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}

	public String getFragment1() {
		return fragment1;
	}

	public void setFragment1(String fragment1) {
		this.fragment1 = fragment1;
	}

	public String getFragment2() {
		return fragment2;
	}

	public void setFragment2(String fragment2) {
		this.fragment2 = fragment2;
	}
	
}

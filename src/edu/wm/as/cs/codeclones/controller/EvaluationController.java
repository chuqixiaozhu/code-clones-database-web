package edu.wm.as.cs.codeclones.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.wm.as.cs.codeclones.dao.EvaluationDao;
import edu.wm.as.cs.codeclones.entities.Evaluation;

@ManagedBean
@SessionScoped
public class EvaluationController {
	private EvaluationDao evaluationDao;
	private Logger logger = Logger.getLogger(getClass().getName());
	
//	private List<String> evaluationNames;
//	private String evaluation1SelectedName;
//	private String evaluation2SelectedName;
//	private Boolean inOneevaluationChecked;
	
	public EvaluationController () throws Exception {
		evaluationDao = EvaluationDao.getInstance();
//		evaluationNames = new ArrayList<>();
//		inOneevaluationChecked = false;
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public String addEvaluationByCloneID(Evaluation theEvaluation, int cloneID) {
		logger.info("Adding evaluation: " + theEvaluation);
		System.out.println("Controller cloneID: " + cloneID);//test
		try {
			
			// add student to the database
			evaluationDao.addEvaluationByCloneID(theEvaluation, cloneID);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding students", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}
		
		return "view_clone";
	}
	
	public String deleteEvaluationByEvaluationID(int evaluationID) {
		logger.info("Deleting evaluationID id: " + evaluationID);
		
		try {

			// delete the student from the database
			evaluationDao.deleteEvaluationByEvaluationID(evaluationID);
			
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
}

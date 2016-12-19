package edu.wm.as.cs.codeclones.controller;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
//import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import edu.wm.as.cs.codeclones.dao.CloneDao;
import edu.wm.as.cs.codeclones.dao.DetectorDao;
import edu.wm.as.cs.codeclones.dao.FragmentDao;
import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.entities.Detector;
import edu.wm.as.cs.codeclones.entities.Fragment;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;

@ManagedBean
@SessionScoped
public class CloneController {
private Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String FOLDER_PATH = new File(".").getAbsolutePath() 
										+ File.separator
										+ "code_clone_projects"
										+ File.separator;
	private String detectorName;
	private String detectorConfig;
	private Part csvFile;
	private String inputMessage;
	private String finalMessage;
	private String detectorNameMessage;
	private String detectorConfigMessage;
	private String csvFileMessage;
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public CloneController() {
		inputMessage = "";
		finalMessage = "";
	}
	
	public String addCloneCommandLink() {
		detectorName = "";
		detectorConfig = "";
		csvFile = null;
		inputMessage = "";
		finalMessage = "";
		detectorNameMessage = "";
		detectorConfigMessage = "";
		csvFileMessage = "";
		return "upload_clone";
	}
	
	private void cleanMessages() {
		inputMessage = "";
		finalMessage = "";
		detectorNameMessage = "";
		detectorConfigMessage = "";
		csvFileMessage = "";
	}
	
	private Boolean isProjectNameExisting(String projectName) throws Exception {
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao.getProjectByName(projectName);
		if (project == null) {
			return false;
		} else {
			return true;
		}
	}
	
	private Boolean isRevisionNameExisting(String revisionName) throws Exception {
		RevisionDao revisionDao = new RevisionDao();
		Revision revision = revisionDao.getRevisionByName(revisionName);
		if (revision == null) {
			return false;
		} else {
			return true;
		}
	}
	public void parseCSVFile() {
//		inputMessage = "";
		cleanMessages();
		
		try (Scanner sc = new Scanner(csvFile.getInputStream())) {
			if (csvFile.getSize() == 0) {
//				System.out.println("The CSV file is empty.");
				csvFileMessage = "Please choose csv file.";
				throw new Exception("The CSV file is empty.");
			}
			if (detectorName == null || detectorName.equals("")) {
				detectorNameMessage = "Please enter detector name.";
				throw new Exception("Detector name is empty.");
			}
			if (detectorConfig == null || detectorConfig.equals("")) {
				detectorConfigMessage = "Please enter detector name.";
				throw new Exception("Detector name is empty.");
			}
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] dt = line.split(",");
				/* Get those data */
				String projectName1 = dt[0].trim();
				String revisionName1 = dt[1].trim();
				String filePath1 = dt[2].trim();
				filePath1 = FOLDER_PATH + filePath1;
				int startLine1 = Integer.parseInt(dt[3].trim());
				int endLine1 = Integer.parseInt(dt[4].trim());
				String projectName2 = dt[5].trim();
				String revisionName2 = dt[6].trim();
				String filePath2 = dt[7].trim();
				filePath2 = FOLDER_PATH + filePath2;
				int startLine2 = Integer.parseInt(dt[8].trim());
				int endLine2 = Integer.parseInt(dt[9].trim());
				if (!(isProjectNameExisting(projectName1) 
						&& isProjectNameExisting(projectName2)
						&& isRevisionNameExisting(revisionName1)
						&& isRevisionNameExisting(revisionName2))) {
					inputMessage = "There are some projects/revisions not existing.";
					continue;
				}
				/* Get ProjectID and RevisionID */
				ProjectDao projectDao = new ProjectDao();
				int projectID1 = projectDao.getProjectByName(projectName1).getProjectID();
				int projectID2 = projectDao.getProjectByName(projectName2).getProjectID();
				RevisionDao revisionDao = new RevisionDao();
				int revisionID1 = revisionDao.getRevisionByName(revisionName1).getRevisionID();
				int revisionID2 = revisionDao.getRevisionByName(revisionName2).getRevisionID();
				/* Add The First Fragment into DB */
				Fragment fragment1 = new Fragment(projectID1, 
													revisionID1,
													filePath1,
													startLine1,
													endLine1);
				FragmentDao fragmentDao = new FragmentDao();
				fragmentDao.addFragment(fragment1);
				/* Add the Second Fragment into DB */
				Fragment fragment2 = new Fragment(projectID2, 
													revisionID2,
													filePath2,
													startLine2,
													endLine2);
				fragmentDao.addFragment(fragment2);
				/* Add the Code Clone into DB */
				fragment1 = fragmentDao.getFragmentByFragment(fragment1);
				fragment2 = fragmentDao.getFragmentByFragment(fragment2);
				int fragmentID1 = fragment1.getFragmentID();
				int fragmentID2 = fragment2.getFragmentID();
				DetectorDao detectorDao = new DetectorDao();
				Detector detector = new Detector(detectorName, detectorConfig);
				detectorDao.addDetector(detector);
				int detectorID = detectorDao.getDetectorByDetector(detector).getDetectorID();
				CloneDao cloneDao = new CloneDao(); 
				CodeClone codeClone = new CodeClone(fragmentID1, fragmentID2, detectorID);
				cloneDao.addClone(codeClone);
			}
			finalMessage = "Success!";
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error upload the csv file", exc);
			addErrorMessage(exc);
			finalMessage = "Upload failed.";
		}
	}
	
//	public void isDetectorNameValid(FacesContext context, 
//			  UIComponent component, 
//			  Object value) throws Exception {
//		System.out.println("@154 isValid!!!!!!!!");//test
//		Boolean isValid = true;
//		if (value == null) {
//			isValid = false;
//		}
//		String detectorName = value.toString();
//		if (detectorName.equals("")) {
//			isValid = false;
//		}
//		if (!isValid) {
//			System.out.println("Detector Name inValid");//test
//			FacesMessage message = new FacesMessage("Please type in detector name.");
//			throw new ValidatorException(message);
//		}
//	}
//	
//	public void isDetectorConfigValid(FacesContext context, 
//			  UIComponent component, 
//			  Object value) throws Exception {
//		System.out.println("@174 isValid!!!!!!!!");//test
//		Boolean isValid = true;
//		if (value == null) {
//			isValid = false;
//		}
//		String detectorConfig = value.toString();
//		if (detectorConfig.equals("")) {
//			isValid = false;
//		}
//		if (!isValid) {
//			FacesMessage message = new FacesMessage("Please type in detector configuration.");
//			throw new ValidatorException(message);
//		}
//	}

	public String getDetectorName() {
		return detectorName;
	}

	public void setDetectorName(String detectorName) {
		this.detectorName = detectorName;
	}

	public String getDetectorConfig() {
		return detectorConfig;
	}

	public void setDetectorConfig(String detectorConfig) {
		this.detectorConfig = detectorConfig;
	}

	public Part getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(Part csvFile) {
		this.csvFile = csvFile;
	}

	public String getInputMessage() {
		return inputMessage;
	}

	public void setInputMessage(String inputMessage) {
		this.inputMessage = inputMessage;
	}

	public String getFinalMessage() {
		return finalMessage;
	}

	public void setFinalMessage(String finalMessage) {
		this.finalMessage = finalMessage;
	}

	public String getDetectorNameMessage() {
		return detectorNameMessage;
	}

	public void setDetectorNameMessage(String detectorNameMessage) {
		this.detectorNameMessage = detectorNameMessage;
	}

	public String getDetectorConfigMessage() {
		return detectorConfigMessage;
	}

	public void setDetectorConfigMessage(String detectorConfigMessage) {
		this.detectorConfigMessage = detectorConfigMessage;
	}

	public String getCsvFileMessage() {
		return csvFileMessage;
	}

	public void setCsvFileMessage(String csvFileMessage) {
		this.csvFileMessage = csvFileMessage;
	}
	
}

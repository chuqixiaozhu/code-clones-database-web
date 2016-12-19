package edu.wm.as.cs.codeclones.controller;

//import java.io.BufferedOutputStream;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
//import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
//import javax.faces.event.AjaxBehaviorEvent;
//import javax.faces.event.ValueChangeEvent;
//import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;
import net.lingala.zip4j.core.ZipFile;

@ManagedBean
@SessionScoped
public class ProjectController {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String FOLDER_PATH = new File(".").getAbsolutePath() 
										+ File.separator
										+ "code_clone_projects"
										+ File.separator;
	private Part zipFile;
	private Part csvFile;
	private String projectName;
	private String revisionName;
	private String revisionNameMessage;
	private String zipFileMessage;
	private String finalMessage;
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public ProjectController() {
		finalMessage = "";
	}
	
	public String addProjectCommandLink() {
		zipFile = null;
		csvFile = null;
		projectName = "";
		revisionName = "";
		zipFileMessage = "";
		finalMessage = "";
		revisionNameMessage = "";
		return "upload_project";
	}
	
	private void cleanMessages() {
		zipFileMessage = "";
		finalMessage = "";
		revisionNameMessage = "";
	}
	
//	public void isRevisionNameValid(FacesContext context, 
//			  UIComponent component, 
//			  Object value) throws Exception {
//		if (value == null) {
//			return;
//		}
	private Boolean isRevisionNameValid() throws Exception {
		RevisionDao revisionDao = new RevisionDao();
		Revision revision = revisionDao.getRevisionByName(revisionName);
		if (revision != null) {
			revisionNameMessage = "The Revision name already exists. Please change it.";
			throw new Exception("The Revision name already exists. Please change it.");
//			return false;
		} else {
			revisionNameMessage = "";
			return true;
		}
	}
	
	public void fromZipToName(){
		cleanMessages();
		try (InputStream inputStream = zipFile.getInputStream()) {
			if (projectName == null || projectName.equals("")) {
				String tmpName = zipFile.getSubmittedFileName();
				projectName = tmpName.substring(0, tmpName.length() - 4);
			}
			if (revisionName == null || revisionName.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
				revisionName = sdf.format(new java.util.Date());
			}
			System.out.println("fromZiptoname");//test
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error upload the zip file", exc);
			addErrorMessage(exc);
		}
	}
	
	public void projectUpload() {
		cleanMessages();
		try {
			saveZipFile();
			isRevisionNameValid();
			/* Add the Project to DB */
			ProjectDao projectDao = new ProjectDao();
			Project project = projectDao.getProjectByName(projectName);
			if (project == null) {
				project = new Project(projectName);
				projectDao.addProject(project);
			}
			int projectID = projectDao.getProjectByName(projectName).getProjectID();
			/* Add the Revision to DB */
			RevisionDao revisionDao = new RevisionDao();
			Revision revision = new Revision(projectID, revisionName);
			revisionDao.addRevision(revision);
			finalMessage = "Success!";
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error upload the zip file", exc);
			addErrorMessage(exc);
			finalMessage = "Upload failed.";
		}
	}
	
	
	private void saveZipFile() throws Exception {
		if (zipFile.getSize() == 0) {
			zipFileMessage = "Please choose zip file.";
			throw new Exception("The Zip File is empty."); 

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
			File newFile = new File(savePath, fileName);
			if (newFile.exists()) {
				newFile.delete();
			}
//			Files.copy(inputStream, new File(savePath, fileName).toPath());
			Files.copy(inputStream, newFile.toPath());
			
//			unZip(savePath + fileName, savePath);
			unZip2(savePath + fileName, savePath);
		} 
//		catch (Exception exc) {
//			logger.log(Level.SEVERE, "Error upload file", exc);
//			addErrorMessage(exc);
//		}
	}
	private void unZip2(String source, String outputPath) throws Exception {
		ZipFile zipFile = new ZipFile(source);
		zipFile.extractAll(outputPath);
	}
	
//	private void unZip(String zipFile, String outputPath) throws Exception {
//		
////		try
////		{
//		File folder = new File(outputPath);
//		if (!folder.exists()) {
//			folder.mkdir();
//		}
//		ZipInputStream zipInputStream =
//				new ZipInputStream(new FileInputStream(zipFile));
//		ZipEntry zipEntry = null;
//		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
//			String fileName = zipEntry.getName();
//			String filePath = outputPath + File.separator + fileName;
//			if (zipEntry.isDirectory()) {
//				File dir = new File(filePath);
//				dir.mkdir();
//			} else {
//				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
//				byte[] buffer = new byte[2048];
//				int len = 0;
//				while ((len = zipInputStream.read(buffer)) > 0) {
//					bos.write(buffer, 0, len);
//				}
//				bos.close();
//			}
////			
////			System.out.println("@139 fileName: " + fileName);//test
////			File newFile = new File(outputPath + File.separator + fileName);
////			System.out.println("file unzip: " + newFile.getAbsoluteFile());
////			
////			new File(newFile.getParent()).mkdirs();
////			System.out.println("@144");//test
////			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
////			System.out.println("@146");//test
////			int len;
////			while ((len = zipInputStream.read(buffer)) > 0) {
////				fileOutputStream.write(buffer, 0, len);
////			}
////			fileOutputStream.close();
//		}
//		zipInputStream.closeEntry();
//		zipInputStream.close();
////		} catch (Exception exc) {
////			exc.printStackTrace();
////		}
//	}
	
//	public void parseCSVFile() throws Exception {
//		try (Scanner sc = new Scanner(csvFile.getInputStream())) {
//			while (sc.hasNextLine()) {
//				String line = sc.nextLine();
//				String[] details = line.split(",");
//				String pathName1 = details[0].trim();
//
//				/* Get ProjectID and RevisionID */
//
//				/* Add The First Fragment into DB */
//
//				/* Add the Second Fragment into DB */
//	
//				/* Add the Code Clone into DB */
//			}
//		}
//	}

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

	public Part getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(Part csvFile) {
		this.csvFile = csvFile;
	}

	public String getFinalMessage() {
		return finalMessage;
	}

	public void setFinalMessage(String finalMessage) {
		this.finalMessage = finalMessage;
	}

	public String getZipFileMessage() {
		return zipFileMessage;
	}

	public void setZipFileMessage(String zipFileMessage) {
		this.zipFileMessage = zipFileMessage;
	}

	public String getRevisionNameMessage() {
		return revisionNameMessage;
	}

	public void setRevisionNameMessage(String revisionNameMessage) {
		this.revisionNameMessage = revisionNameMessage;
	}
	
	
}

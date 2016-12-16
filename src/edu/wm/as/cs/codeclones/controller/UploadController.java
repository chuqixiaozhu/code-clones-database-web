package edu.wm.as.cs.codeclones.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import edu.wm.as.cs.codeclones.dao.FragmentDao;
import edu.wm.as.cs.codeclones.dao.ProjectDao;
import edu.wm.as.cs.codeclones.dao.RevisionDao;
import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.entities.Fragment;
import edu.wm.as.cs.codeclones.entities.Project;
import edu.wm.as.cs.codeclones.entities.Revision;

@ManagedBean
@SessionScoped
public class UploadController {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String FOLDER_PATH = new File(".").getAbsolutePath() 
										+ File.separator
										+ "code_clone_projects"
										+ File.separator;
	private Part zipFile;
	private Part csvFile;
	private String projectName;
	private String revisionName;
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public UploadController() {

	}
	
	public void isRevisionNameValid(FacesContext context, 
			  UIComponent component, 
			  Object value) throws Exception {
		if (value == null) {
			return;
		}
		String revisionName = value.toString();
		RevisionDao revisionDao = new RevisionDao();
		Revision revision = revisionDao.getRevisionByName(revisionName);
		if (revision != null) {
			FacesMessage message = new FacesMessage("Revision name exists. Please change it.");
			throw new ValidatorException(message);
		}
	}
	
	public void projectUpload() {
		try {
			saveZipFile();
			/* Add the Project to DB */
			
			/* Add the Revision to DB */
			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error upload the zip file", exc);
			addErrorMessage(exc);
		}
	}
	
	private void saveZipFile() throws Exception {
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
			
			unZip(savePath + fileName, savePath);
		} 
//		catch (Exception exc) {
//			logger.log(Level.SEVERE, "Error upload file", exc);
//			addErrorMessage(exc);
//		}
	}
	
	private void unZip(String zipFile, String outputPath) throws Exception {
		byte[] buffer = new byte[2048];
//		try
//		{
		File folder = new File(outputPath);
		if (!folder.exists()) {
			folder.mkdir();
		}
		ZipInputStream zipInputStream =
				new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry zipEntry = null;
		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String fileName = zipEntry.getName();
			File newFile = new File(outputPath + File.separator + fileName);
			System.out.println("file unzip: " + newFile.getAbsoluteFile());
			
			new File(newFile.getParent()).mkdirs();
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			
			int len;
			while ((len = zipInputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, len);
			}
			fileOutputStream.close();
		}
		zipInputStream.closeEntry();
		zipInputStream.close();
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
	}
	
	public void parseCSVFile() throws Exception {
		try (Scanner sc = new Scanner(csvFile.getInputStream())) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] details = line.split(",");
				String pathName1 = details[0].trim();

				/* Get ProjectID and RevisionID */

				/* Add The First Fragment into DB */

				/* Add the Second Fragment into DB */
	
				/* Add the Code Clone into DB */
			}
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

	public Part getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(Part csvFile) {
		this.csvFile = csvFile;
	}
	
}

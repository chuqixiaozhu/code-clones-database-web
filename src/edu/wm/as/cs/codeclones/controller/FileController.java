package edu.wm.as.cs.codeclones.controller;

//import java.io.IOException;
import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import edu.wm.as.cs.codeclones.dao.CloneDao;
import edu.wm.as.cs.codeclones.dao.FileDao;
import edu.wm.as.cs.codeclones.entities.CodeClone;
import edu.wm.as.cs.codeclones.entities.File;

@ManagedBean
@SessionScoped
public class FileController {
//	private Part codeListFile;
	private FileDao fileDao;
	private Logger logger = Logger.getLogger(getClass().getName());

	private Part codeFile1;
	private Part codeFile2;
	private Part csvFile;
	private CodeClone theClone;
//	List<String> fileText;
	
	public FileController () throws Exception {
		fileDao = FileDao.getInstance();
		codeFile1 = null;
		codeFile2 = null;
		csvFile = null;
		theClone = new CodeClone();
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String uploadCloneInfoFile() {
		if (null != csvFile) {
			Scanner sc = null;
			File file1 = new File();
			File file2 = new File();
//			Boolean hasData = false;
//			fileText.clear();
            try {
                InputStream is = csvFile.getInputStream();
                sc = new Scanner(is);
//                while (extracted(is).hasNext()) {
                if (sc.hasNextLine()) {
//                	hasData = true;
//                	fileText = extracted(is).useDelimiter("\\A").next();
                	String line = sc.nextLine();
//                	String line = sc.useDelimiter("\\A").next();
                	String[] at = line.split(",");

                	/* File 1 */
                    file1.setFileName(at[2].trim());
                    file1.setProjectName(at[0].trim());
                    file1.setRevisionName(at[1].trim());
                    is = codeFile1.getInputStream();
//                	fileDao.addFileByInputStream(file1, is);
                    /* File 2 */
                    file2.setFileName(at[7].trim());
                    file2.setProjectName(at[5].trim());
                    file2.setRevisionName(at[6].trim());
                	is = codeFile2.getInputStream();
//                	fileDao.addFileByInputStream(file2, is);
                	/* clone */
                	CodeClone tempClone = new CodeClone();
                	tempClone.setProject1Name(at[0].trim());
                	tempClone.setRevision1Name(at[1].trim());
                	tempClone.setFileName1(at[2].trim());
                	tempClone.setStartLine1(Integer.parseInt(at[3].trim()));
                	tempClone.setEndLine1(Integer.parseInt(at[4].trim()));
                	tempClone.setProject2Name(at[5].trim());
                	tempClone.setRevision2Name(at[6].trim());
                	tempClone.setFileName2(at[7].trim());
                	tempClone.setStartLine2(Integer.parseInt(at[8].trim()));
                	tempClone.setEndLine2(Integer.parseInt(at[9].trim()));
                	tempClone.setDetectorName(at[10].trim());
                	tempClone.setConfiguration(at[11].trim());
//                	CloneDao cloneDao = CloneDao.getInstance();
//                	cloneDao.addCloneByClone(tempClone);
                	theClone = tempClone;
//                	return "clones_list";
                }
            } catch (Exception exc) {
            	logger.log(Level.SEVERE, "Error loading projects", exc);
    			addErrorMessage(exc);
            } finally {
            	if(sc != null) {
            		sc.close();
            	}
            }
        }
		return null;
	}
	
	public String saveFiles() {
		File file1 = new File();
		File file2 = new File();
		try {
			file1.setFileName(theClone.getFileName1());
			file1.setProjectName(theClone.getProject1Name());
			file1.setRevisionName(theClone.getRevision1Name());
			InputStream is = codeFile1.getInputStream();
			Scanner sc = new Scanner(is);
			if (sc.hasNextLine()) {
				fileDao.addFileByInputStream(file1, is);
			}
        	
        	file2.setFileName(theClone.getFileName2());
			file2.setProjectName(theClone.getProject2Name());
			file2.setRevisionName(theClone.getRevision2Name());
			is = codeFile2.getInputStream();
			sc.close();
			sc = new Scanner(is);
			if (sc.hasNextLine()) {
				fileDao.addFileByInputStream(file2, is);
			}
        	sc.close();
			if (theClone.getProject1Name() != null) {
				CloneDao cloneDao = CloneDao.getInstance();
	        	cloneDao.addCloneByClone(theClone);
	        	System.out.println("theClone: " + theClone);//test
	        	theClone = new CodeClone();
	        	return "clones_list";
			} else {
				return null;
			}
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading projects", exc);
			addErrorMessage(exc);
		}
		return null;
	}

	public Part getCodeFile1() {
		return codeFile1;
	}

	public void setCodeFile1(Part codeFile1) {
		this.codeFile1 = codeFile1;
	}

	public Part getCodeFile2() {
		return codeFile2;
	}

	public void setCodeFile2(Part codeFile2) {
		this.codeFile2 = codeFile2;
	}

	public Part getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(Part csvFile) {
		this.csvFile = csvFile;
	}

	public CodeClone getTheClone() {
		return theClone;
	}

	public void setTheClone(CodeClone theClone) {
		this.theClone = theClone;
	}
	
	

//	private Scanner extracted(InputStream is) {
//		return new Scanner(is);
//	}
	
}

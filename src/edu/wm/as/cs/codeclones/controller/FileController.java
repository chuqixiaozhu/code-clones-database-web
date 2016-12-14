package edu.wm.as.cs.codeclones.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class FileController {
	private Part codeListFile;
	private String fileText;
	
	public FileController() {
		codeListFile = null;
	}

	public Part getCodeListFile() {
		return codeListFile;
	}

	public void setCodeListFile(Part codeListFile) {
		this.codeListFile = codeListFile;
	}

	public String getFileText() {
		return fileText;
	}

	public void setFileText(String fileText) {
		this.fileText = fileText;
	}
	
	
	public void uploadFile() {
		if (null != codeListFile) {
            try {
                InputStream is = codeListFile.getInputStream();
                if (extracted(is).hasNext()) {
                	fileText = extracted(is).useDelimiter("\\A").next();
                }
            } catch (IOException ex) {
            }
        }
	}

	private Scanner extracted(InputStream is) {
		return new Scanner(is);
	}
	
}

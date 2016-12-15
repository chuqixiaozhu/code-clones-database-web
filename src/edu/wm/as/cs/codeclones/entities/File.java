package edu.wm.as.cs.codeclones.entities;

//import java.io.InputStream;
import java.sql.Clob;
//import java.util.Scanner;

public class File {
	private int fileID;
	private String fileName;
	private String projectName;
	private String revisionName;
	private Clob fileData;
	
	public File() {
		
	}


	public File(int fileID, String fileName, String projectName, String revisionName, Clob fileData) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.projectName = projectName;
		this.revisionName = revisionName;
		this.fileData = fileData;
	}


	public int getFileID() {
		return fileID;
	}


	public void setFileID(int fileID) {
		this.fileID = fileID;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

//	public String getFileData() {
////		return fileData;
//		String code = new String("");
//		try (Scanner sc = new Scanner(fileData.getAsciiStream())) {
//			while (sc.hasNextLine()) {
//				String line = sc.nextLine();
//				code += line + "\n";
//			}
//			System.out.println(code);
//			return code;
//		} catch (Exception exc) {
//			System.out.println("Cannot get file1's code");
//		}
////		InputStream is = fileData.getAsciiStream();
////		Scanner sc = new Scanner(is);
//		return null;
//	}
	
	public Clob getFileData() {
		return fileData;
	}
	
	

	public void setFileData(Clob fileData) {
		this.fileData = fileData;
	}
}

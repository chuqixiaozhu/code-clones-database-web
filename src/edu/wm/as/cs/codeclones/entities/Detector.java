package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Detector {
	private int detectorID;
	private String detectorName;
	private String detectorConfig;
	
	public Detector() {
		
	}

	public Detector(int detectorID, String detectorName, String detectorConfig) {
		super();
		this.detectorID = detectorID;
		this.detectorName = detectorName;
		this.detectorConfig = detectorConfig;
	}

	public int getDetectorID() {
		return detectorID;
	}

	public void setDetectorID(int detectorID) {
		this.detectorID = detectorID;
	}

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

	@Override
	public String toString() {
		return "Detector [detectorID=" + detectorID + ", detectorName=" + detectorName + ", detectorConfig="
				+ detectorConfig + "]";
	}
	
}

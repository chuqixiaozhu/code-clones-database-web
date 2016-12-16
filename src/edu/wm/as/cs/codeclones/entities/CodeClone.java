package edu.wm.as.cs.codeclones.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CodeClone {
	private int cloneID;
	private int fragment1ID;
	private int fragment2ID;
	private int detectorID;
	
	public CodeClone() {

	}

	public CodeClone(int cloneID, int fragment1id, int fragment2id, int detectorID) {
		super();
		this.cloneID = cloneID;
		fragment1ID = fragment1id;
		fragment2ID = fragment2id;
		this.detectorID = detectorID;
	}

	public CodeClone(int fragment1id, int fragment2id, int detectorID) {
		super();
		fragment1ID = fragment1id;
		fragment2ID = fragment2id;
		this.detectorID = detectorID;
	}

	public int getCloneID() {
		return cloneID;
	}

	public void setCloneID(int cloneID) {
		this.cloneID = cloneID;
	}

	public int getFragment1ID() {
		return fragment1ID;
	}

	public void setFragment1ID(int fragment1id) {
		fragment1ID = fragment1id;
	}

	public int getFragment2ID() {
		return fragment2ID;
	}

	public void setFragment2ID(int fragment2id) {
		fragment2ID = fragment2id;
	}

	public int getDetectorID() {
		return detectorID;
	}

	public void setDetectorID(int detectorID) {
		this.detectorID = detectorID;
	}

	@Override
	public String toString() {
		return "CodeClone [cloneID=" + cloneID + ", fragment1ID=" + fragment1ID + ", fragment2ID=" + fragment2ID
				+ ", detectorID=" + detectorID + "]";
	}

}
